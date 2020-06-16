// package com.zFrame.control.activeMq.demo2;
//
// import javax.jms.Destination;
// import javax.jms.Queue;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jms.core.JmsMessagingTemplate;
// import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.PostMapping;
//
// @Service("producerQueue")
// public class ProducerQueue {
//
// @Autowired
// private JmsMessagingTemplate jmsMessagingTemplate;
//
// @Autowired
// private Queue queue;
//
// @Autowired
// private Queue queueItem;
//
// @PostMapping("/queue/send")
// public String sendQueue(String str) {
// this.sendMessage(this.queue, str);
// return "success";
// }
//
// @PostMapping("/queueItem/send")
// public String sendQueueItem(String str) {
// this.sendMessage(this.queueItem, str);
// return "success";
// }
//
// // 发送消息，destination是发送到的队列，message是待发送的消息
// private void sendMessage(Destination destination, final String message) {
// jmsMessagingTemplate.convertAndSend(destination, message);
// }
//
// }
