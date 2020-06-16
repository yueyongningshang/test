package com.zFrame.control.lock;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zFrame.service.TestService;
import com.zFrame.util.ThreadPoolConfig;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：TestLockControl    
 * 类描述：悲观锁、乐观锁控制层    
 * 创建人：Gz    
 * 创建时间：2019年12月10日 上午11:02:55    
 * 修改人：Gz    
 * 修改时间：2019年12月10日 上午11:02:55    
 * 修改备注：    
 * @version     
 *
 */
@Controller
@RequestMapping("/lock")
public class TestLockControl {

    // 前提条件：并发修改同一记录时，避免更新丢失，要么在应用层加锁，要么在缓存加锁，要么在 数据库层使用乐观锁，使用 version 作为更新依据。
    // 说明：如果每次访问冲突概率小于 20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次 数不得小于 3 次。

    @Resource
    ThreadPoolConfig config;

    /**
     * 例如涉及到金额，先查查查！后改！的时候。
     */

    @Resource
    private TestService testService;

    @PostMapping("/v1/testLock/notLock")
    public void notLock() {
        // 1.模拟单线程
        // testService.notLock();
        // 2.模拟并发
        testService.notLockConcurrency();
        System.out.println("未加锁执行");
    }

    // 模拟十个人同时操作一笔数据，并且需要十个人的操作全部需要成功
    // annotation+aop 实现重试
    @PostMapping("/v1/testLock/optimistic")
    public void testOptimisticLock() {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    testService.testOptimisticLock();
                    System.out.println("乐观锁---执行完毕");
                }
            });
            config.submit(t);
        }
    }

    // 悲观锁：
    // 1.数据库:一般使用select...for update对所选择的数据进行加锁处理,例如select * from account
    // where name=”Max” for update,这条sql 语句锁定了account 表中所有符合检索条件（name=”Max”）的记录。
    // 本次事务提交之前（事务提交时会释放事务过程中的锁），外界无法修改这些记录。
    // 2.程序 ：synchronized
    @PostMapping("/v1/testLock/pressimistic")
    public void testPressimisticLock() {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    testService.testPressimisticLock();
                    System.out.println("悲观锁---执行完毕");
                }
            });
            config.submit(t);
        }

        // testService.testPressimisticLock();
        // System.out.println("悲观锁---执行完毕");
    }

}
