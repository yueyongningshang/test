package com.zFrame.control.a;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zFrame.entity.Student;
import com.zFrame.entity.Teacher;

@Component
public class Test {

    @Bean
    public Teacher teacherVo() {
        System.out.println("***************************teacherVo***********************");
        return new Teacher("姓名", 10, 1);
    }

    @Bean
    public Student studentVo() {
        System.out.println("***************************studentVo***********************");
        return new Student(teacherVo().getName(), teacherVo().getAge(), teacherVo().getSex());
    }

    @Bean
    public void systemOut() {
        System.out.println(JSONObject.toJSONString(studentVo()));
    }
}
