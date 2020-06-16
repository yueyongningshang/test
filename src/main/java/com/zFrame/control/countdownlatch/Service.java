package com.zFrame.control.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Service {
    private static boolean flag1 = false;

    private static volatile boolean flag2 = false;

    public void testMethod() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            System.out.println(Thread.currentThread().getName() + " begin timer " + sdf.format(new Date()));
            Thread.sleep((long) (Math.random() * 10000));// 模拟每个跑步选手跑完100米所需的时间
            System.out.println(Thread.currentThread().getName() + " end timer " + sdf.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!flag1)
                    System.out.println("-----11111--------");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-----22222--------" + flag2);
            }
        }).start();
        flag1 = true;
        flag2 = true;
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag2 = false;
    }
}
