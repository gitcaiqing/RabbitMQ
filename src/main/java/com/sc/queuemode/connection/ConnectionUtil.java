package com.sc.queuemode.connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectionUtil {

	public static Connection getConnection() throws IOException, TimeoutException {
		//定义连接池
		ConnectionFactory connectionFactory = new ConnectionFactory();
		//定义连接地址
		connectionFactory.setHost("localhost");
		//定义连接端口
		connectionFactory.setPort(5672);
		//用户名
		connectionFactory.setUsername("guest");
		//密码
		connectionFactory.setPassword("guest");
		//通过连接工厂获取连接
		Connection connection = connectionFactory.newConnection();
		//返回连接
		return connection;
	}
}
