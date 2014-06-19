package cn.whu.zl.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.whu.zl.dao.FundFlowHistoryDao;
import cn.whu.zl.dao.IndexTransactionHistoryDao;
import cn.whu.zl.dao.InstitutionNameDao;
import cn.whu.zl.dao.JrjStockReportDao;
import cn.whu.zl.dao.TransactionHistoryDao;
import cn.whu.zl.entity.IndexTransactionHistory;
import cn.whu.zl.entity.InstitutionName;
import cn.whu.zl.entity.JrjStockReport;
import cn.whu.zl.entity.TransactionHistory;
import cn.whu.zl.util.CrawlerUtil;
import static cn.whu.zl.util.CrawlerUtil.*;

@Transactional
@Service("MysqlToExcelSer")
@Scope("prototype")
/**
 * @author B506-13-1
 * 
 */
public class MysqlToExcel extends BasicCrawServiceImp {

	private static final Logger log = Logger
			.getLogger(IndexTransactionHistory.class.getName());
	@Autowired
	private TransactionHistoryDao transactionHistoryDao;

	@Autowired
	private IndexTransactionHistoryDao indexTransactionHistoryDao;

	@Autowired
	private InstitutionNameDao institutionNameDao;

	@Autowired
	private FundFlowHistoryDao fundLowHistoryDao;

	@Autowired
	private JrjStockReportDao jrjStockReportDao;
	

	// private File infile;
	private File outfile;
	private List<InstitutionName> insList;
	// private int sheetNum = 0;
	private String insName = "中金公司";
	private int[] dayList;
	private int[] pastDayList;
	/*private final String[] excelHeader = { "Case ID", "机构名称", "机构评级", "股票代码",
			"股票名称", "流通市值", "所属市场（主板、中小板、创业板）", "行业", "行业评级", "评级日期", "最新评级",
			"评级变动", "评级变动dummy", "机构研报数", "目标价", "目标价dummy", "评级当日收盘价", "目标涨幅",
			"15日内最高价", "15日内涨幅", "15日内真实涨幅", "30日内最高价", "30日内涨幅", "30日内真实涨幅",
			"90日内最高价", "90日内涨幅", "90日内真实涨幅", "180日内最高价", "180日内涨幅",
			"180日内真实涨幅", "15日内最低价", "15日内跌幅", "15日内真实跌幅", "30日内最低价", "30日内跌幅",
			"30日内真实跌幅", "90日内最低价", "90日内跌幅", "90日内真实跌幅", "180日内最低价", "180日内跌幅",
			"180日内真实跌幅", "在90日内第几天达到最高价", "在180日内第几天达到最高价", "在90日内第几天达到最低价",
			"在180日内第几天达到最低价", "指数在15日内最高价", "指数15日内涨幅", "指数30日内最高价",
			"指数30日内涨幅", "指数90日内最高价", "指数90日内涨幅", "指数180日内最高价", "指数180日内涨幅",
			"指数15日内最低价", "指数15日内跌幅", "指数30日内最低价", "指数30日内跌幅", "指数90日内最低价",
			"指数90日内跌幅", "指数180日内最低价", "指数180日内跌幅", "过去1天资金净流入(万元)",
			"过去1天资金净流入(万元)dummy", "过去5天资金净流入(万元)", "过去5天资金净流入(万元)dummy",
			"过去10天资金净流入(万元)", "过去5天资金净流入(万元)dummy", "过去30天资金净流入(万元)",
			"过去30天资金净流入(万元)dummy", "过去1天主力资金净流入(万元)", "过去1天主力资金净流入(万元)dummy",
			"过去5天主力资金净流入", "过去5天主力资金净流入dummy", "过去10天主力资金净流入(万元)",
			"过去10天主力资金净流入(万元)dummy", "过去30天主力资金净流入(万元)",
			"过去30天主力资金净流入(万元)dummy", "过去1天历史涨幅", "过去1天历史涨幅dummy", "过去5天历史涨幅",
			"过去5天历史涨幅dummy", "过去10天历史涨幅", "过去10天历史涨幅dummy", "过去30天历史涨幅",
			"过去30天历史涨幅dummy" };*/
	
	/*private final String[] excelHeader = { "CaseID","机构名称","机构评级","股票代码","股票名称","流通市值","所属市场（主板、中小板、创业板）",
			"行业","行业评级","评级日期","最新评级","评级变动","评级变动dummy","机构研报数","目标价","目标价dummy",
			"15日真实涨幅","30日真实涨幅","90日真实涨幅","180日真实涨幅",
			"MFI_ALL_1","MFI_ALL7","MFI_MAIN_1","MFI_MAIN_7","RSI","ATR7","ATR14","MACD","Trade","过去5天是否发出Trade"};*/
	
	private final String[] excelHeader = { "CaseID","InstitutionID","InstitutionRate","InstitutionRateDummy","StockID","EFAMC","EFAMCDummy","MarketDummy",
			"Industry","IndustryRate","IndustryRateDummy","RateDate","LatestRateDummy","ChangeRateDummy","TargetPrice","TargetPriceDummy",
			"TurnOverRate","Lastday_TurnOverRate","TurnOverAmount","Lastday_TurnOverAmount","ChangeRatio","Lastday_ChangeRatio",
			"7day_True_Increase","15day_True_Increase","30day_True_Increase","45day_True_Increase","60day_True_Increase","75day_True_Increase","90day_True_Increase",
			"7day_True_Decrease","15day_True_Decrease","30day_True_Decrease","45day_True_Decrease","60day_True_Decrease","75day_True_Decrease","90day_True_Decrease",
			"MFI_ALL_1","MFI_ALL_1dummy","MFI_ALL_7","MFI_ALL_7dummy","MFI_ALL_14","MFI_ALL_14_dummy",
			"MFI_MAIN_1","MFI_MAIN_1dummy","MFI_MAIN_7","MFI_MAIN_7dummy","MFI_MAIN_14","MFI_MAIN_14_dummy",
			"RSI_6","RSI_6_dummy","RSI_12","RSI_12_dummy","RSI_24","RSI_24_dummy",
			"ATR7","ATR14","MACD","Trade","If_Buy_Trade","If_Sell_Trade"};
	
	/*private final String[] excelHeader = {"CaseID","InstitutionID","InstitutionRate","InstitutionRateDummy","StockID","EFAMC","EFAMCDummy","MarketDummy",
			"Industry","IndustryRate","IndustryRateDummy","RateDate","LatestRateDummy","ChangeRateDummy","TargetPrice","TargetPriceDummy",
			"TurnOverRate","Lastday_TurnOverRate","TurnOverAmount","Lastday_TurnOverAmount","ChangeRatio","Lastday_ChangeRatio",
			"7day_Stock_Increase","15day_Stock_Increase","30day_Stock_Increase",
			"45day_Stock_Increase","60day_Stock_Increase","75day_Stock_Increase","90day_Stock_Increase",
			"7day_Stock_Decrease","15day_Stock_Decrease","30day_Stock_Decrease",
			"45day_Stock_Decrease","60day_Stock_Decrease","75day_Stock_Decrease","90day_Stock_Decrease",
			"7day_Index_Increase","15day_Index_Increase","30day_Index_Increase",
			"45day_Index_Increase","60day_Index_Increase","75day_Index_Increase","90day_Index_Increase",
			"7day_Index_Decrease","15day_Index_Decrease","30day_Index_Decrease",
			"45day_Index_Decrease","60day_Index_Decrease","75day_Index_Decrease","90day_Index_Decrease",
			"MFI_ALL_1","MFI_ALL_1dummy","MFI_ALL_7","MFI_ALL_7dummy","MFI_ALL_14","MFI_ALL_14_dummy",
			"MFI_MAIN_1","MFI_MAIN_1dummy","MFI_MAIN_7","MFI_MAIN_7dummy","MFI_MAIN_14","MFI_MAIN_14_dummy",
			"RSI_6","RSI_6_dummy","RSI_12","RSI_12_dummy","RSI_24","RSI_24_dummy",
			"ATR7","ATR14","MACD","Trade","If_Buy_Trade","If_Sell_Trade"};*/
	
 	private Date begin;
	private Date end;
	private Date indicatorDate;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
			Locale.CHINA);
	private Map<String, String> insLevel;
	private Map<String, String> industryLevel;
	private Map<String, String> industryLevelDummy;
	private Map<String, String> insLevelDummy;
	private Date reportDate;

	public MysqlToExcel() {

		//dayList = new int[] { 1, 15, 30, 90, 180 };
		dayList = new int[] {7,15,30,45,60,75,90};
		pastDayList = new int[] { 1, 5, 10, 30 };
	}

	@Override
	public void start() {
		try {
			begin = sdf.parse("2013-01-01");
			end = sdf.parse("2014-01-01");
			insList = institutionNameDao.getAllIns();
//			log.info(excelHeader.length);

			// test
			//insList = new ArrayList<>();
			//insList.add(new InstitutionName("200000039", "东海证券"));
			
			/*
			 * insList.add(new InstitutionName("200000637","国泰君安"));
			 * insList.add(new InstitutionName("200001700","招商证券"));
			 * insList.add(new InstitutionName("200001068","海通证券"));
			 * insList.add(new InstitutionName("200002182","光大证券"));
			 * insList.add(new InstitutionName("200002281","华泰联合"));
			 * insList.add(new InstitutionName("200036127","东方证券"));
			 * insList.add(new InstitutionName("200011181","银河证券"));
			 * insList.add(new InstitutionName("200001194","广发证券"));
			 * insList.add(new InstitutionName("200000869","长江证券"));
			 */
			getDummyFromExcel(System.getProperty("user.dir")
					+ "/src/main/resources/ins.xls");
			FromMysqlToExcel(insList);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error("no file");
		} catch (ParseException pe) {
			log.error(pe);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

	}

	public void FromMysqlToExcel(List<InstitutionName> insList)
			throws IOException {
		// infile = new File("C:/Users/DAY/Desktop/20140314.xls");
		outfile = new File(System.getProperty("user.dir")
				+ "/src/main/resources/20140610-result.xls");
		if (!outfile.exists())
			outfile.createNewFile();
		FromMysqlToExcel(outfile, insList);
	}

	public void FromMysqlToExcel(File outfile, List<InstitutionName> insList)
			throws IOException {

		// in and out should not be the same file

		// BufferedInputStream in = new BufferedInputStream(new FileInputStream(
		// infile));
		FileOutputStream out = new FileOutputStream(outfile);
		// open HSSFWorkbook
		// POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook();
		// HSSFSheet sheet = wb.getSheetAt(sheetNum);// choose sheet in the xls
		int rowNum = 0;
		int colNum = excelHeader.length;

		// 写入表头
		HSSFSheet sheet = wb.createSheet();
		HSSFRow header = sheet.createRow(rowNum++);
		for (int i = 0; i < colNum; i++) {
			header.createCell(i).setCellValue(excelHeader[i]);
		}

		// 对每个机构写入数据
		for (InstitutionName ins : insList) {
		
			rowNum = FromMysqlToExcel(wb, sheet, ins, rowNum, colNum, 0);
		}

		wb.write(out);
		out.close();

		// in.close();
	}

	private int FromMysqlToExcel(HSSFWorkbook wb, HSSFSheet sheet,
			InstitutionName ins, int rowNum, int colNum, int flag)
			throws IOException {
		// 得到当前机构的研报列表
		// flag=1排除目标价为0的，flag=0不排除目标价位0
		String insCode = ins.getInstitutionID();
		String insName = ins.getInstitutionName();
		List<JrjStockReport> reportsList;
		if (flag == 0)
			reportsList = jrjStockReportDao.getOrgReports(insCode, begin, end);
		else
			reportsList = jrjStockReportDao
					.getOrgReportsE0(insCode, begin, end);
		
		//去掉所有12年数据
		for(int i = 0;i<reportsList.size();i++){
			try {
				if(reportsList.get(i).getSdtPK().getDate().getTime() 
						< sdf.parse("2013-01-01").getTime()){
					reportsList.remove(i);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (reportsList == null)
			return rowNum;
		int totals = reportsList.size();
		// test
		// totals = 10;
		if (totals == 0)
			return rowNum;
		List<Integer> firstKeep = new ArrayList<>(totals);

		log.info("写入 " + reportsList.get(0).getOrgName() + " 总共 "
				+ reportsList.size() + "条记录開始");
		Long now = new Date().getTime();
		String[][] toWriteArr = new String[totals][];
		int remove = 0;
		String[] temp = null;
		for (int line = 0; line < totals; line++) {

			JrjStockReport report = reportsList.get(line);
			String[] toWrite = new String[colNum];

			String stockID = report.getSdtPK().getStockID();
			/*if(!stockID.equals("000001"))
				continue;*/
			Date date = report.getSdtPK().getDate();
			String industryName = report.getIndustry();
			String linkURL = report.getSdtPK().getLinkURL();
			Float EFAMC = transactionHistoryDao.getEFAMC(stockID, date);
			

			float curPrice = getcurCloPrices(stockID, date);
			//当天收盘价为0，不写入excel表
			if (curPrice <= 1e-10) {
				remove++;
				continue;
			}

			// 取股票所属板块和板块代码
			String[] market = CrawlerUtil.getMarketDummy(stockID);

			// 取得评级变动并判断
			String gradeChange = report.getGradeChange();
			String gradeChangeDummy = String.valueOf(getGChangeDummy(gradeChange));
			

			if (temp == null && gradeChange.equals("维持"))
				temp = new String[] { stockID, sdf.format(date),
						line - remove + "" };
			else if (gradeChange.equals("维持") && !stockID.equals(temp[0])) {
				// 替换
				firstKeep.add(Integer.parseInt(temp[2]));
				temp[0] = stockID;
				temp[1] = sdf.format(date);
				temp[2] = line - remove + "";
			} else if (gradeChange.equals("维持") && stockID.equals(temp[0])) {
				firstKeep.add(Integer.parseInt(temp[2]));
				try {
					Calendar cal1 = Calendar.getInstance();
					Calendar cal2 = Calendar.getInstance();
					Date oldDate = sdf.parse(temp[1]);
					cal1.setTime(date);
					cal2.setTime(oldDate);
					cal2.add(Calendar.MONTH, 3);
					if (cal2.before(cal1)) {
						temp[0] = stockID;
						temp[1] = sdf.format(date);
						temp[2] = line - remove + "";
					}

				} catch (ParseException pe) {
				}
			} else if (temp != null && !gradeChange.equals("维持")
					&& stockID.equals(temp[0])) {
				firstKeep.add(Integer.parseInt(temp[2]));
				temp = null;
			}

			// 取dayList股票和大盘指数对应的最高最低收盘价,包括研报当天的收盘价
			Float[] afrHighPrices = getHighPrices(stockID, date, dayList);
			Float[] afrLowPrices = getLowPrices(stockID, date, dayList);
			Float[] indHighPrices = getHighIndexes(date, dayList, market[1]);
			Float[] indLowPrices = getLowIndexes(date, dayList, market[1]);

			float curIndex = getcurIndex(date, market[1]);

			// 得到股票和大盤涨幅和跌幅
			Float[] afrPriceInc = CalAOIArr(afrHighPrices, curPrice);
			Float[] afrPriceDec = CalAOIArr(afrLowPrices, curPrice);
			Float[] afrIndexInc = CalAOIArr(indHighPrices, curIndex);
			Float[] afrIndexDec = CalAOIArr(indLowPrices, curIndex);

			// 计算股票真实涨幅
			Float[] indexPriceIncOn = getIndexPriceOn(stockID,
					afrHighPrices, date, market[1]);// 获得当天指数收盘价
			Float[] indexAmountOfInc = CalAOIArr(indexPriceIncOn, curIndex);// 计算当天指数涨幅
			
			//计算股票真实跌幅
			Float[] indexPriceDecOn = getIndexPriceOn(stockID,
					afrHighPrices, date, market[1]);// 获得当天指数收盘价
			Float[] indexAmountOfDec = CalAOIArr(indexPriceDecOn, curIndex);// 计算当天指数跌幅

			// TODO

			// 计算90,180天最高最低股价和大盘价
			float hPrice90 = afrHighPrices[3];
			float hPrice180 = afrHighPrices[4];
			// float hIndex90 = indHighPrices[3];
			// float hIndex180 = indHighPrices[4];
			float lPrice90 = afrLowPrices[3];
			float lPrice180 = afrLowPrices[4];
			// float lIndex90 = indLowPrices[3];
			// float lIndex180 = indLowPrices[4];

			String hPrice90Date = getHighTarDate(stockID, hPrice90, date, 90);
			String hPrice180Date = getHighTarDate(stockID, hPrice180, date, 180);
			// String hIndex90Date = getTarDate(stockID, hIndex90, date, 90);
			// String hIndex180Date = getTarDate(stockID, hIndex180, date, 180);

			String lPrice90Date = getLowTarDate(stockID, lPrice90, date, 90);
			String lPrice180Date = getLowTarDate(stockID, lPrice180, date, 180);
			// String lIndex90Date = getTarDate(stockID, lIndex90, date, 90);
			// String lIndex180Date = getTarDate(stockID, lIndex180, date, 180);

			List<Float> MFI = getMFI(stockID,date);
			List<Float> RSI = getRSI(stockID,date);
			List<Float> ATR = getATR(stockID,date);
			float MACD = getMACD(stockID,date);
			int Trade = getTrade(stockID,date);
			int IfTradeUp = getIfTradeUp(stockID,date);
			int IfTradeDown = getIfTradeDown(stockID,date);
	
			List<Float> mfiavg = getMFIavg(stockID, date);
			List<Float> rsiavg = getRSIavg(stockID, date);
			int[] MFIdummy = calMFIdummy(mfiavg,MFI);
			int[] RSIdummy = calRSIdummy(rsiavg,RSI);
			//reportDate = getReportDate(stockID,date,linkURL);
			
			// 1.caseID.
			// 2.判断所属市场，通过股票代码判断
			int col = 0;
			toWrite[col++] = getCaseID(insCode, line);
			//toWrite[col++] = insName;
			toWrite[col++] = insCode;
			toWrite[col++] = getInsLevel(insName);
			toWrite[col++] = getInsLevelDummy(insName);
			toWrite[col++] = stockID;
			//toWrite[col++] = report.getStockName();
			toWrite[col++] = floatToStr(EFAMC, 2);
			//toWrite[col++] = market[0];
			toWrite[col++] = Integer.toString(getEFAMCDummy(EFAMC));
			toWrite[col++] = getMarketDummy(stockID)[0];
			toWrite[col++] = report.getIndustry();
			toWrite[col++] = getIndustryLevel(industryName);
			toWrite[col++] = getIndustryLevelDummy(industryName);
			toWrite[col++] = sdf.format(date);
			/*if(reportDate == null)
				toWrite[col++] = "未知";
			else
				toWrite[col++] = reportDate.toString();*/
			toWrite[col++] = Integer.toString(getJrjGradeDummy(report.getGrade()));
			//toWrite[col++] = gradeChange;
			toWrite[col++] = gradeChangeDummy;
			//toWrite[col++] = String.valueOf(report.getOrgReportNum());

			toWrite[col++] = floatToStr(report.getTarPrice(), 2);
			toWrite[col++] = getTarPriceDummy(report);
			
			List<Float> tmpList = getTurnOverRate(stockID,date);
			if(tmpList != null && tmpList.size() != 0){
				toWrite[col++] = floatToStr(tmpList.get(0), 4);
				if(tmpList.size() == 1)
					toWrite[col++] = floatToStr(0f, 4);
				else
					toWrite[col++] = floatToStr(tmpList.get(1), 4);}
			else{
				toWrite[col++] = floatToStr(0f,4);
				toWrite[col++] = floatToStr(0f,4);
				
			}
			
			tmpList = getTurnOverAmount(stockID,date);
			if(tmpList != null && tmpList.size() != 0){
				toWrite[col++] = floatToStr(tmpList.get(0), 4);
				if(tmpList.size() == 1)
					toWrite[col++] = floatToStr(0f, 4);
				else
					toWrite[col++] = floatToStr(tmpList.get(1), 4);}
			else{
				toWrite[col++] = floatToStr(0f,4);
				toWrite[col++] = floatToStr(0f,4);
				
			}
			tmpList = getChangeRatio(stockID,date);
			if(tmpList != null && tmpList.size() != 0){
				toWrite[col++] = floatToStr(tmpList.get(0), 4);
				if(tmpList.size() == 1)
					toWrite[col++] = floatToStr(0f, 4);
				else
					toWrite[col++] = floatToStr(tmpList.get(1), 4);}
			else{
				toWrite[col++] = floatToStr(0f,4);
				toWrite[col++] = floatToStr(0f,4);
				
			}

			// if (report.getTarPrice() > curPrice)
			// styleRedList.add(col - 1);
			// else
			// styleGreenList.add(col - 1);

			//真实涨幅
			toWrite[col++] = floatToStr(afrPriceInc[0] - indexAmountOfInc[0], 4);
			toWrite[col++] = floatToStr(afrPriceInc[1] - indexAmountOfInc[1], 4);
			toWrite[col++] = floatToStr(afrPriceInc[2] - indexAmountOfInc[2], 4);
			toWrite[col++] = floatToStr(afrPriceInc[3] - indexAmountOfInc[3], 4);
			toWrite[col++] = floatToStr(afrPriceInc[4] - indexAmountOfInc[4], 4);
		    toWrite[col++] = floatToStr(afrPriceInc[5] - indexAmountOfInc[5], 4);
		    toWrite[col++] = floatToStr(afrPriceInc[6] - indexAmountOfInc[6], 4);
			
		   
			//真实跌幅
			toWrite[col++] = floatToStr(afrPriceDec[0] - indexAmountOfDec[0], 4);
			toWrite[col++] = floatToStr(afrPriceDec[1] - indexAmountOfDec[1], 4);
			toWrite[col++] = floatToStr(afrPriceDec[2] - indexAmountOfDec[2], 4);
			toWrite[col++] = floatToStr(afrPriceDec[3] - indexAmountOfDec[3], 4);
			toWrite[col++] = floatToStr(afrPriceDec[4] - indexAmountOfDec[4], 4);
			toWrite[col++] = floatToStr(afrPriceDec[5] - indexAmountOfDec[5], 4);
			toWrite[col++] = floatToStr(afrPriceDec[6] - indexAmountOfDec[6], 4);
			
		    /*//股票涨幅
		    toWrite[col++] = floatToStr(afrPriceInc[0], 4);
			toWrite[col++] = floatToStr(afrPriceInc[1], 4);
			toWrite[col++] = floatToStr(afrPriceInc[2], 4);
			toWrite[col++] = floatToStr(afrPriceInc[3], 4);
			toWrite[col++] = floatToStr(afrPriceInc[4], 4);
		    toWrite[col++] = floatToStr(afrPriceInc[5], 4);
		    toWrite[col++] = floatToStr(afrPriceInc[6], 4);
		    
		    //股票跌幅
		    toWrite[col++] = floatToStr(afrPriceDec[0], 4);
			toWrite[col++] = floatToStr(afrPriceDec[1], 4);
			toWrite[col++] = floatToStr(afrPriceDec[2], 4);
			toWrite[col++] = floatToStr(afrPriceDec[3], 4);
			toWrite[col++] = floatToStr(afrPriceDec[4] , 4);
			toWrite[col++] = floatToStr(afrPriceDec[5], 4);
			toWrite[col++] = floatToStr(afrPriceDec[6], 4);
			
		    //版块涨幅
		    toWrite[col++] = floatToStr(indexAmountOfInc[0], 4);
			toWrite[col++] = floatToStr(indexAmountOfInc[1], 4);
			toWrite[col++] = floatToStr(indexAmountOfInc[2], 4);
			toWrite[col++] = floatToStr(indexAmountOfInc[3], 4);
			toWrite[col++] = floatToStr(indexAmountOfInc[4], 4);
		    toWrite[col++] = floatToStr(indexAmountOfInc[5], 4);
		    toWrite[col++] = floatToStr(indexAmountOfInc[6], 4);
		    
		    //版块跌幅
		    toWrite[col++] = floatToStr(indexAmountOfDec[0], 4);
			toWrite[col++] = floatToStr(indexAmountOfDec[1], 4);
			toWrite[col++] = floatToStr(indexAmountOfDec[2], 4);
			toWrite[col++] = floatToStr(indexAmountOfDec[3], 4);
			toWrite[col++] = floatToStr(indexAmountOfDec[4], 4);
			toWrite[col++] = floatToStr(indexAmountOfDec[5], 4);
			toWrite[col++] = floatToStr(indexAmountOfDec[6], 4);*/
			
			
			//指标
			toWrite[col++] =  floatToStr(MFI.get(0)-mfiavg.get(0),4);
			toWrite[col++] =  floatToStr(MFIdummy[0],4);
			toWrite[col++] =  floatToStr(MFI.get(1)-mfiavg.get(1),4);
			toWrite[col++] =  floatToStr(MFIdummy[1],4);
			toWrite[col++] =  floatToStr(MFI.get(2)-mfiavg.get(2),4);
			toWrite[col++] =  floatToStr(MFIdummy[2],4);
			toWrite[col++] =  floatToStr(MFI.get(3)-mfiavg.get(3),4);
			toWrite[col++] =  floatToStr(MFIdummy[3],4);
			toWrite[col++] =  floatToStr(MFI.get(4)-mfiavg.get(4),4);
			toWrite[col++] =  floatToStr(MFIdummy[4],4);
			toWrite[col++] =  floatToStr(MFI.get(5)-mfiavg.get(5),4);
			toWrite[col++] =  floatToStr(MFIdummy[5],4);
			
			
			toWrite[col++] =  floatToStr(RSI.get(0)-rsiavg.get(0), 4);
			toWrite[col++] =  floatToStr(RSIdummy[0], 4);
			toWrite[col++] =  floatToStr(RSI.get(1)-rsiavg.get(1), 4);
			toWrite[col++] =  floatToStr(RSIdummy[1], 4);
			toWrite[col++] =  floatToStr(RSI.get(2)-rsiavg.get(2), 4);
			toWrite[col++] =  floatToStr(RSIdummy[2], 4);
			
			toWrite[col++] =  floatToStr(ATR.get(0).floatValue(),4);
			toWrite[col++] =  floatToStr(ATR.get(1).floatValue(),4);
			
			toWrite[col++] =  floatToStr(MACD,4);
			toWrite[col++] =  Integer.toString(Trade);
			toWrite[col++] =  Integer.toString(IfTradeUp);
			toWrite[col++] =  Integer.toString(IfTradeDown);
			
			
			/*toWrite[col++] = floatToStr(curPrice, 2);
			// 目標漲幅
			toWrite[col++] = floatToStr((report.getTarPrice() - curPrice)
					/ curPrice, 4);

			// 存入股价最高价和涨幅
			toWrite[col++] = floatToStr(afrHighPrices[1], 2);
			toWrite[col++] = floatToStr(afrPriceInc[1], 4);
			toWrite[col++] = floatToStr(afrPriceInc[1] - indexAmountOfInc[0], 4);
			toWrite[col++] = floatToStr(afrHighPrices[2], 2);
			toWrite[col++] = floatToStr(afrPriceInc[2], 4);
			toWrite[col++] = floatToStr(afrPriceInc[2] - indexAmountOfInc[1], 4);
			toWrite[col++] = floatToStr(afrHighPrices[3], 2);
			toWrite[col++] = floatToStr(afrPriceInc[3], 4);
			toWrite[col++] = floatToStr(afrPriceInc[3] - indexAmountOfInc[2], 4);
			toWrite[col++] = floatToStr(afrHighPrices[4], 2);
			toWrite[col++] = floatToStr(afrPriceInc[4], 4);
			toWrite[col++] = floatToStr(afrPriceInc[4] - indexAmountOfInc[3], 4);

			// 存入股价最低价和跌幅
			toWrite[col++] = floatToStr(afrLowPrices[1], 2);
			toWrite[col++] = floatToStr(afrPriceDec[1], 4);
			toWrite[col++] = floatToStr(afrPriceDec[1] - afrIndexDec[1], 4);
			toWrite[col++] = floatToStr(afrLowPrices[2], 2);
			toWrite[col++] = floatToStr(afrPriceDec[2], 4);
			toWrite[col++] = floatToStr(afrPriceDec[2] - afrIndexDec[2], 4);
			toWrite[col++] = floatToStr(afrLowPrices[3], 2);
			toWrite[col++] = floatToStr(afrPriceDec[3], 4);
			toWrite[col++] = floatToStr(afrPriceDec[3] - afrIndexDec[3], 4);
			toWrite[col++] = floatToStr(afrLowPrices[4], 2);
			toWrite[col++] = floatToStr(afrPriceDec[4], 4);
			toWrite[col++] = floatToStr(afrPriceDec[4] - afrIndexDec[4], 4);

			// 存入第几天最高价
			toWrite[col++] = hPrice90Date;
			toWrite[col++] = hPrice180Date;
			toWrite[col++] = lPrice90Date;
			toWrite[col++] = lPrice180Date;

			// 存入指数最高价和涨幅
			toWrite[col++] = floatToStr(indHighPrices[1], 2);
			toWrite[col++] = floatToStr(afrIndexInc[1], 4);
			toWrite[col++] = floatToStr(indHighPrices[2], 2);
			toWrite[col++] = floatToStr(afrIndexInc[2], 4);
			toWrite[col++] = floatToStr(indHighPrices[3], 2);
			toWrite[col++] = floatToStr(afrIndexInc[3], 4);
			toWrite[col++] = floatToStr(indHighPrices[4], 2);
			toWrite[col++] = floatToStr(afrIndexInc[4], 4);

			// 存入大盘最低价和跌幅
			toWrite[col++] = floatToStr(indLowPrices[1], 2);
			toWrite[col++] = floatToStr(afrIndexDec[1], 4);
			toWrite[col++] = floatToStr(indLowPrices[2], 2);
			toWrite[col++] = floatToStr(afrIndexDec[2], 4);
			toWrite[col++] = floatToStr(indLowPrices[3], 2);
			toWrite[col++] = floatToStr(afrIndexDec[3], 4);
			toWrite[col++] = floatToStr(indLowPrices[4], 2);
			toWrite[col++] = floatToStr(afrIndexDec[4], 4);

			// 存入过去n天资金净流入和主力凈流入
			int[] netInList = getNetInList(stockID, date, pastDayList);
			int[] netInDummy = getFundDummy(netInList);
			int[] mainNetInList = getMainNetInList(stockID, date, pastDayList);
			int[] mainNetInDummy = getFundDummy(mainNetInList);
			toWrite[col++] = String.valueOf(netInList[0]);
			toWrite[col++] = String.valueOf(netInDummy[0]);
			toWrite[col++] = String.valueOf(netInList[1]);
			toWrite[col++] = String.valueOf(netInDummy[1]);
			toWrite[col++] = String.valueOf(netInList[2]);
			toWrite[col++] = String.valueOf(netInDummy[2]);
			toWrite[col++] = String.valueOf(netInList[3]);
			toWrite[col++] = String.valueOf(netInDummy[3]);

			toWrite[col++] = String.valueOf(mainNetInList[0]);
			toWrite[col++] = String.valueOf(mainNetInDummy[0]);
			toWrite[col++] = String.valueOf(mainNetInList[1]);
			toWrite[col++] = String.valueOf(mainNetInDummy[1]);
			toWrite[col++] = String.valueOf(mainNetInList[2]);
			toWrite[col++] = String.valueOf(mainNetInDummy[2]);
			toWrite[col++] = String.valueOf(mainNetInList[3]);
			toWrite[col++] = String.valueOf(mainNetInDummy[3]);

			// 存过去n天历史涨幅
			Float[] pastIncre = getPastIncre(stockID, date, pastDayList);
			int[] increDummy = getIncreDummy(pastIncre);
			toWrite[col++] = floatToStr(pastIncre[0], 4);
			toWrite[col++] = String.valueOf(increDummy[0]);
			toWrite[col++] = floatToStr(pastIncre[1], 4);
			toWrite[col++] = String.valueOf(increDummy[1]);
			toWrite[col++] = floatToStr(pastIncre[2], 4);
			toWrite[col++] = String.valueOf(increDummy[2]);
			toWrite[col++] = floatToStr(pastIncre[3], 4);
			toWrite[col++] = String.valueOf(increDummy[3]);*/

			// String origExcel[] = ReadCellFromExcel(sheet, line);
			// String marketPara = setMarket(origExcel[0]);
			// List<Float> totalList = CombineAll(origExcel);
			// List<Float> tempCloPriceList = CombineThIth(origExcel);
			// log.info("this is line " + line);
			// WriteToExcel(sheet, cellstyle, tempCloPriceList, line);

			// 创建写入格式,写入红色粗体
			// HSSFFont font = wb.createFont();
			// font.setColor(HSSFFont.COLOR_RED);
			// HSSFCellStyle cellstyle = wb.createCellStyle();
			// cellstyle.setFont(font);

			log.info("计算第 " + line + "条开始");
			toWriteArr[line - remove] = toWrite;
			// writeToExcel(sheet, toWrite, ++rowNum);
			if (line % 50 == 0)
				System.gc();
		}

		for (int i : firstKeep)
			toWriteArr[i][13] = "3";

		rowNum = writeToExcelArr(sheet, toWriteArr, totals - remove, rowNum);
		log.debug("共花时间" + (new Date().getTime() - now) + "");
		return rowNum;
	}
	
	private Date getReportDate(String stockID,Date date,String linkURL){
		return jrjStockReportDao.getReportDate(stockID,date,linkURL);
	}

	// 目標價dummy
	private String getTarPriceDummy(JrjStockReport report) {
		if (report.getTarPrice() > 1e-10)
			return "1";
		else
			return "0";
	}

	// 得到行业和机构对应的dummy值
	private void getDummyFromExcel(String path) throws IOException {
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(path)));
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();

		// 行业dummy
		industryLevel = new HashMap<>(rowNum * 4 / 3);
		for (int i = 1; i <= rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			industryLevel.put(row.getCell(0).toString().trim(),
					String.valueOf((int) row.getCell(1).getNumericCellValue()));
		}
		
		industryLevelDummy = new HashMap<>(rowNum * 4 / 3);
		for (int i = 1;i <= rowNum; i++){
			HSSFRow row = sheet.getRow(i);
			industryLevelDummy.put(row.getCell(0).toString().trim(),
					String.valueOf((int) row.getCell(3).getNumericCellValue()));
		}

		// 机构dummy
		sheet = wb.getSheetAt(1);
		rowNum = sheet.getLastRowNum();

		insLevel = new HashMap<>(rowNum * 4 / 3);
		for (int i = 1; i <= rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			insLevel.put(row.getCell(0).toString().trim(),
					String.valueOf((int) row.getCell(1).getNumericCellValue()));
		}
		
		insLevelDummy = new HashMap<>(rowNum * 4/3);
		for (int i = 1; i <= rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			insLevelDummy.put(row.getCell(0).toString().trim(),
					String.valueOf((int) row.getCell(3).getNumericCellValue()));
		}
	}

	// 過去n天收盤價漲跌幅
	private Float[] getPastIncre(String stockID, Date date, int[] dayList) {
		int len = dayList.length;

		Float[] result = new Float[len];
		for (int i = 0; i < len; i++) {
			Float[] prePrices = transactionHistoryDao.getPre2CloPrice(stockID,
					date, dayList[i]);
			if (prePrices[1] < 1e-10)
				result[i] = 0.0F;
			else
				result[i] = (prePrices[0] - prePrices[1]) / prePrices[1];
		}

		return result;
	}
	
	private int[] calMFIdummy(List<Float> mfiavg,List<Float> MFI){
		int[] MFIdummy = new int[MFI.size()];
		for(int i = 0;i < 3;i++){
			MFIdummy[i] = CrawlerUtil.getMFIDummy(mfiavg.get(i),MFI.get(i));
		}
		
		for(int i = 3;i < MFI.size();i++){
			MFIdummy[i] = CrawlerUtil.getMFIMainDummy(mfiavg.get(i),MFI.get(i));
		}
		
		return MFIdummy;
		
	}
	
	private int[] calRSIdummy(List<Float> rsiavg,List<Float> RSI){
		int[] RSIdummy = new int[3];
		RSIdummy[0] = CrawlerUtil.getRSI6Dummy(rsiavg.get(0), RSI.get(0));
		RSIdummy[1] = CrawlerUtil.getRSI12Dummy(rsiavg.get(1), RSI.get(1));
		RSIdummy[2] = CrawlerUtil.getRSI24Dummy(rsiavg.get(2), RSI.get(2));
		
		return RSIdummy;
	}

	public List<Float> getTurnOverRate(String stockID,Date date){
		return transactionHistoryDao.getTurnOverRate(stockID,date);
		
	}
	
	public List<Float> getTurnOverAmount(String stockID,Date date){
		return transactionHistoryDao.getTurnOverAmount(stockID,date);
	}
	
	public List<Float> getChangeRatio(String stockID,Date date){
		return transactionHistoryDao.getChangeRatio(stockID,date);
	}
	
	
	
	public Float[] getIndexPriceOn(String stockID, Float[] Prices,
			Date date, String market) {
		Float[] results = new Float[7];//数组必须先初始化！！
		String[] hPriceDates = new String[7];
		
        //获得日期
		List<TransactionHistory> tss = transactionHistoryDao.getTranHisList(stockID, date, 90);
		for(int i = 0;i < 7;i++){
			for(int j = 0;j < tss.size();j++){
				TransactionHistory ts = tss.get(j);
				if(Math.abs(ts.getCloPrice() - Prices[i]) <= 1e-10){
					hPriceDates[i] = ts.getSdPK().getDate().toString();
				}
			}
		}
		
		//获得日期当天的index收盘价
		try{
			for(int i = 0;i < 7;i++){
				results[i] = indexTransactionHistoryDao.getCloPrice(
						sdf.parse(hPriceDates[i]),"high", "after",1,market);
				}
		}catch(ParseException pe){}
			
		
		/*for (int i = 0; i < 4; i++) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, Integer.parseInt(hPriceDates[i]));
			results[i] = getcurIndex(cal.getTime(), market);
		}*/

		return results;

	}

	private int[] getIncreDummy(Float[] pastIncre) {
		int len = pastIncre.length;
		int[] increDummy = new int[len];

		for (int i = 0; i < len; i++) {
			if (pastIncre[i] > 1e-10)
				increDummy[i] = 1;
			else if (pastIncre[i] < -(1e-10))
				increDummy[i] = 0;
			else
				increDummy[i] = -1;
		}

		return increDummy;
	}

	// 历史资金净流入
	private int[] getNetInList(String stockID, Date date, int[] dayList) {

		int len = dayList.length;
		int[] result = new int[len];

		for (int i = 0; i < len; i++) {
			result[i] = fundLowHistoryDao.getSumNetInDays(stockID, date,
					dayList[i]);
		}

		return result;
	}

	// 历史主力资金净流入
	private int[] getMainNetInList(String stockID, Date date, int[] dayList) {

		int len = dayList.length;
		int[] result = new int[len];

		for (int i = 0; i < len; i++) {
			result[i] = fundLowHistoryDao.getSumMainNetInDays(stockID, date,
					dayList[i]);
		}

		return result;
	}

	private int[] getFundDummy(int[] fundList) {
		int len = fundList.length;
		int[] result = new int[len];

		for (int i = 0; i < len; i++) {
			if (fundList[i] > 0)
				result[i] = 1;
			else
				result[i] = 0;
		}

		return result;
	}

	// 计算目标价最早在days范围内哪天得到
	private String getHighTarDate(String stockID, float tar, Date date, int days) {
		String result = "-1";
		List<TransactionHistory> list = transactionHistoryDao.getTranHisList(
				stockID, date, days);
		for (int i = 0; i < list.size(); i++) {
			TransactionHistory ts = list.get(i);
			if (Math.abs(ts.getCloPrice() - tar) <= 1e-10) {
				result = String.valueOf(i);
				break;
			}
		}
		return result;
	}

	private String getLowTarDate(String stockID, float tar, Date date, int days) {
		String result = "-1";
		List<TransactionHistory> list = transactionHistoryDao.getTranHisList(
				stockID, date, days);
		for (int i = 0; i < list.size(); i++) {
			TransactionHistory ts = list.get(i);
			if (Math.abs(ts.getLowPrice() - tar) <= 1e-10) {
				result = String.valueOf(i);
				break;
			}
		}
		return result;
	}

	private String getCaseID(String insCode, int line) {
		return new StringBuilder(insCode).append("_").append(line).toString();
	}

	private String getInsLevel(String insName) throws IOException {
		if (insLevel.containsKey(insName))
			return insLevel.get(insName);
		else
			return "-1";
	}
	
	
	private String getIndustryLevel(String industryName) throws IOException {
		if (industryLevel.containsKey(industryName))
			return industryLevel.get(industryName);
		else
			return "-1";
	}
	
	private String getInsLevelDummy(String insName) throws IOException {
		if (insLevelDummy.containsKey(insName))
			return insLevelDummy.get(insName);
		else
			return "-1";
	}
	
	private String getIndustryLevelDummy(String industryName) throws IOException {
		if (industryLevelDummy.containsKey(industryName))
			return industryLevelDummy.get(industryName);
		else
			return "-1";
	}

	// 计算涨跌幅
	private Float CalAOI(float target, float base) {
		if (base == 0)
			return 0.0F;
		return (target - base) / base;
	}

	private List<Float> CalAOIList(List<Float> tarList, float base) {
		List<Float> result = new ArrayList<>(tarList.size());
		for (Iterator<Float> it = tarList.iterator(); it.hasNext();) {
			result.add(CalAOI(it.next(), base));
		}
		return result;
	}

	private Float[] CalAOIArr(Float[] tarList, float base) {
		int len = tarList.length;
		Float[] result = new Float[len];
		for (int i = 0; i < len; i++) {
			result[i] = CalAOI(tarList[i], base);
		}
		return result;
	}

	private int writeToExcelArr(HSSFSheet sheet, String[][] toWriteArr,
			int len, int beginLine) {
		for (int i = 0; i < len; i++) {
			// log.info("写入第" + (beginLine + i) + "行开始");
			writeToExcel(sheet, toWriteArr[i], beginLine + i);
		}
		return beginLine + len;
	}

	// 对于给定sheet 给定列，写回xls
	private void writeToExcel(HSSFSheet sheet, String[] toWrite, int line) {

		HSSFRow row = sheet.createRow(line);
		for (int i = 0; i < toWrite.length; i++) {
			HSSFCell cell = row.createCell(i);
			// if (styleRedCols.contains(i)) {
			// cell.setCellStyle(cellstyle);
			// cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			// }
			cell.setCellValue(toWrite[i]);
		}

		// efamc.setCellValue(String.format("%.2f", toWrite.get(0)
		// .floatValue()));

		/*
		 * for (int i = 10, j = 1; i < 24 && j < 15; i++, j++) {
		 * 
		 * HSSFCell tempCell = row.getCell(i); if (tempCell == null) tempCell =
		 * row.createCell(i);
		 * 
		 * // if (i == 16) // tempCell.setCellValue(String.format("%.4f",
		 * tempCloPriceList // .get(j).floatValue())); // else //
		 * tempCell.setCellValue(String.format("%.2f", tempCloPriceList //
		 * .get(j).floatValue()));
		 * 
		 * }
		 */

	}

	private String floatToStr(float f, int num) {
		String format = "%.?f";
		format = format.replaceAll("\\?", String.valueOf(num));
		return String.format(format, f);
	}

	// 得出stockID的dayList的对应天数的最高收盤价或者最低收盤价
	private Float[] getPrices(String stockID, Date date, int[] dayList,
			String... args) {
		String flag2 = "after", flag1 = "High";

		if (args != null)
			for (String arg : args)
				switch (arg) {
				case "past":
					flag2 = "past";
					break;
				case "low":
					flag1 = "low";
					break;
				default:
					break;
				}

		int len = dayList.length;
		Float[] results = new Float[len];

		for (int i = 0; i < len; i++) {
			results[i] = transactionHistoryDao.getCloPrice(stockID, date,
					flag1, flag2, dayList[i]);
		}

		return results;
	}

	private Float[] getHighPrices(String stockID, Date date, int[] dayList) {
		int len = dayList.length;
		Float[] results = new Float[len];
		for (int i = 0; i < len; i++) {
			results[i] = transactionHistoryDao.getHighPrice(stockID, date,
					dayList[i]);// 取收盘价
		}
		return results;
	}

	private Float[] getHighIndexes(Date date, int[] dayList, String id) {
		int len = dayList.length;
		Float[] results = new Float[len];
		for (int i = 0; i < len; i++) {
			results[i] = indexTransactionHistoryDao.getHighPrice(date,
					dayList[i], id);
		}
		return results;
	}

	private Float[] getLowPrices(String stockID, Date date, int[] dayList) {
		int len = dayList.length;
		Float[] results = new Float[len];
		for (int i = 0; i < len; i++) {
			results[i] = transactionHistoryDao.getLowPrice(stockID, date,
					dayList[i]);
		}
		return results;
	}

	private Float[] getLowIndexes(Date date, int[] dayList, String id) {
		int len = dayList.length;
		Float[] results = new Float[len];
		for (int i = 0; i < len; i++) {
			results[i] = indexTransactionHistoryDao.getLowPrice(date,
					dayList[i], id);
		}
		return results;
	}

	private float getcurCloPrices(String stockID, Date date, String... args) {
		return transactionHistoryDao.getCloPrice(stockID, date, "high",
				"after", 1);
	}

	private float getcurIndex(Date date, String id) {
		return indexTransactionHistoryDao.getCloPrice(date, "high", "after", 1,
				id);
	}

	// 得出指数指数对应天数的指数
	private Float[] getIndexPrices(Date date, int[] dayList, String... args) {
		String flag1 = "high", flag2 = "after";

		if (args != null)
			for (String arg : args)
				switch (arg) {
				case "past":
					flag2 = "past";
					break;
				case "low":
					flag1 = "low";
					break;
				default:
					break;
				}

		int len = dayList.length;
		Float[] results = new Float[len];

		for (int i = 0; i < len; i++) {
			results[i] = indexTransactionHistoryDao.getCloPrice(date, flag1,
					flag2, dayList[i]);
		}

		return results;
	}
	
	private List<Float> getMFI(String stockID,Date date){
		return fundLowHistoryDao.getMFI(stockID,date);
		
	}

	/*private List<Integer> getMFIdummy(String stockID,Date date){
		return fundLowHistoryDao.getMFIdummy(stockID,date);
	}*/
	
	private List<Float> getRSI(String stockID,Date date){
		return transactionHistoryDao.getRSI(stockID, date);
	}
	
	/*private List<Integer> getRSIdummy(String stockID,Date date){
		return transactionHistoryDao.getRSIdummy(stockID, date);
	}*/
	
	private List<Float> getATR(String stockID,Date date){
		return transactionHistoryDao.getATR(stockID, date);
	}
	
	private float getMACD(String stockID,Date date){
		return transactionHistoryDao.getMACD(stockID, date);
	}
	
	private int getTrade(String stockID,Date date){
		return transactionHistoryDao.getTrade(stockID, date);
	}
	
	private int getIfTradeUp(String stockID,Date date){
		return transactionHistoryDao.getIfTradeUp(stockID,date);
	}
	
	private int getIfTradeDown(String stockID,Date date){
		return transactionHistoryDao.getIfTradeDown(stockID,date);
	}
	
	private List<Float> getMFIavg(String stockID,Date date){
		return fundLowHistoryDao.getMFIa(stockID, date);
	}
	
	private List<Float> getRSIavg(String stockID,Date date){
		return transactionHistoryDao.getRSIa(stockID,date);
	}
}
