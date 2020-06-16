package com.zFrame.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zFrame.entity.Test;
import com.zFrame.service.RedisService;

@RestController
public class RedisControl {

    @Autowired
    private RedisService redisService;

    // list赋值测试
    public void listValue(List<Test> testList) {
        for (int i = 0; i < 3; i++) {
            Test t = new Test();
            t.setName("test" + i);
            t.setNo("no" + i);
            testList.add(t);
        }
    }

    /**
     * 插入缓存数据
     */
    @GetMapping("/testRedis/insert")
    public void set() {
        List<Test> testList = new ArrayList<Test>();
        listValue(testList);
        System.out.println("redisService--------control---------" + redisService);
        redisService.set("testList", testList);
    }

    /**
     * 读取缓存数据
     */
    @GetMapping("/testRedis/get")
    public void get() {
        List<Test> value = (List<Test>) redisService.get("testList");
        System.out.println(JSONObject.toJSONString(value));
    }

    /**
     * 删除缓存数据
     */
    @GetMapping("testRedis/del/{key}")
    public void delete(@PathVariable String key) {
        redisService.remove(key);
    }

}
