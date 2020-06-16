package com.zFrame.control.activeMq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：ProducerController    
 * 类描述：队列消息控制器--生产者    
 * 创建人：Gz    
 * 创建时间：2019年12月20日 下午2:43:23    
 * 修改人：Gz    
 * 修改时间：2019年12月20日 下午2:43:23    
 * 修改备注：    
 * @version     
 *
 */
@Api(value = "队列消息控制器--生产者")
@RestController
@Slf4j
public class ProducerController {
    @Autowired
    private JmsTemplate jmsTemplate;

    /*
     * 消息生产者
     */
    @GetMapping("/sendmsg")
    public void sendmsg(String msg) {
        // 指定消息发送的目的地及内容
        this.jmsTemplate.convertAndSend("info_listen", msg);
    }

    @GetMapping("/send")
    public void send(String msg) {
        // 指定消息发送的目的地及内容
        System.out.println("@@@@@@@@@@@@@@" + msg);
        this.jmsTemplate.convertAndSend("info_listen", msg);
    }

}
