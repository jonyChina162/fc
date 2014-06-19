package cn.whu.zl.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import cn.whu.zl.service.CrawService;

@Component("transactionHistoryAction")
public class TransactionHistoryAction {
	private static final Logger log = Logger.getLogger(TransactionHistoryAction.class.getName());
	
	public void excuteError(){
		log.info("transactionHistoryAction excuteError begin");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");
		
		CrawService thSer = (CrawService) context
				.getBean("transactionHistorySer");
		
		thSer.setCodeList(errorList);
		
		thSer.start();
		
		context.close();
		
		log.info("transactionHistoryAction excuteError end");
	}
	
	public void excute() {
		log.info("transactionHistoryAction excute begin");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");

		CrawService thSer = (CrawService) context
				.getBean("transactionHistorySer");

		List<String> codeList = thSer.getCodeList();
		
//		codeList = new ArrayList<String>(Arrays.asList("000001","000002"));

		int total = codeList.size();

		// int isWhole = 1;

		int threadNum = total / sizePerList;

		if (total % sizePerList > 0) {
			threadNum += 1;
			// isWhole = 0;
		}

		thSerArr = new CrawService[threadNum];

		List<String> thisCodeList = null;

		for (int i = 0; i < threadNum - 1; i++) {
			thSerArr[i] = (CrawService) context
					.getBean("transactionHistorySer");
			thisCodeList = codeList.subList(0 + i * sizePerList, (i + 1)
					* sizePerList);
			thSerArr[i].setCodeList(thisCodeList);
//			thSerArr[i].setMinCode("600000");
		}

		thSerArr[threadNum - 1] = (CrawService) context
				.getBean("transactionHistorySer");

		thisCodeList = codeList.subList((threadNum - 1) * sizePerList,
				codeList.size());

		thSerArr[threadNum - 1].setCodeList(thisCodeList);
//		thSerArr[threadNum - 1].setMinCode("600000");

		

		// 还不会多线程
//		ExecutorService es = Executors.newFixedThreadPool(threadNum);
//
//		for (int i = 0; i < threadNum; i++)
//			es.execute(thSerArr[i]);
//		es.shutdown();

		for (int i = 0; i < threadNum; i++)
			thSerArr[i].start();
		
		context.close();

		log.info("transactionHistoryAction excute end");
	}

	public int getSizePerList() {
		return sizePerList;
	}

	public void setSizePerList(int sizePerList) {
		this.sizePerList = sizePerList;
	}
	
	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	private int sizePerList = 40;
	private List<String> errorList;
	CrawService[] thSerArr;
}
