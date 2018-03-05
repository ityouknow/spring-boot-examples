package com.tools.schedulejob.web;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tools.schedulejob.service.ScheduleJobService;

@RestController
public class ScheduleJobController {

	protected static Logger logger = LoggerFactory.getLogger(ScheduleJobController.class);

	@Autowired
	private ScheduleJobService schedulejobservice;
	
	// 添加任务
	@RequestMapping("/addJob")
	public void addJob() throws SchedulerException {
		// 初始化任务名和组名
		String jobName = "jobName";
		String jobGroupName = "jobGroupName";

		logger.info("/addJob 添加任务");
		schedulejobservice.addJob(jobName, jobGroupName);
		
		//保存数据库
		
	}

	// 暂停任务
	@RequestMapping("/pauseJob")
	public void pauseJob() {

		// 初始化任务名和组名
		String jobName = "jobName";
		String jobGroupName = "jobGroupName";

		logger.info("/pauseJob 暂停任务");
		schedulejobservice.pauseJob(jobName, jobGroupName);
	}

	// 恢复调度器
	@RequestMapping("/resumeJob")
	public void resumeJob() {
		// 初始化任务名和组名
		String jobName = "jobName";
		String jobGroupName = "jobGroupName";

		logger.info("/resumeJob 恢复调度器");
		schedulejobservice.resumeJob(jobName, jobGroupName);
	}

	// 删除任务
	@RequestMapping("/deleteJob")
	public void deleteJob() {
		// 初始化任务名和组名
		String jobName = "jobName";
		String jobGroupName = "jobGroupName";

		logger.info("/deleteJob 删除任务");
		schedulejobservice.deleteJob(jobName, jobGroupName);
	}

	// 修改任务
	@RequestMapping("/alterJob")
	public void alterJob() {

		// 初始化任务名和组名
		String jobName = "jobName";
		String jobGroupName = "jobGroupName";
		
		// 初始化任务名和组名
		String newTriggerName = "newJobName";
		String newTriggerGroupName = "newJobGroupName";

		logger.info("/deleteJob 删除任务");
		schedulejobservice.alterJob(newTriggerName, newTriggerGroupName, jobName, jobGroupName);
	}
}
