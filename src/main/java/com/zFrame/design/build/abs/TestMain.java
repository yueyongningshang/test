package com.zFrame.design.build.abs;

public class TestMain {

    public static void main(String[] args) {
        Animal p = new Person();
        String a = Action.say;
        a = p.say;
        p.notAbstract();
    }

}
