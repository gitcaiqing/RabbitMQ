package com.sc.queuemode.routing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sc.queuemode.connection.ConnectionUtil;

public class Producter {
	
	private final static String EXCHANGE_NAME = "routing_exchange";

	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//声明交换机Exchange类型为direct
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		
		//发布消息3种routingKey的消息
		String message = "hello info";
		channel.basicPublish(EXCHANGE_NAME, "info", null, message.getBytes());
		
		message = "hello warning";
		channel.basicPublish(EXCHANGE_NAME, "warning", null, message.getBytes());
		
		message = "hello error";
		channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes());
	}
}
