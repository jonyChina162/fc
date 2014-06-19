package cn.whu.zl.service;

import static cn.whu.zl.util.CrawlerUtil.GBK;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

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

import cn.whu.zl.dao.FundFlowHistoryDao;
import cn.whu.zl.dao.StockIndustryDao;
import cn.whu.zl.entity.FundFlowHistory;
import cn.whu.zl.entity.StockDataPK;
import cn.whu.zl.entity.TransactionHistory;

@Service("fundFlowHistorySer")
@Transactional
public class FundFlowHistoryService extends BasicCrawServiceImp {
	private static final Logger log = Logger
			.getLogger(FundFlowHistoryService.class.getName());

	public FundFlowHistoryService() {
		log.debug("FundFlowHistory crawler initialization begin");

		initiaHttpClient();

		log.debug("FundFlowHistory crawler initialization end");
	}

	@Override
	public void start() {
		crawFundFlowHistory();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}
	
	public List<FundFlowHistory> getAllList(int recordNo,int pageSize){
		return fundFlowHistoryDao.getAllList(recordNo, pageSize);
	}
	
	public Long getTotalRecords(){
		return fundFlowHistoryDao.getSizes();
	}

	/**
	 * Description
	 */
	private void crawFundFlowHistory() {
		log.warn("craw FundFlowHistory from " + getMinCode() + " to "
				+ getMaxCode() + " start");
		int count = 0;
		for (String code : getCodeList()) {
			try {
				crawFundFlowHistory(code, GBK);
			} catch (ClientProtocolException cpe) {
				log.error(code + " th record failed!");
				log.error(cpe.getStackTrace());
			} catch (IOException ioe) {
				log.error(code + " th record failed!");
				log.error(ioe.getStackTrace());
			} catch (ParseException pe) {
				log.error(code + " th record failed!");
				log.error(pe.getStackTrace());
			} catch (Exception e) {
				log.error(code + " th record failed!");
				log.error(e.getStackTrace());
			}
			if (++count == 10) {
				fundFlowHistoryDao.flush();
				count = 0;
			}
		}
		log.warn("craw FundFlowHistory from " + getMinCode() + " to "
				+ getMaxCode() + " end");
	}

	private void crawFundFlowHistory(String code, String charSet)
			throws ClientProtocolException, ParseException, IOException,
			Exception {
		// log.warn("craw " + code + " start");
		seed = preURL + code + sufURL;
		HttpGet httpGet = new HttpGet(seed);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();

			if (statusLine.getStatusCode() != HttpStatus.SC_OK)
				log.error("Request failed : " + statusLine);
			else {
				String html = EntityUtils.toString(response.getEntity(),
						charSet);

				Document doc = Jsoup.parse(html);

				Elements mod_pages = doc.getElementsByClass("mod_pages");

				int len = 0;
				if (mod_pages != null)
					len = doc.getElementsByClass("mod_pages").first()
							.getElementsByTag("a").size() - 1;
				for (int i = 0; i < len; i++)
					crawFundFlowEachPage(code, i, charSet);
			}

			// log.warn("craw " + code + " end");
		} catch (ClientProtocolException cpe) {
			throw cpe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			httpGet.releaseConnection();
		}
	}

	/**
	 * 
	 */
	private void crawFundFlowEachPage(String code, int page, String charSet)
			throws ClientProtocolException, ParseException, IOException,
			Exception {
		seed = preURL + code + "," + page + sufURL;
		HttpGet httpGet = new HttpGet(seed);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();

			if (statusLine.getStatusCode() != HttpStatus.SC_OK)
				log.error("Request failed : " + statusLine);
			else {
				String html = EntityUtils.toString(response.getEntity(),
						charSet);

				Document doc = Jsoup.parse(html);
				// log.warn(html);
				Element table = doc.getElementsByTag("table").last();
				Elements trs = table.getElementsByTag("tr");
				trs.remove(0);
				for (Element tr : trs) {
					saveToDB(getEntityFromElement(code, tr));
				}

			}

		} catch (ClientProtocolException cpe) {
			throw cpe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			httpGet.releaseConnection();
		}
	}

	private FundFlowHistory getEntityFromElement(String code, Element ele)
			throws ParseException {
		Elements tds = ele.getElementsByTag("td");
		FundFlowHistory ffh = new FundFlowHistory();
		Iterator<Element> it = tds.iterator();
		ffh.setSdPK(new StockDataPK(code, new SimpleDateFormat("yyyy-MM-dd")
				.parse(it.next().text())));
		it.next();
		it.next();
		it.next();
		ffh.setFundIn(Integer.parseInt(it.next().text().replaceAll(",", "")));
		ffh.setFundOut(Integer.parseInt(it.next().text().replaceAll(",", "")));
		ffh.setNetIn(Integer.parseInt(it.next().text().replaceAll(",", "")));
		ffh.setMainIn(Integer.parseInt(it.next().text().replaceAll(",", "")));
		ffh.setMainOut(Integer.parseInt(it.next().text().replaceAll(",", "")));
		ffh.setMainNetIn(Integer.parseInt(it.next().text().replaceAll(",", "")));
		return ffh;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void saveToDB(FundFlowHistory ffh) {
		log.debug("write " + ffh.toString() + " to DB begin");
		fundFlowHistoryDao.save(ffh);
		log.debug("write " + ffh.toString() + " to DB end");
		ffh = null;
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
	private FundFlowHistoryDao fundFlowHistoryDao;
	@Autowired
	private StockIndustryDao stockIndustryDao;

	private final String preURL = "http://quotes.money.163.com/trade/lszjlx_";
	private final String sufURL = ".html";

	List<String> codeList;
}
