package com.zFrame.control;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadTestControl {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 创建一个固定线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(1);

    // 向kafka里推送消费
    public static void push2Kafka(Object msg) {
        executor.execute(() -> {
            try {
                // 模拟 占用的内存大小
                Byte[] bytes = new Byte[1024 * 1000 * 1000];
                System.out.println(Thread.currentThread().getName() + "-->任务放到线程池:" + msg);
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {

        // 模拟高并发环境下 一直向线程池里面不停的塞任务
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            System.out.println("塞任务start..." + i);
            push2Kafka(i);
            System.out.println("塞任务end..." + i);
        }

    }

}
