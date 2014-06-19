/**
 * 
 */
package cn.whu.zl.mail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.whu.zl.service.BasicCrawServiceImp;
import cn.whu.zl.service.CrawService;
import cn.whu.zl.service.NcTableService;

/**
 * @author B506-13-1
 * 
 */
@Service("SendMail")
public class SendNcExcel extends BasicCrawServiceImp {
	private static final Logger log = Logger.getLogger(NcTableService.class
			.getName());

	public void start() {
		sendThisNcExcel();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jpa.xml");
		CrawService cs = (CrawService) context.getBean("SendMail");

		cs.start();

		cs.close();

	}

	private void sendThisNcExcel() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-mail.xml");
		JavaMailSender sender = (JavaMailSender) context.getBean("mailSender");
		try {
			Date date = new Date();
			Calendar cr = Calendar.getInstance();
			cr.setTime(date);
			if(cr.get(Calendar.HOUR_OF_DAY) < 19) {
				cr.add(Calendar.DAY_OF_YEAR, -1);
				date = cr.getTime();
			}

			String sdate = sdf.format(date);

			String filename = System.getProperty("user.dir")
					+ "/src/main/resources/nctable_" + sdate + ".xls";

			MimeMessage mimeMess = new JavaMailSenderImpl().createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMess,
					true, "utf-8");
			List<InternetAddress> recivers = new ArrayList<>();
			recivers.add(new InternetAddress("676322889@qq.com"));
			recivers.add(new InternetAddress("540319072@qq.com"));
			recivers.add(new InternetAddress("83845884@qq.com"));
			recivers.add(new InternetAddress("693318469@qq.com"));

			int num = recivers.size();
			InternetAddress[] sendTo = new InternetAddress[num];
			recivers.toArray(sendTo);
			messageHelper.setTo(sendTo);
			messageHelper.setFrom("whucsb506@163.com");
			messageHelper.setSubject("牛叉评估"+sdate+"excel表");
			messageHelper.setText(
					"<html><head></head><body> 牛叉评估"+sdate+" 爬取数据见附件</body></html>",
					true);

			File file = new File(filename);
			messageHelper.addAttachment(MimeUtility.encodeWord(file.getName()),
					file);
			sender.send(mimeMess);
			log.info("send success");
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}

	}

	private final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd", Locale.CHINA);
}
