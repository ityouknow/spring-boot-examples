package com.neo.web;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.neo.model.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
    @RequestMapping("/hello")
	public String hello(Locale locale, Model model) {
		return "Hello World";
	}

}