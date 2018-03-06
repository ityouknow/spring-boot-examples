package com.tools.schedulejob.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tools.schedulejob.domain.User;
import com.tools.schedulejob.domain.UserRepository;

@RestController
public class TaskController {
	
	protected static Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping("/getUser")
    @Cacheable(value="user-key")
    public User getUser() {
    	User user=userRepository.findByUserName("aa");
    	logger.info("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return user;
    }
    
    @RequestMapping("/getUsers")
    @Cacheable(value="key-Users")
    public List<User> getUsers() {
    	List<User> users=userRepository.findAll();
    	logger.info("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
        return users;
    }
}