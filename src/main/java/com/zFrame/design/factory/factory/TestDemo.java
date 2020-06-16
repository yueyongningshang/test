package com.zFrame.design.factory.factory;

public class TestDemo {

    public static void main(String[] args) {
        MyFactory apple = new AppleFactory();
        apple.createProduct().build();
    }
}
