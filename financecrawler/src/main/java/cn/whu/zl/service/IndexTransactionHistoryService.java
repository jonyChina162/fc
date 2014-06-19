package cn.whu.zl.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.IndexTransactionHistoryDao;
import cn.whu.zl.dao.StockIndexDao;
import cn.whu.zl.entity.IndexTransactionHistory;
import cn.whu.zl.entity.StockDataPK;
import cn.whu.zl.entity.TransactionHistory;
import static cn.whu.zl.util.CrawlerUtil.GBK;
import static cn.whu.zl.util.CrawlerUtil.strToFloat;
import static cn.whu.zl.util.CrawlerUtil.strToLong;

@Transactional
@Service("IndexTransactionHistorySer")
/**
 * @author B506-13-1
 * 
 */
public class IndexTransactionHistoryService extends BasicCrawServiceImp {
	
	private static final Logger log = Logger.getLogger(IndexTransactionHistory.class.getName());
	@Autowired
	private IndexTransactionHistoryDao indexTransactionHistorydao;
	@Autowired
	private StockIndexDao stockIndexDao;
	private List<String> codelist = null;
	private final String THRESHOLD_CODE = "300000";
	
	//constructor 
	public IndexTransactionHistoryService(){
		this("19900101",new SimpleDateFormat("yyyyMMdd").format(new Date()));
	}
	
	public IndexTransactionHistoryService(String start,String end){
		log.debug("IndexTransactionHistoryService initialization start");
		
		setSeed("http://quotes.money.163.com/service/chddata.html");
		
		try {
			uriBuilder = new URIBuilder(seed);
		} catch (URISyntaxException uso) {
			log.error("Fatal error : " + uso);
			Runtime.getRuntime().exit(-1);
		}
		uriBuilder.setParameter("start", start).setParameter("end", end).setParameter(
				"fields","TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP");
		
		initiaHttpClient();
		
		log.debug("IndexTransactionHistory initialization end");
	}
	
	@Override
	public void start(){
		//list
		List<String> list = new ArrayList<String>();
		list.add("399005");
		list.add("399006");
		setCodeList(list);
		crawFromDownload();
	}
	
	@Override
	public void run(){
		crawFromDownload();
	}
	
	@Override
	public void close(){
		
	}
	
	@Override
	public List<String> getCodeList() {
		if(codelist==null||codelist.size()==0){
			codelist= stockIndexDao.getAllStockID();
		}
		return codelist;
	}
	
	@Override
	public void setCodeList(List<String> codelist) {
		this.codelist = codelist;
		setMaxCode(codelist.get(codelist.size() - 1));
		setMinCode(codelist.get(0));
	}
	
	
	public List<IndexTransactionHistory> getAllList(int recordNo,int pageSize){
		return indexTransactionHistorydao.getAllList(recordNo,pageSize);
	}
	
	public long getTotalRecords(){
		return indexTransactionHistorydao.getSizes();
	}
	
	private void crawFromDownload(){
		log.warn("craw Index Transaction History begin");
		
		for(String code:getCodeList()){
			if (code.compareTo(getMinCode()) >= 0 && code.compareTo(getMaxCode()) <= 0){
				try{
					crawFromDownload(code,GBK);
				}catch (ClientProtocolException cpe) {
					log.error(code + " th record failed!");
					log.error("Fatal protocol violation : " + cpe);
				} catch (ParseException pe) {
					log.error(code + " th record failed!");
					log.error("Fatal Parse Error : " + pe);
				} catch (IOException ioe) {
					log.error(code + " th record failed!");
					log.error("Fatal io error : " + ioe);
				} catch (Exception e) {
					log.error(code + " th record failed!");
					log.error("Fatal transport error: " + e);
				}
			}
				
		}
		log.warn("craw Index Transaction History end");
		
	}
	
	private void crawFromDownload(String code,String charSet) throws ParseException, Exception{
		log.warn("craw"+ code + "Index Transaction History begin");
		if(code.compareTo(THRESHOLD_CODE)<0)
			code = "0" + code;
		else
			code = "1" + code;
		HttpGet httpget=null;
		BufferedReader reader=null;
		try{
			uriBuilder.setParameter("code", code);
			httpget=new HttpGet(uriBuilder.build());
			HttpResponse response = client.execute(httpget);
			StatusLine status = response.getStatusLine();
			int reTry = 0;
			while (status.getStatusCode() != HttpStatus.SC_OK) {
				reTry++;
				
				if (reTry == 10) {
					log.error("Request failed : " + status);
					throw new ClientProtocolException(status.toString());
				}
				httpget.releaseConnection();
				response = client.execute(httpget);
				status = response.getStatusLine();
				
			}
			
			HttpEntity entity=response.getEntity();
			reader=new BufferedReader(new InputStreamReader(entity.getContent(), charSet));
			
			String temp=reader.readLine();
			int count=0;
		
			List<IndexTransactionHistory> ith =new ArrayList<>();
			while ((temp = reader.readLine()) != null){
				log.debug(temp);
				ith.add(strToIth(temp));
				count++;
			}
			
			if(count>0)
				saveListToDB(ith);
		System.gc();
			log.warn("The number of records write to DB is : " + count);
			reader.close();
		}catch (ClientProtocolException cpe) {
			throw cpe;
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (null != httpget)
				httpget.releaseConnection();
			if (null != reader)
				reader = null;
		}
		
	}
	
	private IndexTransactionHistory strToIth(String str) throws ParseException {
		String[] values = str.split(",");
		IndexTransactionHistory ith = new IndexTransactionHistory.Builder(new StockDataPK(
				new String(values[1]).substring(new String(values[1])
						.indexOf("'") + 1), (Date) new SimpleDateFormat(
						"yyyy-MM-dd").parse(values[0])))
				.stockName(new String(values[2]))
				.cloPrice(strToFloat(values[3]))
				.highPrice(strToFloat(values[4]))
				.lowPrice(strToFloat(values[5]))
				.openPrice(strToFloat(values[6]))
				.beforeClo(strToFloat(values[7]))
				.changeAmount(strToFloat(values[8]))
				.changeRatio(strToFloat(values[9]))
				.turnover(strToLong(values[10]))
				.turnoverAmount(strToFloat(values[11]))
				.build();

		return ith;
	}

	@Transactional
	private void saveToDB(IndexTransactionHistory ith) {
		indexTransactionHistorydao.save(ith);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private void saveListToDB(List<IndexTransactionHistory> iths) {
		for (IndexTransactionHistory th : iths) {
			indexTransactionHistorydao.save(th);
			th = null;
		}
		// transanctionHistoryDao.flush();
		iths = null;
	}

//	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

//	}

}
