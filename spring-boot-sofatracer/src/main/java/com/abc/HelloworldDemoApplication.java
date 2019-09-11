package com.abc; /**
 * @author fangpc
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloworldDemoApplication  {

    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(HelloworldDemoApplication.class);
        springApplication.run();
    }
}