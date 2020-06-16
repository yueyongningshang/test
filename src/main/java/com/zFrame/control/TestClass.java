package com.zFrame.control;

import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

import com.zFrame.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestClass {

    public static final FastDateFormat sdf = FastDateFormat.getInstance("yyyy-MM-dd");

    public static void testValue() {
        // 这是因为与String类似，所有的包装类都是final类，即不可变类。
        // 虽然在代码A处看起来是改变了counter的值，但实际上是创建了另一个对象，
        // 并将方法内的counter参数的引用指向了这个新创建的对象，由于是不同的引用，所以不会对方法外的引用有任何的影响。
        String a = "b";
        // final 指的是引用地址不会改变，对于基本类型，值不会改变
        final Student stu = new Student("a", 1, 1);
        int c = 11;
        final int d = 15;
        // d=11;此时编译出错
        testToValue(a, stu, c, d);
        System.out.println("a--------" + a);
        System.out.println("student----" + stu.getAge() + " " + stu.getName());
        System.out.println("c----" + c + " d----" + d);
        c = 12;
        System.out.println("c----" + c + " d----" + d);

        Date dx = new Date();
        nextDateUpdate(dx);
        System.out.println("after nextDateUpdate: " + sdf.format(dx));
        Date date = new Date();
        nextDateReplace(date);
        System.out.println("after nextDateReplace: " + sdf.format(date));
    }

    private static void testToValue(String a, Student s, int c, int d) {
        a = "a";
        s.setAge(2);
        s.setName("b");
        c = 22;
        d = 33;
    }

    private static void testThread() throws InterruptedException {
        Thread.sleep(5 * 1000);
        new Thread(() -> {
            log.info("1111111111111111111111");
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        testValue();
        for (int i = 0; i < 2000; i++) {
            testThread();
        }
    }

    private static void nextDateUpdate(Date d) {
        d.setDate(d.getDate() + 1);
        System.out.println("nextDateUpdate: " + sdf.format(d));
    }

    private static void nextDateReplace(Date date) {
        date = new Date(date.getYear(), date.getMonth(), date.getDate() + 1);
        System.out.println("nextDateReplace: " + sdf.format(date));
    }
}
