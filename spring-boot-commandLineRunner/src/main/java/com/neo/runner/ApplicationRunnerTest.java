package com.neo.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//ApplicationRunner 和 CommandLineRunner 功能一样
@Component
public class ApplicationRunnerTest implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("The ApplicationRunner start to initialize ...----------------");
    }
}