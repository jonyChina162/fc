package cn.whu.zl.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import cn.whu.zl.dao.NcTableDao;
import cn.whu.zl.entity.NcTable;
import cn.whu.zl.util.CrawlerUtil;

@Service("ncTableToExcel")
public class NcTableToExcel extends BasicCrawServiceImp {

	private static final Logger log = Logger.getLogger(NcTableToExcel.class
			.getName());

	@Autowired
	private NcTableDao nctableDao;

	private File outfile;

	private final String[] excelHeader = { "股票名称", "股票代码", "所属板块", "诊断日期","买入建议",
			"平均成本", "牛叉评级", "机构占A股流通比例", "技术面得分", "技术面百分比", "股价是否在成本上下方运行",
			"该股走势与大盘", "该股走势与行业", "该股压力位价位", "该股支位", "资金面得分", "资金面百分比", "资金流向",
			"资金流向与行业", "控盘程度", "消息面得分", "消息面百分比", "所属行业", "行业面得分", "行业面百分比",
			"行业走势", "机构行业评级", "机构个股评级", "机构每股收益预测" };

	private final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm", Locale.CHINA);

	public NcTableToExcel() {
	}

	@Override
	public void start() {
		try {
			Date date = new Date();
			Calendar cr = Calendar.getInstance();
			cr.setTime(date);
			if (cr.get(Calendar.HOUR_OF_DAY) < 19) {
				cr.add(Calendar.DAY_OF_YEAR, -1);
				date = cr.getTime();
			}
			fromMysqlToExcel(date);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			log.error("no file");
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

	}

	public void fromMysqlToExcel(Date date) throws IOException {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		String d = sdf1.format(date);

		outfile = new File(System.getProperty("user.dir")
				+ "/src/main/resources/nctable_" + d + ".xls");
		if (!outfile.exists())
			outfile.createNewFile();

		log.info("写入 " + outfile.getName() + " 文件开始");

		FileOutputStream out = new FileOutputStream(outfile);
		HSSFWorkbook wb = new HSSFWorkbook();
		int rowNum = 0;
		int colNum = excelHeader.length;

		// 写入表头
		HSSFSheet sheet = wb.createSheet();
		HSSFRow header = sheet.createRow(rowNum++);
		for (int i = 0; i < colNum; i++) {
			header.createCell(i).setCellValue(excelHeader[i]);
		}

		List<NcTable> toWriteList = nctableDao.getByDate(date);
		int total = toWriteList.size();

		for (NcTable toWrite : toWriteList) {
			HSSFRow row = sheet.createRow(rowNum++);
			for (int j = 0; j < colNum; j++) {
				row.createCell(j);
			}
			int tsColNum = 0;

			row.getCell(tsColNum++).setCellValue(toWrite.getStockName());
			row.getCell(tsColNum++)
					.setCellValue(toWrite.getSdPK().getStockID());
			row.getCell(tsColNum++).setCellValue(CrawlerUtil.getMarket(toWrite.getIndusName())[0]);
			row.getCell(tsColNum++).setCellValue(sdf.format(toWrite.getTime()));
			row.getCell(tsColNum++).setCellValue(toWrite.getStoRate());
			row.getCell(tsColNum++).setCellValue(floatToStr(toWrite.getAvgCost(),2));
			row.getCell(tsColNum++).setCellValue(floatToStr(toWrite.getNcGrade(),2));
			row.getCell(tsColNum++).setCellValue((int) toWrite.getIndusInAPer());
			row.getCell(tsColNum++).setCellValue(floatToStr(toWrite.gettGrade(),2));
			row.getCell(tsColNum++).setCellValue((int) toWrite.gettGradePer());
			row.getCell(tsColNum++).setCellValue(toWrite.getPriComCost());
			row.getCell(tsColNum++).setCellValue(toWrite.getStoComIndex());
			row.getCell(tsColNum++).setCellValue(toWrite.getStoComIndus());
			row.getCell(tsColNum++).setCellValue(floatToStr(toWrite.getPsePrice(),2));
			row.getCell(tsColNum++).setCellValue(floatToStr(toWrite.getStdPrice(),2));
			row.getCell(tsColNum++).setCellValue(floatToStr(toWrite.getmGrade(),2));
			row.getCell(tsColNum++).setCellValue((int) toWrite.getmGradePer());
			row.getCell(tsColNum++).setCellValue(toWrite.getmFlowTo());
			row.getCell(tsColNum++).setCellValue(toWrite.getmFlowComIndus());
			row.getCell(tsColNum++).setCellValue(toWrite.getConDegree());
			row.getCell(tsColNum++).setCellValue(floatToStr(toWrite.getImGrade(),2));
			row.getCell(tsColNum++).setCellValue((int) toWrite.getImGradePer());
			row.getCell(tsColNum++).setCellValue(toWrite.getIndusName());
			row.getCell(tsColNum++).setCellValue(floatToStr(toWrite.getInGrade(),2));
			row.getCell(tsColNum++).setCellValue((int) toWrite.getInGradePer());
			row.getCell(tsColNum++).setCellValue(toWrite.getInTrend());
			row.getCell(tsColNum++).setCellValue(toWrite.getInRate());
			row.getCell(tsColNum++).setCellValue(toWrite.getInStoRate());
			row.getCell(tsColNum++).setCellValue(toWrite.getInProfor());

		}
		log.info("总共写入了 " + total + " 条记录");

		wb.write(out);
		out.close();
	}
	
	private String floatToStr(float f, int num) {
		String format = "%.?f";
		format = format.replaceAll("\\?", String.valueOf(num));
		return String.format(format, f);
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");
		CrawService cs = (CrawService) context.getBean("ncTableToExcel");

		cs.start();

		cs.close();

	}

}
