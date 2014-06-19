package cn.whu.zl.service;

import static cn.whu.zl.util.CrawlerUtil.GBK;
import static cn.whu.zl.util.CrawlerUtil.getIndexFromArr;
import static cn.whu.zl.util.FinConstants.REPORT_GRADE_ARR;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.IndustryReportDao;
import cn.whu.zl.entity.IndustryNameDataTitlePK;
import cn.whu.zl.entity.IndustryReport;
import cn.whu.zl.entity.TransactionHistory;

@Transactional
@Service("industryReportSer")
public class IndustryReportService extends BasicCrawServiceImp {
	private static final Logger log = Logger
			.getLogger(IndustryReportService.class.getName());

	public IndustryReportService() {
		log.debug("IndustryReportService crawler initialization begin");

		initiaHttpClient();

		seed = "http://data.eastmoney.com/report/data.aspx?style=hyyb&dt=m6&pageSize=50000&page=1";

		log.debug("IndustryReportService crawler initialization end");
	}

	@Override
	public void start() {
		log.warn("IndustryReportService begin");
		crawIndustryReport();
		log.warn("IndustryReportService end");
	}
	
	public List<IndustryReport> getAllList(int recordNo,int pageSize){
		return industryReportDao.getAllList(recordNo, pageSize);
	}
	
	public Long getTotalRecords(){
		return industryReportDao.getSizes();
	}

	private void crawIndustryReport() {
		try {
			int pages = crawReportPages(DEFAULT_PAGESISE, preURL, GBK);
			for (int i = 1; i <= pages; i++) {
				try {
					crawIndustryReport(i, DEFAULT_PAGESISE, GBK);
				} catch (URISyntaxException use) {
					log.error(use);
				} catch (ClientProtocolException cpe) {
					log.error(cpe);
					cpe.printStackTrace();
				} catch (IOException ioe) {
					log.error(ioe);
					ioe.printStackTrace();
				} catch (ParseException pe) {
					log.error(pe);
					pe.printStackTrace();
				} catch (Exception e) {
					log.error(e);
					e.printStackTrace();
				}
			}
		} catch (ClientProtocolException cpe) {
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

	private void crawIndustryReport(int page, String count, String charSet)
			throws URISyntaxException, ClientProtocolException, IOException,
			ParseException {
		log.warn("craw " + page + "th " + count + " industryReport begin");

		HttpGet httpGet = null;
		try {

			uriBuilder = new URIBuilder(seed);

			uriBuilder.setParameter("style", "hyyb");
			// uriBuilder.setParameter("tp", "");
			// uriBuilder.setParameter("cg", "");
			uriBuilder.setParameter("dt", "m6");
			// uriBuilder.setParameter("jg", "");
			uriBuilder.setParameter("pageSize", count);
			uriBuilder.setParameter("page", String.valueOf(page));

			httpGet = new HttpGet(uriBuilder.build());
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
			html = html.substring(html.indexOf("[") + 1, html.indexOf("]"));
			String[] reports = html.split("\"");
			int len = reports.length;
			for (int i = 1; i < len - 1; i += 2) {
				IndustryReport ir = new IndustryReport();
				IndustryNameDataTitlePK idtPK = new IndustryNameDataTitlePK();
				String report = new String(reports[i]);
				String[] tmp = report.split(",");
				Date date = new SimpleDateFormat("yyyy-MM-dd")
						.parse(new String(tmp[4]));
				idtPK.setIndustryName(new String(tmp[1]));
				idtPK.setDate(date);
				StringBuilder link = new StringBuilder(preURL);
				link.append(new SimpleDateFormat("yyyyMMdd").format(date));
				link.append("/hy,");
				link.append(new String(tmp[9]));
				link.append(sufURL);
				idtPK.setLinkURL(link.toString());
				ir.setIdtPK(idtPK);
				ir.setOrgName(new String(tmp[7]));
				String title = new String(tmp[10]);
				try {
					title = new String(title.getBytes(GBK),GBK);
				} catch (UnsupportedEncodingException e) {
					title = "";
				}
				ir.setTitle(title);
				ir.setGradeChange(Byte.parseByte(new String(tmp[6])));
				ir.setGrade(Byte.parseByte(String.valueOf(getIndexFromArr(
						new String(tmp[5]), REPORT_GRADE_ARR))));
				ir.setOrgWeight(Byte.parseByte(new String(tmp[11])));
				// log.warn(ir.getTitle());
				saveToDB(ir);
			}
			// industryReportDao.flush();
		} catch (URISyntaxException use) {
			throw use;
		} catch (ClientProtocolException cpe) {
			throw cpe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (httpGet != null)
				httpGet.releaseConnection();
		}

		log.warn("craw " + page + "th " + count + " industryReport end");
	}

	private int crawReportPages(String pageNum, String url, String charSet)
			throws ClientProtocolException, IOException {
		int pages = 0;
		HttpGet httpGet = null;
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
			int repNum = Integer
					.parseInt(doc.getElementById("list_num").text());
			int pageSize = Integer.parseInt(pageNum);
			pages = (repNum % pageSize == 0) ? repNum / pageSize : repNum
					/ pageSize + 1;

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

	@Transactional(propagation = Propagation.REQUIRED)
	private void saveToDB(IndustryReport ir) {
		industryReportDao.saveOrUpdate1(ir);
	}

	@Autowired
	IndustryReportDao industryReportDao;

	private final String preURL = "http://data.eastmoney.com/report/";
	private final String sufURL = ".html";
	private final String DEFAULT_PAGESISE = "100";
}
