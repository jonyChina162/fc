package cn.whu.zl.service;

import static cn.whu.zl.util.CrawlerUtil.strToFloat;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.whu.zl.dao.NcTableDao;
import cn.whu.zl.dao.StockIndustryDao;
import cn.whu.zl.entity.NcTable;
import cn.whu.zl.entity.StockDataPK;

@Service("nctableSer")
// @Transactional
public class NcTableService extends BasicCrawServiceImp {
	private static final Logger log = Logger.getLogger(NcTableService.class
			.getName());

	public NcTableService() {
		log.debug("nctableSer init begin");
	}

	@Override
	public void start() {
		try{
			crawAll();
		log.info("this nctable task complete");
		}catch(Exception e){
			log.error(e);
		}
	}

	public void crawAll() {
		List<String> codeList = stockIndustryDao.getAllStockID();
//		codeList = new ArrayList<String>();
//		codeList.add("300033");
//		codeList.add("600817");
		clientFactory = new SimpleClientHttpRequestFactory();
		clientFactory.setConnectTimeout(10000);
		clientFactory.setReadTimeout(10000);
		List<String> errList = new ArrayList<String>();
		// 牛叉评估不能诊自己的股票
		codeList.remove("300033");
		for (String code : codeList)
			try {
				craw(code);
			} catch (Exception e) {
				errList.add(code);
				log.error(e);
				e.printStackTrace();
			}

		log.info(errList);
	};

	public void craw(String code) throws ParseException {
		log.info("craw " + code + " nctable begin");

		RestTemplate rest = new RestTemplate(clientFactory);

		HttpEntity<String> entity = null;

		String html = null;

		int retry = 0;

		while (retry < 10) {
			try {
				entity = rest.getForEntity(preURL + code, String.class);
				if (entity == null)
					throw new NullPointerException("entity is empty");

				html = entity.getBody();

				if (html == null || html.length() == 0)
					throw new NullPointerException("html is empty");

				break;
			} catch (Exception e) {
				log.info("retry : " + retry);
				retry++;
			}
		}

		String tmpSen = "";

		NcTable nc = new NcTable();

		StockDataPK sdPk = new StockDataPK();

		int from = 0, to = 0;

		sdPk.setStockID(code);
		
//		log.info(html);

		// 利用Jsoup枯燥的解析过程，查看网页源码一一对照
		Document doc = Jsoup.parse(html);

		tmpSen = doc.getElementsByClass("stockname").first().text();

		nc.setStockName(tmpSen.substring(0, tmpSen.indexOf("（")));

		tmpSen = doc.getElementsByClass("stocktotal").first().text();

		nc.setNcGrade(strToFloat(tmpSen.substring(tmpSen.indexOf("：") + 1,
				tmpSen.indexOf("分"))));

		nc.setNcGradePer(strToFloat(tmpSen.substring(tmpSen.indexOf("打败了") + 3,
				tmpSen.indexOf("%"))));

		tmpSen = doc.getElementsByClass("value_bar").first()
				.getElementsByClass("cur").first().text();

		nc.setStoRate(tmpSen);

		tmpSen = doc.getElementsByClass("cnt").first()
				.getElementsByTag("strong").first().text();

		nc.setAvgCost(strToFloat(tmpSen.substring(0, tmpSen.length() - 1)));

		tmpSen = doc.getElementsByClass("value_info").first()
				.getElementsByClass("long").first().text();

		from = tmpSen.indexOf("占流通A股");

		if (from >= 0)
			nc.setIndusInAPer(strToFloat(tmpSen.substring(from + 5,
					tmpSen.indexOf("%"))));
		else
			nc.setIndusInAPer(-1.0f);

		tmpSen = doc.getElementsByClass("date").first().text();

		tmpSen = tmpSen.substring(tmpSen.indexOf("诊断日期") + 5,
				tmpSen.length() - 1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

		long tmpTime = sdf.parse(tmpSen).getTime();

		Timestamp time = new Timestamp(tmpTime);

		nc.setTime(time);

		Date date = new Date(tmpTime);

		sdPk.setDate(date);

		nc.setSdPK(sdPk);
		// 技术面

		Element thisEle = doc.getElementById("nav_technical");

		Element gel = thisEle.getElementsByClass("title").first();

		nc.settGrade(strToFloat(gel.getElementsByTag("em").text()));

		tmpSen = thisEle.getElementsByClass("gray").first().text();

		nc.settGradePer(strToFloat(tmpSen.substring(tmpSen.indexOf("打败了") + 3,
				tmpSen.indexOf("%"))));

		tmpSen = thisEle.getElementsByClass("content").first().text();

		tmpSen = tmpSen
				.substring(tmpSen.indexOf("股价") + 2, tmpSen.indexOf("。"));

		nc.setPriComCost(tmpSen);

		tmpSen = thisEle.getElementsByClass("special_border").first()
				.getElementsByClass("hd2").first().text();

		tmpSen = tmpSen.substring(tmpSen.indexOf("，") + 1);

		nc.setStoComIndex(tmpSen.substring(tmpSen.indexOf("该股走势"),
				tmpSen.indexOf("，")));

		nc.setStoComIndus(tmpSen.substring(tmpSen.indexOf("，") + 1,
				tmpSen.length() - 1));

		tmpSen = thisEle.getElementsByClass("indexStat").get(1)
				.getElementsByClass("hd2").first().text();

		from = tmpSen.indexOf("压力位为");
		if (from >= 0)
			nc.setPsePrice(strToFloat(tmpSen.substring(from + 4,
					tmpSen.indexOf("元"))));
		else
			nc.setPsePrice(0.0f);

		from = tmpSen.indexOf("支撑位为");
		if (from >= 0)
			nc.setStdPrice(strToFloat(tmpSen.substring(from + 4,
					tmpSen.length() - 2)));

		else
			nc.setStdPrice(0.0f);
		// 资金面

		thisEle = doc.getElementById("nav_funds");

		gel = thisEle.getElementsByClass("title").first();

		nc.setmGrade(strToFloat(gel.getElementsByTag("em").text()));

		tmpSen = thisEle.getElementsByClass("gray").first().text();

		nc.setmGradePer(strToFloat(tmpSen.substring(tmpSen.indexOf("打败了") + 3,
				tmpSen.indexOf("%"))));

		tmpSen = thisEle.getElementsByClass("hd2").get(1).text();

		from = tmpSen.indexOf("资金");

		nc.setmFlowTo(tmpSen.substring(from + 2, tmpSen.indexOf("，", from)));

		to = tmpSen.indexOf("平均水平");

		if (to >= 0)
			nc.setmFlowComIndus(tmpSen.substring(tmpSen.indexOf("，", from) + 1,
					to));
		else
			nc.setmFlowComIndus("未知");

		float a = 0.0f;float b = 0.0f;
		a = strToFloat(thisEle.getElementById("position_value_rate").val());
		b = strToFloat(thisEle.getElementById("position_value_last").val());
//		tmpSen = thisEle.getElementById("hotbar").getElementsByClass("current")
//				.first().text();

		nc.setConDegree(calConDegree(a, b));

		// 消息面

		thisEle = doc.getElementById("nav_message");

		gel = thisEle.getElementsByClass("title").first();

		nc.setImGrade(strToFloat(gel.getElementsByTag("em").text()));

		tmpSen = thisEle.getElementsByClass("gray").first().text();

		nc.setImGradePer(strToFloat(tmpSen.substring(tmpSen.indexOf("打败了") + 3,
				tmpSen.indexOf("%"))));

		// 行业面

		thisEle = doc.getElementById("nav_trade");

		gel = thisEle.getElementsByClass("title").first();

		nc.setInGrade(strToFloat(gel.getElementsByTag("em").text()));

		tmpSen = thisEle.getElementsByClass("gray").first().text();

		nc.setInGradePer(strToFloat(tmpSen.substring(tmpSen.indexOf("打败了") + 3,
				tmpSen.indexOf("%"))));

		tmpSen = thisEle.getElementsByClass("content").first().text();

		nc.setInTrend(tmpSen.substring(tmpSen.indexOf("行业") + 2,
				tmpSen.indexOf("。")));

		if (tmpSen.contains("无任何评级"))
			nc.setInRate("无任何评级");
		else if (tmpSen.contains("不明显"))
			nc.setInRate("不明显");
		else
			nc.setInRate(tmpSen.substring(tmpSen.indexOf("机构评级") + 5,
					tmpSen.indexOf("为主")));

		tmpSen = thisEle.getElementById("tradeTendName").text();

		nc.setIndusName(tmpSen.substring(tmpSen.indexOf(",") + 1));

		// 基本面

		thisEle = doc.getElementById("nav_basic");

		gel = thisEle.getElementsByClass("title").first();

		nc.setInGrade(strToFloat(gel.getElementsByTag("em").text()));

		tmpSen = thisEle.getElementsByClass("gray").first().text();

		nc.setInGradePer(strToFloat(tmpSen.substring(tmpSen.indexOf("打败了") + 3,
				tmpSen.indexOf("%"))));

		tmpSen = thisEle.getElementsByClass("hd2").get(6).text();

		if (tmpSen.contains("无任何评级"))
			nc.setInStoRate("无任何评级");
		else if (tmpSen.contains("不明显"))
			nc.setInStoRate("不明显");
		else
			nc.setInStoRate(tmpSen.substring(tmpSen.indexOf("机构评级") + 5,
					tmpSen.indexOf("为主")));

		tmpSen = thisEle.getElementsByClass("hd2").get(7).text();

		tmpSen = tmpSen.substring(tmpSen.indexOf("前一年") + 3,
				tmpSen.indexOf("，"));
		
		if(tmpSen.contains("增长"))
			tmpSen = tmpSen.replaceAll("增长", "+");
		else if(tmpSen.contains("减少"))
			tmpSen = tmpSen.replaceAll("减少", "-");
		else if(tmpSen.contains("相同"))
			tmpSen = tmpSen.replaceAll("相同", "0%");
		else
			tmpSen = "-0%";
		
		nc.setInProfor(tmpSen);

		saveToNcTable(nc);

		log.debug("craw " + code + " nctable end");
	}
	
	private String calConDegree(float a,float b){
		//计算过程在 http://s.thsi.cn/combo?js/doctor/&flash.min.20120731.js&index.20130703.min.js&search.min.20120731.js&score.20130402.js 里
		String[] conStr = {"没有控盘","轻度控盘","中度控盘","高度控盘"};
		int index = 0;
		if(a < 0.15 + 1e-10){
			index = 0;
		}else if(a >= 0.15 - 1e-10 && a <0.4+1e-10){
			index = 1;
		}else if(a >= 0.4 - 1e-10 && a <0.7+1e-10){
			index = 2;
		}else {
			index = 3;
		}
			
		return conStr[index];
				
	}

	private void saveToNcTable(NcTable nc) {
		// 注意这个性能～究竟save好还是update好
		nctableDao.update(nc);
	}

	private final String preURL = "http://doctor.10jqka.com.cn/";
	private SimpleClientHttpRequestFactory clientFactory;

	@Autowired
	private StockIndustryDao stockIndustryDao;

	@Autowired
	private NcTableDao nctableDao;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");
		CrawService cs = (CrawService) context.getBean("nctableSer");

		cs.start();

		cs.close();

	}
}
