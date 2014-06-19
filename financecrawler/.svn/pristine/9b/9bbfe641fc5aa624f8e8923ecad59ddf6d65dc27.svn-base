package cn.whu.zl.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import cn.whu.zl.service.CrawService;

@Component("mysqlToExcelAction")
public class MysqlToExcelAction {
	private static final Logger log = Logger.getLogger(TransactionHistoryAction.class.getName());
	
	private int sizePerList = 40;
	private List<String> errorList;
	CrawService[] mteSerArr;
	
	public void excuteError(){
		log.info("mysqlToExcelAction excuteError begin");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");
		
		CrawService mteSer = (CrawService) context
				.getBean("MysqlToExcelSer");
		
		mteSer.setCodeList(errorList);
		
		mteSer.start();
		
		context.close();
		
		log.info("mysqlToExcelAction excuteError end");
	}
	
	public void excute(){
		
		log.info("mysqlToExcelAction excute begin");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");

		CrawService mteSer = (CrawService) context
				.getBean("MysqlToExcelSer");

		List<String> codeList = mteSer.getCodeList();
		

		int total = codeList.size();


		int threadNum = total / sizePerList;

		if (total % sizePerList > 0) {
			threadNum += 1;
		
		}

		mteSerArr = new CrawService[threadNum];

		List<String> thisCodeList = null;

		for (int i = 0; i < threadNum - 1; i++) {
			mteSerArr[i] = (CrawService) context
					.getBean("MysqlToExcelSer");
			thisCodeList = codeList.subList(0 + i * sizePerList, (i + 1)
					* sizePerList);
			mteSerArr[i].setCodeList(thisCodeList);
		}

		mteSerArr[threadNum - 1] = (CrawService) context
				.getBean("MysqlToExcelSer");

		thisCodeList = codeList.subList((threadNum - 1) * sizePerList,
				codeList.size());

		mteSerArr[threadNum - 1].setCodeList(thisCodeList);

		
		for (int i = 0; i < threadNum; i++)
			mteSerArr[i].start();
		
		context.close();

		log.info("mysqlToExcelAction excute end");
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
	

}
