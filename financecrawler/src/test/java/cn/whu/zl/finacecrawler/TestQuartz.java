package cn.whu.zl.finacecrawler;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestQuartz {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-quartz.xml");
		
		System.out.println("end");

	}

}
