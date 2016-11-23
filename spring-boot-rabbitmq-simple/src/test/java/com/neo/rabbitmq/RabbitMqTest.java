package com.neo.rabbitmq;

import com.neo.rabbit.HelloSender;
import com.neo.rabbit.NeoReceiver;
import com.neo.rabbit.NeoSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

	@Autowired
	private HelloSender helloSender;

	@Autowired
	private NeoSender neoSender;

	@Test
	public void hello() throws Exception {
		helloSender.send();
	}

	@Test
	public void neo() throws Exception {
		neoSender.send();
	}

}