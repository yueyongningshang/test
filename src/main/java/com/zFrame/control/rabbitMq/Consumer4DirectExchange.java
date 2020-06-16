package com.zFrame.control.rabbitMq;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Consumer4DirectExchange {
    public static void main(String[] args) throws Exception {

        // 1、创建connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");

        connectionFactory.setAutomaticRecoveryEnabled(true);
        connectionFactory.setNetworkRecoveryInterval(3000);
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        // 4 声明
        // 交换机名
        String exchangeName = "test_direct_exchange";
        //
        String exchangeType = "direct";
        // 队列名
        String queueName = "test_direct_queue";
        // 路由键
        String routingKey = "test.direct";

        // 表示声明了一个交换机
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        // 表示声明了一个队列
        channel.queueDeclare(queueName, false, false, false, null);
        // 建立一个绑定关系:
        channel.queueBind(queueName, exchangeName, routingKey);

        channel.basicQos(64);// 设置客户端最多接收未被ack的消息个数
        Consumer consumer1 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                System.out.println("resv msg:" + new String(body));
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 消息确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 回调： 参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName, false, consumer1);
        // 等待回调函数执行完毕之后，关闭资源
        TimeUnit.SECONDS.sleep(15);
        channel.close();
        connection.close();
    }
}
