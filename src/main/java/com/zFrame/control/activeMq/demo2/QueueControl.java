// package com.zFrame.control.activeMq.demo2;
//
// import javax.annotation.Resource;
//
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
//
// @RestController
// public class QueueControl {
// @Resource
// private ProducerQueue producerQueue;
//
// /**
// * sendQueue(模拟发送消息使用)
// * @param vo
// * @return
// * @return String
// * @author wuwenjin
// * @date 2020年4月24日 下午2:35:21
// * @Exception 异常对象
// * @version 1.0
// */
// @PostMapping("/v1/queue/send")
// public String sendQueue(@RequestBody String str) {
// int num = 5;
// System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> 生产者将发送" + num + "条消息 : " +
// str);
// producerQueue.sendQueueItem(str);
// return "success";
// }
// }
