package com.zFrame.control;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(5);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        List<Future<String>> futureTaskList = new ArrayList<Future<String>>(5);
        for (int i = 0; i < 5; i++) {
            futureTaskList.add(threadPool.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    latch.countDown();
                    return "ok";
                }
            }));
        }
        try {
            latch.await();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        try {
            for (int i = 0; i < futureTaskList.size(); i++) {
                if (i == 0 && "ok".equals(futureTaskList.get(i).get())) {

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
