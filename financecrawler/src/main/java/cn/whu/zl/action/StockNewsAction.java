package cn.whu.zl.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import cn.whu.zl.service.CrawService;

@Component("stockNewsAction")
public class StockNewsAction {
	private static final Logger log = Logger.getLogger(StockNewsAction.class
			.getName());

	public void excuteError() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");

		CrawService thSer = (CrawService) context.getBean("stockNewsSer");

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
	}

	public void excute() {
		log.warn("stockNewsAction excute begin");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");

		CrawService thSer = (CrawService) context.getBean("stockNewsSer");

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

		log.warn("stockNewsAction excute end");
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

	private int sizePerList = 20;
	private List<String> errorList;
}
