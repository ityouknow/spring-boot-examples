package com.neo.task;

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
import org.springframework.stereotype.Service;

//这个@Service很关键
@Service
public class SchedulerJob {

  //通过schedulerFactory获取一个调度器
  SchedulerFactory schedulerfactory = new StdSchedulerFactory();

  Scheduler scheduler = null;

  //添加任务
  public void addJob() throws SchedulerException{
      // 通过schedulerFactory获取一个调度器
      scheduler = schedulerfactory.getScheduler();

      //初始化任务名和组名
      String jobName = "jobName";
      String jobGroupName = "jobGroupName";

      //初始化任务(ListenJob.class)
      JobDetail job = JobBuilder.newJob(ListenJob.class).withIdentity(jobName,jobGroupName).build();

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
          scheduler.start();
      }  
  }


  //暂停任务
  public void pauseJob(){
      //调度器为空
      if(scheduler==null){
          return;
      }
      try {
          //触发器和任务都要暂停
          scheduler.pauseTrigger(new TriggerKey("triggerName","triggerGroupName"));
          scheduler.pauseJob(new JobKey("jobName"));          
      } catch (SchedulerException e) {
          e.printStackTrace();
      }   
  }

  //恢复调度器
  public void resumeJob(){
      //
      if(scheduler==null){
          return;
      }
      //
      try {
          scheduler.resumeJob(new JobKey("jobName"));
          scheduler.resumeTrigger(new TriggerKey("triggerName","triggerGroupName"));
      } catch (SchedulerException e) {
          e.printStackTrace();
      }   
  }

  //删除任务
  public void deleteJob(){
      if(scheduler==null){
          return;
      }
      //JobKey对象
      JobKey jobKey = new JobKey("jobName");
      //触发器
      TriggerKey triggerKey = TriggerKey.triggerKey("triggerName", "triggerGroupName");
      try {
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
  public void alterJob(){
      // 创建一个新的触发器
      TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
      // 新触发器名,触发器组
      triggerBuilder.withIdentity("newTriggerName", "newTriggerGroupName");
      // 新触发器条件设置
      triggerBuilder.startNow();           

triggerBuilder.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(2).repeatForever());

      // 创建Trigger对象
      SimpleTrigger trigger = (SimpleTrigger)triggerBuilder.build();

      //旧的触发器
      TriggerKey triggerKey = TriggerKey.triggerKey("triggerName", "triggerGroupName");

      try {
          //修改触发器
          scheduler.rescheduleJob(triggerKey, trigger);
      } catch (SchedulerException e) {
          e.printStackTrace();
      }
  }   
}
