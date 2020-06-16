package com.zFrame.design.build.abs;

public class Person extends Animal {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void sleep() {
        System.out.println("人可以睡觉。");
    }

    @Override
    public void language() {
        // TODO Auto-generated method stub

    }
}
