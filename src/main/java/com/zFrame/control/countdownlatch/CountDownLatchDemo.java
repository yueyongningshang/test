package com.zFrame.control.countdownlatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//   https://blog.csdn.net/zqyoncemore/article/details/795656
public class CountDownLatchDemo {

    // 线程池大小决定了一次运行多少个线程
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            test();
        }

    }

    public static void test() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Service service = new Service();
            // CountDownLatch begin = new CountDownLatch(1);// 裁判员鸣枪信号
            CountDownLatch end = new CountDownLatch(10);// 10名参赛选手结束信号
            MyThread[] threadArray = new MyThread[10];
            for (int i = 0; i < 10; i++) {
                // threadArray[i] = new MyThread(service, begin, end);
                threadArray[i] = new MyThread(service, end);
                threadArray[i].setName((i + 1) + " 号选手 ");
                threadArray[i].start();
                // executor.execute(threadArray[i]);
            }
            executor.shutdown();
            if (executor.isTerminated()) {
                System.out.println("线程池执行结束");
            }
            System.out.println("在等待裁判员鸣枪  " + sdf.format(new Date()));
            long t1 = System.currentTimeMillis();// 记录比赛的开始时间
            // begin.countDown();// 裁判员鸣枪了
            end.await();// 等待10个参赛选手都跑完100米
            long t2 = System.currentTimeMillis();// 记录比赛的结束时间
            System.out.println("所有参赛选手都完成了100米赛跑，赛跑总耗时是  " + (t2 - t1));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
