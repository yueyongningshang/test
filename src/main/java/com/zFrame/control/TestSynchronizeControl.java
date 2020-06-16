package com.zFrame.control;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zFrame.service.TestService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：TestSynchronizeControl    
 * 类描述：synchronize关键字
 * 创建人：Gz    
 * 创建时间：2019年11月21日 下午1:48:30    
 * 修改人：Gz    
 * 修改时间：2019年11月21日 下午1:48:30    
 * 修改备注：    
 * @version     
 *
 */
@RestController
// 协议集描述 作用类
@Api(value = "测试锁-数据库事务")
@Slf4j
public class TestSynchronizeControl {

    // 创建一个固定线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    // static修饰的属性，类。不依赖类特定的实例，被类的所有实例共享，存储在内存中的静态区(全局区)。可以直接引用。但是，多线程中，如果需要同步改值，就会涉及到线程安全问题
    // 1、静态全局变量也称为类变量，属于类对象所有，位于方法区，为所有对象共享，共享一份内存，一旦值被修改，则其他对象均对修改可见，故线程非安全
    public static int a = 123;

    /**
     * 2、实例变量是实例对象私有的，系统只存在一个实例对象，则在多线程环境下，如果值改变后，则其它对象均可见，故线程非安全；
     * 如果每个线程都在不同的实例对象中执行，则对象与对象间的修改互不影响，故线程安全。
     * 单例时线程非安全，非单例时线程安全(非单例重新new 对象)
     */
    // SimpleDateFormat 是线程不安全的。如果多线程并发，需要重新new SimpleDateFormat 或者 加锁
    // format方法在运行过程中改动了SimpleDateFormat的calendar字段，所以，它是有状态的。
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private TestService testService;

    /**
     * 
     * testNotLockInter(开启两个线程调用没有锁的方法：此时打印的日志是每个线程异步的。)  
     * @throws InterruptedException
     * @return void
     * @author Gz
     * @date 2019年11月21日 下午1:48:01  
     * @Exception 异常对象  
     * @version 1.0
     */
    @GetMapping("/v1/testSynchronize/testNotLockInter")
    public void testNotLockInter() throws InterruptedException {
        Thread one = new Thread(() -> {
            try {
                testNotLock("lock", 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread two = new Thread(() -> {
            try {
                testNotLock("lock", 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        two.start();
    }

    /**
     * 
     * testLockInter(开启两个线程调用加锁：此时打印的日志是每个线程同步的，一个线程执行完才会执行另一个线程。)  
     * @return void
     * @author Gz
     * @date 2019年11月21日 下午1:48:52  
     * @Exception 异常对象  
     * @version 1.0
     */
    @GetMapping("/v1/testSynchronize/testLockInter")
    public void testLockInter() {
        Thread one = new Thread(() -> {
            try {
                testLock("lock", 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread two = new Thread(() -> {
            try {
                testLock("lock", 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        one.start();
        two.start();
    }

    /**
     * 
     * moreRequestTimesNotLock(多次访问接口时，不加锁：postMan多次请求设置是单个线程，不起作用，如果是jmeter 开启多个线程是可以测试出)  
     * @return void
     * @author Gz
     * @date 2019年11月21日 下午1:48:52  
     * @Exception 异常对象  
     * @version 1.0
     * @throws InterruptedException 
     */
    @GetMapping("/v1/testSynchronize/moreRequestTimesNotLock")
    public void moreRequestTimesNotLock() throws InterruptedException {
        testNotLock("lock", 1);
    }

    /**
     * 
     * moreRequestTimesLock(多次访问接口时，加锁：postMan)  
     * @return void
     * @author Gz
     * @date 2019年11月21日 下午1:48:52  
     * @Exception 异常对象  
     * @version 1.0
     * @throws InterruptedException 
     */
    @GetMapping("/v1/testSynchronize/moreRequestTimesLock")
    public void moreRequestTimesLock() throws InterruptedException {
        testLock("lock", 1);
    }

    private void testLock(String lock, int num) throws InterruptedException {
        synchronized (lock) {
            System.out.println("testTwo***********************" + num);
            testService.testTwo(num);
        }
    }

    private void testNotLock(String lock, int num) throws InterruptedException {
        System.out.println("testTwo***********************" + num);
        testService.testTwo(num);
    }

    @GetMapping("/v1/testSynchronize/testPoolLockInter")
    public void testPoolLockInter() throws InterruptedException {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    testLock("lock", 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    testLock("lock", 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    @GetMapping("/v1/testSynchronize/testPoolNotLockInter")
    public void testPoolNotLockInter() throws InterruptedException {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    testNotLock("lock", 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    testNotLock("lock", 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
    }
}
