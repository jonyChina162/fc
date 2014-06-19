package cn.whu.zl.finacecrawler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

@Service("QuartzClass")
public class QuartzClass implements Job{
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		start();
		
	}

	public void start(){
		System.out.println("do it");
	}
}
