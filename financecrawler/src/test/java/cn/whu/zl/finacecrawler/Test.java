/**
 * 
 */
package cn.whu.zl.finacecrawler;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.whu.zl.action.CalculateIndicatorAction;
import cn.whu.zl.action.JrjStockReportAction;
import cn.whu.zl.action.TransactionHistoryAction;
import cn.whu.zl.service.CalculateIndicatorService;
import cn.whu.zl.service.CrawService;

/**
 * @author B506-13-1
 * 
 */
public class Test {
	private static final Logger log = Logger.getLogger("Test.class");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");
		 CrawService cs =(CrawService) context.getBean("stockIndustrySer");
		//  CrawService cs =(CrawService) context.getBean("stockReportSer");
		// CrawService cs =(CrawService) context.getBean("financialReportSer");
		// CrawService cs =(CrawService) context.getBean("industryReportSer");
//		CrawService cs =(CrawService) context.getBean("jrjStockReportSer");
//		CrawService cs = (CrawService) context
//				.getBean("IndexTransactionHistorySer");
		 CrawService cs =(CrawService) context.getBean("MysqlToExcelSer");
//		 CrawService cs =(CrawService) context.getBean("InstitutionNameSer");
//		 CrawService cs = (CrawService) context.getBean("calculateIndicatorSer");
		 cs.start();
		// cs.close();
		// context.close();

//		CalculateIndicatorAction thAction =(CalculateIndicatorAction) context.getBean("calculateIndicatorAction");
		
		//TransactionHistoryAction thAction = (TransactionHistoryAction) context
		//		.getBean("transactionHistoryAction");
		// FundFlowHistoryAction thAction = (FundFlowHistoryAction) context
		// .getBean("fundFlowHistoryAction");

		// StockNewsAction thAction = (StockNewsAction) context
		// .getBean("stockNewsAction");
//				JrjStockReportAction jrjAction = (JrjStockReportAction) context.getBean("jrjStockReportAction");
		
		// List<String> errorList = new ArrayList<>();
		//
		// errorList.add("000557");
		// errorList.add("002705");
		// errorList.add("300357");
		// thAction.setErrorList(errorList);
		// thAction.excuteError();
<<<<<<< .mine
//		thAction.excute();
=======
//		thAction.excute();
//		jrjAction.excute();
>>>>>>> .r74
//		context.close();
	}

}