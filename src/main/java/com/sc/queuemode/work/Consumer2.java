package com.sc.queuemode.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.sc.queuemode.connection.ConnectionUtil;

public class Consumer2 {
	private final static String QUEUE_NAME = "work_queue";

	public static void main(String[] args) throws IOException, TimeoutException{
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		
		//同一时刻服务器只发送1条消息给消费者（能者多劳，消费消息快的，会消费更多的消息）
		channel.basicQos(1);
		
		//声明队列的消费者
		Consumer consumer2 = new DefaultConsumer(channel){
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				//channel.basicConsume(QUEUE_NAME, false, consumer1);
				String message = new String(body, "UTF-8");
				System.out.println("customer2 消费消息："+message);
				channel.basicAck(envelope.getDeliveryTag(), false);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		//定义的消费者监听队列(第二个参数：true自动返回结果，false手动返回)
		channel.basicConsume(QUEUE_NAME, false,consumer2);
	}
}
