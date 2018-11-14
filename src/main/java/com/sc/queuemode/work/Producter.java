package com.sc.queuemode.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.sc.queuemode.connection.ConnectionUtil;

public class Producter {
	
	private final static String QUEUE_NAME = "work_queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		//获取连接
		Connection connection = ConnectionUtil.getConnection();
		//声明信道
		Channel channel = connection.createChannel();
		//队列申明,durable：true消息持久化
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		//消息内容
		String message = getMessage(args);
		for(int i = 0; i < 20; i++) {
			//发布消息
			//basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
			channel.basicPublish("", QUEUE_NAME, null, (i + " " +message).getBytes());
		}
		//关闭通道
		channel.close();
		//连接关闭
		connection.close();
	}
	
	private static String getMessage(String[] strings) {
		if(strings.length < 1) {
			return "Hello World!";
		}
		return joinStrings(strings, "");
	}
	
	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if(length == 0) return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for(int i = 0; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
