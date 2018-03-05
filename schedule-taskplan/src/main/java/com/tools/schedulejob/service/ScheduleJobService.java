package com.tools.schedulejob.service;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

//这个@Service很关键
@Service
public class ScheduleJobService {

	protected static Logger logger=LoggerFactory.getLogger(ScheduleJobService.class);  
	
  //通过schedulerFactory获取一个调度器
  SchedulerFactory schedulerfactory = new StdSchedulerFactory();

  Scheduler scheduler = null;

  //添加任务
  public void addJob(String paramJobName , String paramJobGroupName ) throws SchedulerException{
      // 通过schedulerFactory获取一个调度器
      scheduler = schedulerfactory.getScheduler();


      //初始化任务(ListenJob.class)
      JobDetail job = JobBuilder.newJob(ShedulejobListenjobService.class).withIdentity(paramJobName,paramJobGroupName).build();

      //设置任务开始时间
      Date startTime = new Date();

      //设置任务结束时间(开始时间一小时后)
      Date endTime = new Date(startTime.getTime()+1000*60*60);

      //初始化任务的触发器(起止时间，repeatSecondlyForever(1)表示每秒执行一次)
      SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().startAt(startTime).endAt(endTime).withIdentity("triggerName", "triggerGroupName")      .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).repeatForever()).build();

      //添加任务到调度器
      scheduler.scheduleJob(job, simpleTrigger);

      //开始执行
      if(!scheduler.isStarted()){
    	  logger.info(paramJobName, ": 定时任务启动");  
          scheduler.start();
      }  
  }


  //暂停任务
  public void pauseJob(String paramJobName , String paramJobGroupName ){

      //调度器为空
      if(scheduler==null){
          return;
      }
      try {
          //触发器和任务都要暂停
          //scheduler.pauseTrigger(new TriggerKey(paramJobName,paramJobGroupName));
          //scheduler.pauseJob(new JobKey("jobName"));      
    	  logger.info(paramJobName, ": 定时任务停止");  
          JobKey jobKey = JobKey.jobKey(paramJobName,paramJobGroupName);  
          scheduler.pauseTrigger(new TriggerKey(paramJobName,paramJobGroupName));
          scheduler.pauseJob(jobKey);
      } catch (SchedulerException e) {
          e.printStackTrace();
      }   
  }

  //恢复调度器
  public void resumeJob(String paramJobName , String paramJobGroupName){

      
      if(scheduler==null){
          return;
      }
      //
      try {
          //scheduler.resumeJob(new JobKey("jobName"));
          //scheduler.resumeTrigger(new TriggerKey(paramJobName, paramJobGroupName));
      	  logger.info(paramJobName, ": 定时任务恢复");   
          JobKey jobKey = JobKey.jobKey(paramJobName,paramJobGroupName);  
          scheduler.resumeJob(jobKey);
          scheduler.resumeTrigger(new TriggerKey(paramJobName, paramJobGroupName));
      } catch (SchedulerException e) {
          e.printStackTrace();
      }   
  }

  //删除任务
  public void deleteJob( String paramJobName , String paramJobGroupName ){
	  
	  logger.info(paramJobName, ": 定时任务删除 deleteJob" );  
	  
      if(scheduler==null){
    	  logger.info(paramJobName, ": 定时任务删除  scheduler==null " );  
          return;
      }
      //JobKey对象
      //JobKey jobKey = new JobKey("jobName");
      JobKey jobKey = JobKey.jobKey(paramJobName,paramJobGroupName);  
      //触发器
      TriggerKey triggerKey = TriggerKey.triggerKey(paramJobName, paramJobGroupName);
      try {
    	  logger.info(paramJobName, ": 定时任务  停止触发器  移除触发器  删除任务 ");   
          // 停止触发器
          scheduler.pauseTrigger(triggerKey);
          // 移除触发器
          scheduler.unscheduleJob(triggerKey);
          //删除任务
          scheduler.deleteJob(jobKey);
      } catch (SchedulerException e) {
          e.printStackTrace();
      }       
  }

  //修改任务
  public void alterJob( String  newTriggerName, String  newTriggerGroupName, String paramJobName , String paramJobGroupName){
      // 创建一个新的触发器
      TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
      // 新触发器名,触发器组
      triggerBuilder.withIdentity(newTriggerName, newTriggerGroupName);
      // 新触发器条件设置
      triggerBuilder.startNow();           

triggerBuilder.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2).repeatForever());

      // 创建Trigger对象
      SimpleTrigger trigger = (SimpleTrigger)triggerBuilder.build();

      //旧的触发器
      TriggerKey triggerKey = TriggerKey.triggerKey(paramJobName, paramJobGroupName);

      try {
    	  logger.info(paramJobName, ": 修改触发器"); 
          //修改触发器
          scheduler.rescheduleJob(triggerKey, trigger);
      } catch (SchedulerException e) {
          e.printStackTrace();
      }
  }   
}
