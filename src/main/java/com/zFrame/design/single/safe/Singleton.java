package com.zFrame.design.single.safe;

public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }// 为避免被其他类创建实例，该构造函数设为私有

    synchronized public static Singleton getInstance() {
        // synchronize是java中用于加锁的关键字
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    // 定义一个show()方法表示一下成功创建
    public void show() {
        System.out.println("懒汉式单例模式已运行,线程安全");
    }

}
