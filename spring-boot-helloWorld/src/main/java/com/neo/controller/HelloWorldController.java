package com.neo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    
    /**
     * url传参，访问的路径类似这样：localhost:8080/getParamDemo1/1
     * 方法体中的参数要在前面加注释，@PathVariable，代表url中的参数
     */
    @RequestMapping(path = {"/getParamDemo1/{id}"})
    public String getParamDemo1 (@PathVariable("id") int userId){
        System.out.println("get param " + userId);
        return "success get param";
    }
    /**
     * 当然，你也可以通过这种传参方式：localhost:8080/getParamDemo?param1=1或者直接表单提交参数
     * 当然，同时方法中参数声明的注释也要变成@RequestParam，代表请求参数，required属性说明了参数是否是必须的
     */
    @RequestMapping(path = {"/getParamDemo2"})
    public String getParamDemo2 (@RequestParam(value="param1",required = false) int param){
        System.out.println("get param " + param);
        return "success get param";
    }
}