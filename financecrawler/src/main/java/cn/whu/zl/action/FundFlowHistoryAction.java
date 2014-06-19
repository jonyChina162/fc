package cn.whu.zl.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import cn.whu.zl.service.CrawService;

@Component("fundFlowHistoryAction")
public class FundFlowHistoryAction {
	private static final Logger log = Logger.getLogger(FundFlowHistoryAction.class
			.getName());
	
	public void excuteError() {
		log.warn("fundFlowHistoryAction excuteError begin");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");

		CrawService thSer = (CrawService) context.getBean("fundFlowHistorySer");

		thSer.setCodeList(errorList);
		
		List<String> codeList = thSer.getCodeList();

		int total = codeList.size();

		int threadNum = total / sizePerList;

		if (total % sizePerList > 0) {
			threadNum += 1;
		}

		List<String> thisCodeList = null;

		for (int i = 0; i < threadNum - 2; i++) {
			thisCodeList = codeList.subList(0 + i * sizePerList, (i + 1)
					* sizePerList);
			thSer.setCodeList(thisCodeList);
			thSer.start();
		}

		thisCodeList = codeList.subList((threadNum - 1) * sizePerList,
				codeList.size());

		thSer.setCodeList(thisCodeList);

		thisCodeList = null;

		thSer.start();
		
		context.close();

		log.warn("fundFlowHistoryAction excuteError end");
	}
	

	public void excute() {
		log.warn("fundFlowHistoryAction excute begin");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");

		CrawService thSer = (CrawService) context.getBean("fundFlowHistorySer");

		List<String> codeList = thSer.getCodeList();

		int total = codeList.size();

		int threadNum = total / sizePerList;

		if (total % sizePerList > 0) {
			threadNum += 1;
		}

		List<String> thisCodeList = null;

		for (int i = 0; i < threadNum - 2; i++) {
			thisCodeList = codeList.subList(0 + i * sizePerList, (i + 1)
					* sizePerList);
			thSer.setCodeList(thisCodeList);
			thSer.start();
		}

		thisCodeList = codeList.subList((threadNum - 1) * sizePerList,
				codeList.size());

		thSer.setCodeList(thisCodeList);

		thisCodeList = null;

		thSer.start();
		
		context.close();

		log.warn("fundFlowHistoryAction excute end");
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



	private int sizePerList = 100;
	private List<String> errorList;
}
