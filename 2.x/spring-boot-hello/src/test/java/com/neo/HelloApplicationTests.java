package com.neo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println("Hello Spring Boot 2.0!");
	}

}
