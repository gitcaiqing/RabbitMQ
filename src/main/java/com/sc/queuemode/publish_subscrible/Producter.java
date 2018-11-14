package com.sc.queuemode.publish_subscrible;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sc.queuemode.connection.ConnectionUtil;

public class Producter {

	private final static String EXCHANGE_NAME = "publishSubscrible_exchange";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		//声明交换机Exchange类型为fanout
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		String message = "publish/subscrible hello world";
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		System.out.println("发布订阅 生产者 发布消息："+message);
		channel.close();
		connection.close();
	}
}
