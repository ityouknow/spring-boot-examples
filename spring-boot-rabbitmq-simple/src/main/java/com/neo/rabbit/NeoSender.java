package com.neo.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class NeoSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "neo test queue";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("neo", context);
	}

}