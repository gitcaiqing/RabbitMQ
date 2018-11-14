package com.sc.queuemode.topics;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sc.queuemode.connection.ConnectionUtil;

public class Producter {
	
	private static final String EXCHANGE_NAME = "topic_exchange";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel =connection.createChannel();
		//声明交换机类型为topic
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		
		String message = "发布了一条中国新闻消息";
		channel.basicPublish(EXCHANGE_NAME, "china.news", null, message.getBytes());
		
		message = "发布了一条中国天气消息";
		channel.basicPublish(EXCHANGE_NAME, "china.weather", null, message.getBytes());
		
		message = "发布了一条美国新闻消息";
		channel.basicPublish(EXCHANGE_NAME, "usa.news", null, message.getBytes());
		
		message = "发布了一条美国天气消息";
		channel.basicPublish(EXCHANGE_NAME, "usa.weather", null, message.getBytes());
	}

}
