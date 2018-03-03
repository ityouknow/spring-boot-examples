package com.neo.config;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import com.neo.domain.Cron;
import com.neo.domain.CronRepository;
import com.neo.task.SchedulerJob;


@Configuration
@EnableScheduling
@Component
public class CompleteScheduleConfig implements SchedulingConfigurer {
 

	  
		@Autowired
		private CronRepository cronRepository;
 
  /*
  @Autowired
  @SuppressWarnings("all")
  CronMapper cronMapper;
  */
	
  /**
   * 执行定时任务.
   */
  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

	  //2.1 从数据库获取执行周期
	  List<Cron> crons=cronRepository.findAll();
	  
		SchedulerJob schedulerJob = new SchedulerJob();
	  
	  
	  
	  	for(Cron cron: crons )
	  	{

	  		try {
				schedulerJob.addJob();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		taskRegistrar.addTriggerTask(
	  		        //1.添加任务内容(Runnable)
	  		        //() -> System.out.println("执行定时任务: [cron_id=" + cron.getCron_id() + "]" ),
	  				() -> System.out.println(" " ),
	  		        //2.设置执行周期(Trigger)
	  		        triggerContext -> {
	  		         
	  		          String crontime = cron.getCron();
	  		          //2.2 合法性校验.
	  		          if (StringUtils.isEmpty(crontime)) {
	  		            //Omitted Code ..
	  		          }
	  		          //2.3 返回执行周期(Date)
	  		          return new CronTrigger( crontime ).nextExecutionTime(triggerContext);
	  		        }
	  		    );
	  		
	  	}
	  	
	  	
  
    
  }
 
}
