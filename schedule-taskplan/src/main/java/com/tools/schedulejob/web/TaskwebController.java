package com.tools.schedulejob.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskwebController {
	
	protected static Logger logger = LoggerFactory.getLogger(TaskwebController.class);

	
    @RequestMapping("/hi")
	public String hello(Locale locale, Model model) {
		model.addAttribute("greeting", "Hello!");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);        
		String formattedDate = dateFormat.format(date);
		model.addAttribute("currentTime", formattedDate);
		
		logger.info("/hi 测试成功"); 
		return "hello";
	}
	
    @RequestMapping("/hello")
	public String helloweb(Locale locale, Model model) {
    	logger.info("/hello 测试成功"); 
		return "hello world";
	}
    
    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        logger.info("/uid 测试成功"); 
        return session.getId();
    }


}