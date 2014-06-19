package cn.whu.zl.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.StockIndustryDao;
import cn.whu.zl.entity.StockIndustry;
import cn.whu.zl.util.CrawlerUtil;

@Service("stockIndustrySer")
@Transactional
public class StockIndustryService extends BasicCrawServiceImp {
	private static final Logger log = Logger
			.getLogger(StockIndustryService.class.getName());

	public StockIndustryService() {
		log.debug("stockIndustry crawler initialization begin");

		seed = "http://quote.eastmoney.com/center/list.html";
		dynamicURL = "http://hqdigi2.eastmoney.com/EM_Quote2010NumericApplication/index.aspx";

		try {
			uriBuilder = new URIBuilder(dynamicURL);
		} catch (URISyntaxException uso) {
			log.error("Fatal error : " + uso);
			Runtime.getRuntime().exit(-1);
		}
		uriBuilder.setParameter("type", "s").setParameter("sortType", "C")
				.setParameter("sortRule", "-1")
				.setParameter("pageSize", "20000").setParameter("page", "1")
				.setParameter("style", "28002473");

		/*
		 * paramsIndex = new HashMap<>(); paramsIndex.put("type", 0);
		 * paramsIndex.put("sortType", 1); paramsIndex.put("sortRule", 2);
		 * paramsIndex.put("pageSize", 3); paramsIndex.put("page", 4);
		 * paramsIndex.put("style", 5);
		 * 
		 * params = new BasicNameValuePair[6]; setParam("type", "s");
		 * setParam("sortType", "C"); setParam("sortRule", "-1");
		 * setParam("pageSize", "2000"); setParam("page", "1");
		 * setParam("style", "28002473");
		 * 
		 * setParamFlag(6);
		 */

		initiaHttpClient();
		
		log.debug("stockIndustry crawler initialization end");
	}

	@Override
	public void start() {
		log.warn("crawler started");

		crawIndustryParam();

		crawDeepStockIndus();

		log.warn("crawler ended");

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * Description
	 */
	public List<StockIndustry> getAllList(int recordNo,int pageSize){
		return stockIndustryDao.getAllList(recordNo, pageSize);
	}
	
	public Long getTotalRecords(){
		return stockIndustryDao.getSizes();
	}
	
	/**
	 * Description 根据seed爬取dynamicUrl的请求参数
	 */
	private void crawIndustryParam() {
		crawIndustryParam(CrawlerUtil.GBK);
	}

	private void crawIndustryParam(String charSet) {
		log.info("craw industryParam start");
		HttpGet httpGet = new HttpGet(seed);
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();

			if (statusLine.getStatusCode() != HttpStatus.SC_OK)
				log.error("Request failed : " + statusLine);
			else {
				String html = EntityUtils.toString(response.getEntity(),
						charSet);

				/*
				 * int statusCode = client.executeMethod(method);
				 * 
				 * if (statusCode != HttpStatus.SC_OK)
				 * log.error("Method failed : " + method.getStatusLine());
				 * 
				 * String html = method.getResponseBodyAsString(); try {
				 * CrawlerUtil.changeCharset(html, method.getResponseCharSet(),
				 * CrawlerUtil.UTF_8); } catch (UnsupportedEncodingException
				 * uee) { log.error(uee); }
				 */

				Document doc = Jsoup.parse(html);

				Element indusEle = doc.getElementsByClass("node-sub-sub")
						.last();
				Elements a = indusEle.getElementsByTag("a");
				int aIndex = a.size();
				indusKeyValue = new String[aIndex][2];
				for (int i = 0; i < aIndex; i++) {
					Element ele = a.get(i);
					indusKeyValue[i][0] = ele.attr("href").substring(10, 18);
					indusKeyValue[i][1] = ele.child(0).text();
				}
				log.debug("dynamic key-value output begin");
				log.debug(Arrays.deepToString(indusKeyValue));
				log.debug("dynamic key-value output end");
				log.warn("craw industryParam end");
			}
		} catch (ClientProtocolException cpe) {
			log.error("Fatal protocol violation : " + cpe);
		} catch (IOException ioe) {
			log.error("Fatal IO error : " + ioe);
		} catch (Exception e) {
			log.error("Fatal transport error: " + e.getMessage());
		} finally {
			httpGet.releaseConnection();
		}
	}

	/**
	 * Description 根据dynamicUrl和请求参数爬取行业股票并存储数据库
	 */
	private void crawDeepStockIndus() {
		crawDeepStockIndus(CrawlerUtil.GBK);
	}

	private void crawDeepStockIndus(String charSet) {

		log.warn("craw crawDeepStockIndus start");
		int len = indusKeyValue.length;
		NameValuePair[][] stocks = new BasicNameValuePair[len][]; // 行业对应股票;一维-对应行业所有股票数组,二维-每只股票,name-股票代码,value-股票名称
		HttpGet httpGet = null;

		for (int i = 0; i < len; i++) {
			try {
				String nIndustry = indusKeyValue[i][1];
				log.warn("craw stocks from " + nIndustry + " begin");
				uriBuilder.setParameter("style", indusKeyValue[i][0]);
				httpGet = new HttpGet(uriBuilder.build());

				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();

				if (statusLine.getStatusCode() != HttpStatus.SC_OK)
					log.error("request failed : " + statusLine);
				else {

					String html = EntityUtils.toString(response.getEntity(),
							charSet);

					html = html.substring(html.indexOf("[") + 1,
							html.indexOf("]"));
					log.debug(html);

					String[] tmpA = html.split("\"");
					int subLen = tmpA.length / 2;
					stocks[i] = new BasicNameValuePair[subLen];
					for (int j = 0; j < subLen; j++) {
						String[] tmpB = tmpA[j * 2 + 1].split(",");
						stocks[i][j] = new BasicNameValuePair(tmpB[1], tmpB[2]);

						saveToDB(stocks[i][j].getName(),
								stocks[i][j].getValue(), nIndustry);
					}
					log.info("craw crawDeepStockIndus end");
				}
			} catch (ClientProtocolException he) {
				log.error("Fatal protocol violation : " + he);
			} catch (IOException ioe) {
				log.error("Fatal IO error : " + ioe);
			} catch (URISyntaxException uso) {
				log.error("Fatal URISyntax error : " + uso);
			} catch (Exception e) {
				log.error("Fatal transport error: " + e);
			} finally {
				if (httpGet != null)
					httpGet.releaseConnection();
			}

		}

	}

	/**
	 * 存数据库
	 * 
	 * @param stockID
	 *            股票代码
	 * @param stockName
	 *            股票名称
	 * @param industryName
	 *            行业名称
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	private void saveToDB(String stockID, String stockName, String industryName) {
		try {
			StockIndustry si = new StockIndustry(stockID, industryName);
			si.setStockName(stockName);
			log.debug("write " + si.toString() + " to DB begin");
			stockIndustryDao.save(si);
			log.debug("write " + si.toString() + " to DB end");
		} catch (Exception e) {
			log.error(e);
		}
	}

	public String getDynamicURL() {
		return dynamicURL;
	}

	public void setDynamicURL(String dynamicURL) {
		this.dynamicURL = dynamicURL;
	}

	public StockIndustryDao getStockIndustryDao() {
		return stockIndustryDao;
	}

	public void setStockIndustryDao(StockIndustryDao stockIndustryDao) {
		this.stockIndustryDao = stockIndustryDao;
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");
		CrawService ser = (CrawService) context.getBean("stockIndustryService");
		ser.start();
		context.close();
	}

	@Autowired
	private StockIndustryDao stockIndustryDao;

	private String dynamicURL; // 页面第二个url

	private String[][] indusKeyValue; // 行业名称和行业代码;第二维中第0列为行业代码，第1列为行业名称

	// private NameValuePair[][] stocks; //
	// 行业对应股票;一维-对应行业所有股票数组,二维-每只股票,name-股票代码,value-股票名称
}
