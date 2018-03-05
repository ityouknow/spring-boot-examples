package com.tools.schedulejob.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tools.schedulejob.domain.Logs;
import com.tools.schedulejob.domain.LogsRepository;

@RestController
public class LogsController {

	protected static Logger logger = LoggerFactory.getLogger(LogsController.class);

	@Autowired
	private LogsRepository logsRepository;


	@RequestMapping("/getLog")
	@Cacheable(value = "log-key")
	public Logs getLogs() {
		Logs logs = logsRepository.findByMethod("/addJob");
		logger.info("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
		return logs;
	}

	@RequestMapping("/getLogs")
	@Cacheable(value = "key-Logs")
	public List<Logs> getLogss() {
		List<Logs> Logs = logsRepository.findAll();
		logger.info("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
		return Logs;
	}

}