package com.neo.web;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * Spring动态周期定时任务<br>
 * 在不停应用的情况下更改任务执行周期
 * @Author 许亮
 * @Create 2016-11-10 16:31:29
 */
@Lazy(false)
@Component
@EnableScheduling
public class SpringDynamicCronTask implements SchedulingConfigurer {
	private static final Logger logger = LoggerFactory.getLogger(SpringDynamicCronTask.class);
	
	private static String cron;
	
	public SpringDynamicCronTask() {
		cron = "0/9995 * * * * ?";
		
		// 开启新线程模拟外部更改了任务执行周期
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				cron = "0/9991 * * * * ?";
				//System.err.println("cron change to: " + cron);
			}
		}).start();
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(new Runnable() {
			@Override
			public void run() {
				// 任务逻辑
				//System.err.println("dynamicCronTask is running...");
			}
		}, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				// 任务触发，可修改任务的执行周期
				CronTrigger trigger = new CronTrigger(cron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
			}
		});
	}
}