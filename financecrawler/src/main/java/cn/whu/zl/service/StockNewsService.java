package cn.whu.zl.service;

import static cn.whu.zl.util.CrawlerUtil.GBK;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.StockIndustryDao;
import cn.whu.zl.dao.StockNewsDao;
import cn.whu.zl.entity.StockDataTitlePK;
import cn.whu.zl.entity.StockNews;
import cn.whu.zl.entity.TransactionHistory;

@Service("stockNewsSer")
@Transactional
public class StockNewsService extends BasicCrawServiceImp {
	private static final Logger log = Logger.getLogger(StockNewsService.class
			.getName());

	public StockNewsService() {
		log.debug("stockNewsService crawler initialization begin");

		initiaHttpClient();

		log.debug("stockNewsService crawler initialization end");
	}

	@Override
	public void start() {
		crawStcokNews();
	}
	
	public List<StockNews> getAllList(int recordNo,int pageSize){
		return stockNewsDao.getAllList(recordNo, pageSize);
	}
	
	public Long getTotalRecords(){
		return stockNewsDao.getSizes();
	}

	private void crawStcokNews() {
		for (String code : codeList) {
			if (code.compareTo(getMinCode()) >= 0
					&& code.compareTo(getMaxCode()) <= 0)
				try {
					crawStcokNews(code);
				} catch (ClientProtocolException | ParseException cpe) {
					log.error(cpe);
					cpe.printStackTrace();
				} catch (IOException ioe) {
					log.error(ioe);
					ioe.printStackTrace();
				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();
				}
		}
	}

	private void crawStcokNews(String code) throws ClientProtocolException,
			ParseException, IOException, Exception {
		log.warn("craw " + code + " stockNews begin");
		int pages = crawNewsPages(code, GBK);
		for (int i = 0; i < pages; i++) {
			crawStcokNews(i, code, GBK);
		}
		log.warn("craw " + code + " stockNews end");
	}

	private void crawStcokNews(int page, String code, String charSet)
			throws URISyntaxException, ClientProtocolException, IOException,
			ParseException {
		log.debug("craw " + code + " " + page + "th stockNews begin");

		HttpGet httpGet = null;
		String uri = new StringBuilder(preURL).append(code).append(",")
				.append(page).append(sufURL).toString();
		try {

			httpGet = new HttpGet(uri);
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int reTry = 0;
			while (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				reTry++;
				if (reTry == 10) {
					log.error("Request failed : " + statusLine);
					throw new ClientProtocolException(statusLine.toString());
				}
				httpGet.releaseConnection();
				response = client.execute(httpGet);
				statusLine = response.getStatusLine();
			}
			HttpEntity entity = response.getEntity();
			String html = EntityUtils.toString(entity, charSet);
			EntityUtils.consume(entity);

			log.debug(html);

			Document doc = Jsoup.parse(html);
			Element table = doc.getElementsByClass("tabs_panel").first();
			Elements trs = table.getElementsByTag("tr");
			if (trs.size() > 0)
				trs.remove(0);
			Iterator<Element> it_tr = trs.iterator();
			int len = trs.size();
			StockNews[] sns = new StockNews[len];
			for (int i = 0; i < len; i++) {
				StockNews sn = new StockNews();
				StockDataTitlePK sdtPK = new StockDataTitlePK();
				Element tr = it_tr.next();
				Date date = new SimpleDateFormat("yyyy-MM-dd")
						.parse(new String(tr.getElementsByTag("td").last()
								.text()));
				sdtPK.setStockID(new String(code));
				sdtPK.setDate(date);
				sdtPK.setLinkURL(new String(tr.getElementsByTag("td").first()
						.getElementsByTag("a").attr("href")));
				sn.setSdtPK(sdtPK);
				String title = new String(tr.getElementsByTag("td").first()
						.text());
				try {
					title = new String(title.getBytes(GBK), GBK);
				} catch (UnsupportedEncodingException e) {
				}
				sn.setTitle(title);
				sn.setExtendNosql("");
				sn.setTendency(Byte.parseByte("2"));
				sns[i] = sn;
			}

			saveArrToDB(sns);

		} catch (ClientProtocolException cpe) {
			throw cpe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (httpGet != null)
				httpGet.releaseConnection();
		}

		log.debug("craw " + code + " " + page + "th stockNews end");
	}

	private int crawNewsPages(String code, String charSet)
			throws ClientProtocolException, IOException {
		int pages = 0;
		HttpGet httpGet = null;
		String url = new StringBuilder(preURL).append(code).append(sufURL)
				.toString();
		try {
			httpGet = new HttpGet(url);
			HttpResponse response = client.execute(httpGet);

			StatusLine statusLine = response.getStatusLine();

			int reTry = 0;
			while (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				reTry++;
				if (reTry == 10) {
					log.error("Request failed : " + statusLine);
					throw new ClientProtocolException(statusLine.toString());
				}
				httpGet.releaseConnection();
				response = client.execute(httpGet);
				statusLine = response.getStatusLine();
				log.warn(reTry + "th try again!");
			}
			HttpEntity entity = response.getEntity();
			String html = EntityUtils.toString(entity, charSet);
			EntityUtils.consume(entity);

			Document doc = Jsoup.parse(html);
			Element mod_pages = doc.getElementsByClass("mod_pages").first();
			Elements a = mod_pages.getElementsByTag("a");
			int size = a.size();
			if (size == 0 || size == 1) {
				pages = 1;
			} else
				pages = Integer.parseInt(a.get(size - 2).text());
			return pages;
		} catch (ClientProtocolException cpe) {
			throw cpe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (httpGet != null)
				httpGet.releaseConnection();
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void saveToDB(StockNews sn) {
		stockNewsDao.save(sn);
		/*
		 * stockReportDao.flush(); sp = null;
		 */
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void saveArrToDB(StockNews[] sns) {
		stockNewsDao.saveArr(sns);
	}

	@Override
	public List<String> getCodeList() {
		if (null == codeList || codeList.size() == 0)
			codeList = stockIndustryDao.getAllStockID();
		return codeList;
	}

	@Override
	public void setCodeList(List<String> codeList) {
		this.codeList = codeList;
		setMaxCode(codeList.get(codeList.size() - 1));
		setMinCode(codeList.get(0));
	}

	@Autowired
	StockNewsDao stockNewsDao;

	@Autowired
	StockIndustryDao stockIndustryDao;

	private final String preURL = "http://quotes.money.163.com/f10/gsxw_";
	private final String sufURL = ".html";
	private List<String> codeList = null;
}
