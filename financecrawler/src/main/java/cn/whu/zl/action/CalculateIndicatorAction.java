package cn.whu.zl.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import cn.whu.zl.service.CrawService;

@Component("calculateIndicatorAction")
public class CalculateIndicatorAction {
	private static final Logger log = Logger.getLogger(CalculateIndicatorAction.class.getName());
	
	public void excute() {
		log.info("CalculateIndicatorAction excute begin");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");
		
		CrawService calSer = (CrawService) context
				.getBean("calculateIndicatorSer");

		List<String> codeList = calSer.getCodeList();
		
//		codeList = new ArrayList<String>(Arrays.asList("000001","000002"));

		int total = codeList.size();

		// int isWhole = 1;

		int threadNum = total / sizePerList;

		if (total % sizePerList > 0) {
			threadNum += 1;
			// isWhole = 0;
		}

		calSerArr = new CrawService[threadNum];

		List<String> thisCodeList = null;

		for (int i = 0; i < threadNum - 1; i++) {
			calSerArr[i] = (CrawService) context
					.getBean("calculateIndicatorSer");
			thisCodeList = codeList.subList(0 + i * sizePerList, (i + 1)
					* sizePerList);
			calSerArr[i].setCodeList(thisCodeList);
		}

		calSerArr[threadNum - 1] = (CrawService) context
				.getBean("calculateIndicatorSer");

		thisCodeList = codeList.subList((threadNum - 1) * sizePerList,
				codeList.size());

		calSerArr[threadNum - 1].setCodeList(thisCodeList);

		

		// 还不会多线程
//		ExecutorService es = Executors.newFixedThreadPool(threadNum);
//
//		for (int i = 0; i < threadNum; i++)
//			es.execute(thSerArr[i]);
//		es.shutdown();
		
//		thSerArr[0].setCodeList(Arrays.asList("000878"));

		log.info("ini service nums : " + threadNum);
		for (int i = 0; i < threadNum; i++){
			log.info(i + " st service begin");
			calSerArr[i].start();
			calSerArr[i] = null;
//			break;
		}
		
//		context.close();

		log.info("CalculateIndicatorAction excute end");
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
	CrawService[] calSerArr;
}
