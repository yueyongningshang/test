package com.zFrame.control.activeMq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerController {

    // 日志
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @JmsListener(destination = "info_listen")
    public void getInfoOne(String boxId) {
        log.info("getInfoOne------boxId:" + boxId);
        this.handleServiceData(boxId);
    }

    @JmsListener(destination = "info_listen")
    public void getInfoTwo(String boxId) {
        log.info("getInfoTwo----boxId:" + boxId);
        this.handleServiceData(boxId);
    }

    @JmsListener(destination = "info_listen")
    public void getInfoThree(String boxId) {
        log.info("getInfoThree----boxId:" + boxId);
        this.handleServiceData(boxId);
    }

    @JmsListener(destination = "info_listen")
    public void getInfoFour(String boxId) {
        log.info("getInfoFour----boxId:" + boxId);
        this.handleServiceData(boxId);
    }

    /**
    * 
    * handleServiceData(执行service业务逻辑)  
    * @param boxId
    * @return void
    * @author Gz
    * @date 2020年4月28日 上午10:49:11  
    * @Exception 异常对象  
    * @version 1.0
    */
    private void handleServiceData(String boxId) {
        try {
            System.out.println("执行service业务逻辑");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
