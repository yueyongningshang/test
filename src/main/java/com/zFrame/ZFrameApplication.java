package com.zFrame;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 开启定时任务
// @ComponentScan("com.zFrame")
@MapperScan("com.zFrame.dao")
public class ZFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZFrameApplication.class, args);
    }

}
