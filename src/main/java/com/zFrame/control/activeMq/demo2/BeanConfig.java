package com.zFrame.control.activeMq.demo2;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

@Configuration
public class BeanConfig {
    // 默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

    // 默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    // 默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;

    // 货代业务归集后生成新的申请单调用接口自动拆分成本 -- 按申请单处理
    @Value("cost_listen")
    private String queueName;

    // 货代业务归集后申请单分录审批后调用接口自动拆分成本 -- 按申请单分录处理
    @Value("cost_listen_item")
    private String queueNameItem;

    // 申请单处理队列
    @Bean(name = "queue")
    public Queue queue() {
        System.out.println("===============queue=====================");
        return new ActiveMQQueue(queueName);
    }

    // 申请单分录处理队列
    @Bean(name = "queueItem")
    public Queue queueItem() {
        System.out.println("===============queueItem=====================");
        return new ActiveMQQueue(queueNameItem);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        System.out.println("===============connectionFactory=====================");
        return new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
    }

    @Bean
    public JmsMessagingTemplate jmsMessageTemplate() {
        System.out.println("===============jmsMessageTemplate=====================");
        return new JmsMessagingTemplate(connectionFactory());
    }

    // // 在Queue模式中，对消息的监听需要对containerFactory进行配置
    @Bean("queueListener")
    public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(false);
        System.out.println("===============queueJmsListenerContainerFactory=====================");
        return factory;
    }

}
