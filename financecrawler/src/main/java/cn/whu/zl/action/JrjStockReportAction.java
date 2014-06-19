package cn.whu.zl.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import cn.whu.zl.service.CrawService;

@Component("jrjStockReportAction")
public class JrjStockReportAction {
	private static final Logger log = Logger.getLogger(JrjStockReportAction.class.getName());
	
	private int sizePerList = 40;
	
	CrawService[] jrjSerArr;
	
	private List<String> errorList;
	
	public void excute() {
		log.info("jrjStockReportAction excute begin");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");

		CrawService jrjSer = (CrawService) context
				.getBean("jrjStockReportSer");

		List<String> codeList = jrjSer.getCodeList();

		int total = codeList.size();

		int threadNum = total / sizePerList;

		if (total % sizePerList > 0) {
			threadNum += 1;
		}

		jrjSerArr = new CrawService[threadNum];

		List<String> thisCodeList = null;

		for (int i = 0; i < threadNum - 1; i++) {
			jrjSerArr[i] = (CrawService) context
					.getBean("jrjStockReportSer");
			thisCodeList = codeList.subList(0 + i * sizePerList, (i + 1)
					* sizePerList);
			jrjSerArr[i].setCodeList(thisCodeList);
		}

		jrjSerArr[threadNum - 1] = (CrawService) context
				.getBean("jrjStockReportSer");

		thisCodeList = codeList.subList((threadNum - 1) * sizePerList,
				codeList.size());

		jrjSerArr[threadNum - 1].setCodeList(thisCodeList);


    	for (int i = 0; i < threadNum; i++)
    	{
    		log.info("service "+ i + "start");
    		log.info("jrjSerArr" + i + " contains " + jrjSerArr[i].getCodeList());
    		jrjSerArr[i].start();
    		log.info("service "+ i + "end");
    	}
			
			
    	
//		jrjSerArr[0].start();
		
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

}
