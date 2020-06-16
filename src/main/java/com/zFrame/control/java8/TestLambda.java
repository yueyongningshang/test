package com.zFrame.control.java8;

public class TestLambda {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("lbw----NB!");
            }
        }).start();
        // 使用Lambda来创建线程
        new Thread(() -> test()).start();
    }

    private static void test() {
        // TODO Auto-generated method stub
    }
}
