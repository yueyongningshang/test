package com.zFrame.design.build.one;

import com.alibaba.fastjson.JSONObject;

public class TestDemo {
    public static void main(String[] args) {
        Director d = new Director();
        Human human = d.createHumanByDirector(new SmartManBulider());
        System.out.println(JSONObject.toJSONString(human));
    }
}
