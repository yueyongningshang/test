package com.zFrame.control.countdownlatch;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

//   https://www.jianshu.com/p/f030aa5d7a28
public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException, IOException {
        // 核心线程池大小
        int corePoolSize = 2;
        // 最大线程池大小
        int maximumPoolSize = 4;
        // 线程最大空闲时间
        long keepAliveTime = 10;
        // 时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        // 线程等待队列
        // ArrayBlockingQueue:一个由数组结构组成的有界阻塞队列(数组结构可配合指针实现一个环形队列)。
        // BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        // LinkedBlockingQueue:一个由链表结构组成的有界阻塞队列，而在未指明容量时，容量默认为Integer.MAX_VALUE即无界阻塞队列队列，如果任务提交速度持续大余任务处理速度，会造成队列大量阻塞。因为队列很大，很有可能在拒绝策略前，内存溢出。是其劣势；
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
        // 线程创建工厂
        ThreadFactory threadFactory = new NameTreadFactory();
        // 拒绝策略
        RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue, threadFactory, handler);
        // executor.prestartAllCoreThreads(); // 预启动所有核心线程

        for (int i = 1; i <= 10; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            executor.execute(task);
        }
        executor.shutdown();
        System.in.read(); // 阻塞主线程
    }

    static class NameTreadFactory implements ThreadFactory {

        // 高并发的情况下，i++无法保证原子性，往往会出现问题，所以引入AtomicInteger类。
        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
            // System.out.println("completedTaskCount: " +
            // e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(
                        Thread.currentThread().getName() + "----------------------" + this.toString() + " is running!");
                Thread.sleep(3000); // 让任务执行慢点
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
