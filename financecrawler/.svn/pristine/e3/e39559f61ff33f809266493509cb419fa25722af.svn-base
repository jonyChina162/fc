/**
 * 
 */
package cn.whu.zl.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import cn.whu.zl.service.CrawService;

/**
 * @author B506-13-1
 *
 */
@Component("FcQuartzClass")
public class FcQuartzClass {
	//单机版的定时器
	//需要定时执行的类添加进来就可以，顺序执行。
	public void start(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-jpa.xml");
		CrawService ncCs = (CrawService) context.getBean("nctableSer");
		ncCs.start();
		ncCs.close();
		CrawService ncExcelCs = (CrawService) context.getBean("ncTableToExcel");
		ncExcelCs.start();
		ncExcelCs.close();
		CrawService smCs = (CrawService) context.getBean("SendMail");
		smCs.start();
		smCs.close();
	}
}
