package com.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"com.elasticsearch"})
@RestController
public class App
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class,args);
    }
}
