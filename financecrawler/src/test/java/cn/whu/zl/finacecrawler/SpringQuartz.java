package cn.whu.zl.finacecrawler;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component("SpringQuartz")
public class SpringQuartz{
	@PostConstruct
	public void start() throws Exception{
		
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

		  Scheduler sched = schedFact.getScheduler();

		  sched.start();

		  // define the job and tie it to our HelloJob class
		  JobDetail job = newJob(QuartzClass.class)
		      .withIdentity("myJob", "group1")
		      .build();

		  // Trigger the job to run now, and then every 40 seconds
		  Trigger trigger = newTrigger()
		      .withIdentity("myTrigger", "group1")
		      .startNow()
		      .withSchedule(simpleSchedule()
		          .withIntervalInSeconds(2)
		          .repeatForever())
		      .build();
		  
		  // Tell quartz to schedule the job using our trigger
		  sched.scheduleJob(job, trigger);
		
		
	} 
}
