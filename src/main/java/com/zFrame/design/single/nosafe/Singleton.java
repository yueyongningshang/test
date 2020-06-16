package com.zFrame.design.single.nosafe;

public class Singleton {

    private static final Singleton instance = new Singleton();

    private Singleton() {// 为避免被其他类创建实例，该构造函数设为私有。
    }

    public static Singleton getInstance() {
        return instance;
    }

    // 定义一个show()方法标识一下承购创建
    public void show() {
        System.out.println("饿汉式单例模式已运行");
    }

}
