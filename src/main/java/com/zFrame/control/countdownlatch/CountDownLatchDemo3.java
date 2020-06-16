package com.zFrame.control.countdownlatch;

import java.util.concurrent.CountDownLatch;

import com.zFrame.entity.Student;

public class CountDownLatchDemo3 {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            test();
        }
    }

    public static void test() {
        CountDownLatch count = new CountDownLatch(10);
        Student s = new Student();
        BillRedundancyFieldThread[] threadArray = new BillRedundancyFieldThread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new BillRedundancyFieldThread(count, s, i + "");
            threadArray[i].start();
        }
        try {
            System.out.println("count---------------------start" + count.getCount());
            count.await();
            System.out.println("count---------------------end" + count.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class BillRedundancyFieldThread extends Thread {

        private String i;

        private Student s;

        private final CountDownLatch end;

        public BillRedundancyFieldThread(CountDownLatch end, Student s, String i) {
            this.end = end;
            this.s = s;
            this.i = i;
        }

        @Override
        public void run() {
            try {
                handleRedundancyFieldForThread(s, i);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                end.countDown();
                System.out.println("runcount------****************-----" + end.getCount());
            }
        }

        private void handleRedundancyFieldForThread(Student s2, String i2) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----------业务数据处理--------------");
        }
    }
}
