package cn.whu.zl.service;

import static cn.whu.zl.util.CrawlerUtil.GBK;
import static cn.whu.zl.util.CrawlerUtil.strToFloat;
import static cn.whu.zl.util.CrawlerUtil.strToInt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.InstitutionNameDao;
import cn.whu.zl.dao.JrjStockReportDao;
import cn.whu.zl.dao.StockIndustryDao;
import cn.whu.zl.entity.InstitutionName;
import cn.whu.zl.entity.JrjStockReport;
import cn.whu.zl.entity.StockDataTitlePK;

@Transactional
@Service("jrjStockReportSer")
@Scope("prototype")
public class JrjStockReportService extends BasicCrawServiceImp {
	private static final Logger log = Logger
			.getLogger(JrjStockReportService.class.getName());

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
			Locale.CHINA);
	
	public JrjStockReportService() {
		log.debug("StockReportService crawler initialization begin");

		mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

		initiaHttpClient();

		seed = "http://app.jrj.com.cn/appdata/data.php?cid=114&FORECAST_YEAR=2014&ORGCODE=1&INVEST_RATING=1&RATING_CHG=1&ISFIRST=1&declare=y2&ob=0&od=d&page=1&psize=5000&vname=ggyj";

		log.debug("StockReportService crawler initialization end");
	}

	@Override
	public void start() {
		log.info("StockReportService begin");
//		 try {
//		 crawStockReport(11, DEFAULT_PAGESISE, GBK);
//		 } catch (Exception e) {
//		 }
		//crawStcokReport();
		addReportDate();
		log.info("StockReportService end");
	}

	// public List<StockReport> getAllList(int recordNo,int pageSize){
	// return stockReportDao.getAllList(recordNo, pageSize);
	// }

	public Long getTotalRecords() {
		return jrjStockReportDao.getSizes();
	}
	
	private void addReportDate(){
		try {
			Date begin = sdf.parse("2013-01-01");
			Date end = sdf.parse("2014-05-30");
			Date ReportDate;
			
			List<JrjStockReport> reportList;
			for(String stockID : getCodeList()){
				reportList = jrjStockReportDao.getStockReport(stockID, begin, end);
				for(int i = 0;i < reportList.size();i++){
					
					if(reportList.get(i).getReportDate() != null)
						continue;
					String linkURL = reportList.get(i).getSdtPK().getLinkURL();
					linkURL = linkURL.substring(7);
					if(linkURL.split(",")[1].equals("null"))
						continue;
					//log.info("stockID = "+ stockID + " linkURL = "+ linkURL +" begin");
					ReportDate = crawReportDate(linkURL);
					//ReportDate = crawReportDate("http://istock.jrj.com.cn/article,yanbao,24007336.html");
					updateReportDate(reportList.get(i).getSdtPK(),ReportDate);
					
					//log.info("stockID = "+ stockID + " linkURL = "+ linkURL +" end");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private Date crawReportDate(String linkURL){
		Date ReportDate = null;
		final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.CHINA);
		HttpGet httpGet = null;
		try{
			uriBuilder = new URIBuilder(linkURL);
			httpGet = new HttpGet(uriBuilder.build());
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			
			int reTry =0;
			while(statusLine.getStatusCode() != HttpStatus.SC_OK) {
				reTry++;
				log.warn("第 " + reTry + " 次连接失败");
				if (reTry == 10) {
					log.error("Request failed : " + statusLine);
					throw new ClientProtocolException(statusLine.toString());
				}
				httpGet.releaseConnection();
				response = client.execute(httpGet);
				statusLine = response.getStatusLine();
				
			}
			HttpEntity entity = response.getEntity();
			String html = EntityUtils.toString(entity, GBK);

			log.debug(html);
			if (html != null && html.length() != 0){
				Document doc = Jsoup.parse(html);
				Element title = doc.getElementsByClass("lou").first();
				if(title == null)
					return null;
				String temp = 
						title.getElementsByClass("title").text().split("发表于")[1].substring(1);
				//ReportDate = temp.substring(0, temp.length()-3);
				try {
					ReportDate = sdf2.parse(temp);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			/*seed = "http://istock.jrj.com.cn/topicgetclick.jspa";
			uriBuilder = new URIBuilder(seed);
			String[] temp = linkURL.split(",");
			String[] temp2 = temp[2].split("\\.");//转义
			String id = temp2[0];
			uriBuilder.setParameter("forumid", "yanbao").setParameter("topicid", id);
			httpGet = new HttpGet(uriBuilder.build());
			HttpResponse response2 = client.execute(httpGet);
			StatusLine statusLine2 = response.getStatusLine();
			int reTry2 =0;
			while(statusLine2.getStatusCode() != HttpStatus.SC_OK) {
				reTry2++;
				log.warn("第 " + reTry2 + " 次连接失败");
				if (reTry2 == 10) {
					log.error("Request failed : " + statusLine2);
					throw new ClientProtocolException(statusLine2.toString());
				}
				httpGet.releaseConnection();
				response2 = client.execute(httpGet);
				statusLine2 = response.getStatusLine();
				
			}
			HttpEntity entity2 = response2.getEntity();
			String html2 = EntityUtils.toString(entity2, GBK);

			log.debug(html2);
			if (html2 != null && html2.length() != 0){
				String temp3 = html2.split(";")[0].split("=")[1];
				ReportDateVisits[1] = temp3.substring(1, temp3.length()-1);
				
			}*/
			
		} catch (URISyntaxException use) {
			
		} catch (ClientProtocolException cpe) {
			
		} catch (IOException ioe) {
			
		} finally {
			if (httpGet != null)
				httpGet.releaseConnection();
		}
		return ReportDate;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void updateReportDate(StockDataTitlePK sdt,Date ReportDate){
		jrjStockReportDao.updateReportDate(sdt,ReportDate);
	}
	
	private void crawStcokReport() {
		try {
			int pages = crawReportPages(DEFAULT_PAGESISE, seed, GBK);
			log.info("总共有" + pages + "页记录");

			for (int i = 1; i <= pages; i++)
				try {
					crawStockReport(i, DEFAULT_PAGESISE, GBK);
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

	@Transactional(propagation = Propagation.REQUIRED)
	private void crawStockReport(int page, String count, String charSet)
			throws URISyntaxException, ClientProtocolException, IOException,
			ParseException {
		log.info("craw " + page + "th " + count + " jrjStockReport begin");

		HttpGet httpGet = null;
		try {

			uriBuilder = new URIBuilder(seed);

			// uriBuilder.setParameter("tp", "");
			// uriBuilder.setParameter("cg", "");
			// uriBuilder.setParameter("jg", "");
			uriBuilder.setParameter("psize", count);
			uriBuilder.setParameter("page", String.valueOf(page));

			httpGet = new HttpGet(uriBuilder.build());
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int reTry = 0;
			while (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				reTry++;
				log.warn("第 " + reTry + " 次连接失败");
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
			if (html != null && html.length() != 0) {
				html = html.substring(html.indexOf("{"));

				html = html.substring(html.indexOf("[") + 1, html.length() - 2);
				String[] reports = html.split("],");
				List<JrjStockReport> toSave = new ArrayList<>(
						Integer.parseInt(count));
				for (int i = 1; i < reports.length; i++) {
					JrjStockReport sp = new JrjStockReport();
					StockDataTitlePK sdtPK = new StockDataTitlePK();
					String report = new String(reports[i]);
					String[] tmp1 = report.split("'");
					String[] tmp = new String[19];

					for (int j = 1; j < tmp1.length; j += 2) {
						tmp[(j - 1) / 2] = tmp1[j];
					}
					log.debug(tmp1.length + "," + i);
					log.debug(i + " , " + tmp[14]);
					Date date = new SimpleDateFormat("yyyy-MM-dd")
							.parse(new String(tmp[14]));
					sdtPK.setStockID(new String(tmp[0]));
					sdtPK.setDate(date);
					StringBuilder link = new StringBuilder(preURL);
					link.append(tmp[18]);
					link.append(",");
					link.append(tmp[17]);
					link.append(sufURL);
					sdtPK.setLinkURL(link.toString());
					sp.setSdtPK(sdtPK);

					sp.setStockName(new String(tmp[1]));
					sp.setOrgName(new String(tmp[2]));
					sp.setOrgID(new String(tmp[3]));
					sp.setCurPrice(strToFloat(new String(tmp[6])));
					sp.setTarPrice(strToFloat(new String(tmp[9])));
					sp.setTarRatio(strToFloat(new String(tmp[16])));
					sp.setPreIncomeThis(strToFloat(new String(tmp[10])));
					sp.setOrgReportNum(strToInt(new String(tmp[11])));
					sp.setPrePEratioThis(strToFloat(new String(tmp[12])));
					String title = new String(tmp[13]);
					String industry = new String(tmp[4]);
					try {
						title = new String(title.getBytes(GBK), GBK);
						industry = new String(industry.getBytes(GBK), GBK);
					} catch (UnsupportedEncodingException e) {
						log.error(e);
					}
					sp.setTitle(title);
					sp.setIndustry(industry);
					sp.setGradeChange(new String(tmp[8]));
					sp.setGrade(new String(tmp[7]));
					toSave.add(sp);

				}
				saveToDBList(toSave);
				jrjStockReportDao.flush();
			}
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

	}

	private int crawReportPages(String pageNum, String url, String charSet)
			throws ClientProtocolException, IOException {
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

			JsonNode node = mapper.readTree(html.substring(html.indexOf("{")));
			int pages = node.get("summary").get("pages").getIntValue();

			// Map<String,Map<String,Object>> map = mapper.readValue(html,
			// Map.class);
			// int count = (Integer) map.get("summary").get("total");

			log.debug(pages);

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
	private void saveToDB(JrjStockReport sp) {
		jrjStockReportDao.update(sp);
		/*
		 * stockReportDao.flush(); sp = null;
		 */
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void saveToDBList(List<JrjStockReport> list) {
		for (JrjStockReport sp : list)
			jrjStockReportDao.update(sp);
		/*
		 * stockReportDao.flush(); sp = null;
		 */
	}

	@Override
	public List<String> getCodeList(){
		if(codeList == null || codeList.size() == 0)
			codeList = StockIndustryDao.getAllStockID();
		return codeList;
	}
	
	@Override
	public void setCodeList(List<String> codeList){
		this.codeList = codeList;
		setMaxCode(codeList.get(codeList.size() - 1));
		setMinCode(codeList.get(0));
		
	}
	
	@Autowired
	JrjStockReportDao jrjStockReportDao;
	
	@Autowired
	private InstitutionNameDao institutionNameDao;
	
	@Autowired
	private StockIndustryDao StockIndustryDao;

	private final String preURL = "http://http://istock.jrj.com.cn/article,";
	private final String sufURL = ".html";
	private final String DEFAULT_PAGESISE = "5000";
	private final ObjectMapper mapper;
}
