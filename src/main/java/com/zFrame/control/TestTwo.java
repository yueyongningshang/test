package com.zFrame.control;

import java.util.concurrent.CountDownLatch;

public class TestTwo {

    public static void main(String[] args) {
        CountDownLatch count = new CountDownLatch(2);
        String str;
        Thread thread1, thread2 = null;
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1" + " is running...");
                count.countDown();
                System.out.println("Thread1 " + " is finished...");
            }
        });
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2" + " is running...");
                count.countDown();
                System.out.println("Thread2" + " is finished...");
            }
        });
        thread1.start();
        thread2.start();
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All is finished.");
    }
}
