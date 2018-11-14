package com.sc.queuemode.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sc.queuemode.connection.ConnectionUtil;

public class Producter {
	
	private final static String QUEUE_NAME = "hello_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		//获取连接
		Connection connection = ConnectionUtil.getConnection();
		//声明信道
		Channel channel = connection.createChannel();
		//队列申明
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//消息内容
		String message = "simple queue hello world !";
		//推送发布消息
		//basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		//关闭通道
		channel.close();
		//连接关闭
		connection.close();
	}
}
