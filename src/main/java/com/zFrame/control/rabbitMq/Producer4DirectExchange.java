package com.zFrame.control.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：Producer4DirectExchange    
 * 类描述：生产者    
 * 创建人：Gz    
 * 创建时间：2020年5月27日 上午11:21:40    
 * 修改人：Gz    
 * 修改时间：2020年5月27日 上午11:21:40    
 * 修改备注：    
 * @version     
 *
 */
public class Producer4DirectExchange {
    public static void main(String[] args) throws Exception {
        // 1、创建connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        // 2 创建Connection
        Connection connection = connectionFactory.newConnection();
        // 3 创建Channel
        Channel channel = connection.createChannel();
        // 4 声明
        // 交换机名称
        String exchangeName = "test_direct_exchange";
        // 路由键
        String routingKey = "test.direct";
        // 5 发送
        for (int i = 0; i < 5; i++) {
            String msg = "Hello World RabbitMQ 4  Direct Exchange Message ... " + i;
            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes());
            System.out.println("send: " + msg);
        }
        channel.close();
        connection.close();
    }
}
