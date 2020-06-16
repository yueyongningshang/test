package com.zFrame.design.proxy.dynamic;

public class Teacher implements People {

    @Override
    public String education() {
        System.out.println("学历本科以上");
        return "学历本科";
    }

    @Override
    public String work() {
        System.out.println("教书育人");
        return "教书";
    }

}
