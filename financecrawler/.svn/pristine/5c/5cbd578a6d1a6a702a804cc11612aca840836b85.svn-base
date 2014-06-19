package cn.whu.zl.service;

import static cn.whu.zl.util.CrawlerUtil.GBK;
import static cn.whu.zl.util.CrawlerUtil.strToFloat;
import static cn.whu.zl.util.CrawlerUtil.strToInt;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.FinancialReportDao;
import cn.whu.zl.dao.StockIndustryDao;
import cn.whu.zl.entity.FinancialReport;
import cn.whu.zl.entity.StockDataPK;
import cn.whu.zl.entity.TransactionHistory;

@Service("financialReportSer")
@Transactional
public class FinancialReportService extends BasicCrawServiceImp {
	private static final Logger log = Logger
			.getLogger(FinancialReportService.class.getName());

	public FinancialReportService() {
		log.debug("FinancialReportService crawler initialization begin");

		initiaHttpClient();

		log.debug("FinancialReportService crawler initialization end");
	}

	@Override
	public void start() {
		log.warn("StockReportService begin");
		crawFinancialReport();
		log.warn("StockReportService end");
	}
	
	public List<FinancialReport> getAllList(int recordNo,int pageSize){
		return financialReportDao.getAllList(recordNo, pageSize);
	}
	
	public Long getTotalRecords(){
		return financialReportDao.getSizes();
	}

	private void crawFinancialReport() {
		List<String> codes = stockIndustryDao.getAllStockID();
		for (String code : codes) {
			try {
				crawFinancialReport(code, GBK);
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
	}

	private void crawFinancialReport(String code, String charSet)
			throws URISyntaxException, ClientProtocolException, IOException,
			ParseException {
		log.warn("craw " + code + "th financialReport begin");

		HttpGet httpGet = null;
		seed = preURL + code + sufURL;
		try {
			httpGet = new HttpGet(seed);
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
			String html = EntityUtils.toString(entity, GBK);
			EntityUtils.consume(entity);
			String[] rows = html.trim().split("\r\n");
			String[][] tmpArr = new String[22][];
			String[][] datas = new String[22][];
			for (int i = 0; i < 22; i++)
				tmpArr[i] = new String(rows[i]).split(",");

			int len = tmpArr[0].length;

			for (int i = 0; i < 22; i++) {
				datas[i] = new String[len];
				datas[i] = Arrays.copyOf(tmpArr[i], len);
			}

			FinancialReport[] fps = new FinancialReport[len - 2];
			StockDataPK[] sdPKs = new StockDataPK[len - 2];
			for (int i = 0; i < len - 2; i++) {
				sdPKs[i] = new StockDataPK();
				fps[i] = new FinancialReport();
				sdPKs[i].setStockID(code);
				sdPKs[i].setDate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(new String(datas[0][i + 1])));

				fps[i].setSdPK(sdPKs[i]);

				fps[i].setIncome(strToInt(datas[1][i + 1]));
				fps[i].setCost(strToInt(datas[2][i + 1]));
				fps[i].setOperProfit(strToInt(datas[3][i + 1]));
				fps[i].setProfitAmount(strToInt(datas[4][i + 1]));
				fps[i].setIncomeTax(strToInt(datas[5][i + 1]));
				fps[i].setNetProfit(strToInt(datas[6][i + 1]));
				fps[i].setEPS(strToFloat(datas[7][i + 1]));
				fps[i].setMonCap(strToInt(datas[8][i + 1]));
				fps[i].setCurrentAsset(strToInt(datas[9][i + 1]));
				fps[i].setFixAsset(strToInt(datas[10][i + 1]));
				fps[i].setTotalAsset(strToInt(datas[11][i + 1]));
				fps[i].setCurrentLiab(strToInt(datas[12][i + 1]));
				fps[i].setFixLiab(strToInt(datas[13][i + 1]));
				fps[i].setTotalLiab(strToInt(datas[14][i + 1]));
				fps[i].setTotalEquity(strToInt(datas[15][i + 1]));
				fps[i].setCashBalance(strToInt(datas[16][i + 1]));
				fps[i].setBusActFund(strToInt(datas[17][i + 1]));
				fps[i].setInvestActFund(strToInt(datas[18][i + 1]));
				fps[i].setFinanceActFund(strToInt(datas[19][i + 1]));
				fps[i].setCashIncrease(strToInt(datas[20][i + 1]));
				fps[i].setFinalCashBalance(strToInt(datas[21][i + 1]));
			}

			saveListToDB(fps);
		}

		/*
		 * } catch (URISyntaxException use) { throw use; }
		 */catch (ClientProtocolException cpe) {
			throw cpe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (httpGet != null)
				httpGet.releaseConnection();
		}

		log.warn("craw " + code + "th financialReport end");
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void saveToDB(FinancialReport fp) {
		financialReportDao.save(fp);
		/*
		 * stockReportDao.flush(); sp = null;
		 */
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void saveListToDB(FinancialReport[] fps) {
		for (FinancialReport fp : fps)
			saveToDB(fp);
		financialReportDao.flush();
		for (@SuppressWarnings("unused")
		FinancialReport fp : fps)
			fp = null;
		fps = null;
	}

	@Autowired
	FinancialReportDao financialReportDao;

	@Autowired
	private StockIndustryDao stockIndustryDao;

	private final String preURL = "http://quotes.money.163.com/service/cwbbzy_";
	private final String sufURL = ".html";
}
