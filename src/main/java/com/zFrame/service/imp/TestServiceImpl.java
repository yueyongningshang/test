package com.zFrame.service.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.zFrame.control.lock.NeedTryAgain;
import com.zFrame.control.lock.TryAgainException;
import com.zFrame.dao.TestDao;
import com.zFrame.entity.CommonData;
import com.zFrame.entity.Student;
import com.zFrame.service.TestService;
import com.zFrame.util.ThreadPoolConfig;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Resource
    ThreadPoolConfig config;

    static {

    }

    @Transactional
    @Override
    public void testOne(int i) {
        System.out.println(i + "*************************" + " testOne：select data is already has");
        List<Student> list = testDao.selectA();
        System.out.println(i + "*************************" + " testOne：into data");
    }

    @Transactional
    @Override
    public void testTwo(int i) {
        int count = 0;
        System.out.println(i + "*************************" + " testTwo：select data is already has");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testDao.selectA();
        System.out.println(i + "*************************" + " testTwo：into data");
    }

    @Transactional
    @Override
    public void notLock() {
        int ron = 10;
        for (int i = 1; i <= 100; i++) {
            CommonData oldData = testDao.selectCommonDataById("0");
            oldData.setFront(oldData.getFront().subtract(new BigDecimal(ron)));
            oldData.setTotal(BigDecimal.valueOf(100));
            testDao.updateCommonDataById(oldData);
            oldData.setId(i + "");
            oldData.setValue(i + "value");
            testDao.insertCommonData(oldData);
        }
    }

    // @Transactional
    @Override
    public void notLockConcurrency() {
        for (int i = 1; i <= 100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    int j = 1;
                    CommonData oldData = testDao.selectCommonDataById("0");
                    int ron = 10;
                    oldData.setFront(oldData.getFront().subtract(new BigDecimal(ron)));
                    oldData.setTotal(BigDecimal.valueOf(100));
                    testDao.updateCommonDataById(oldData);
                    oldData.setId(UUID.randomUUID().toString());
                    oldData.setValue(j + "value");
                    testDao.insertCommonData(oldData);
                    j++;
                }
            });

            config.submit(t);

        }

    }

    /**
     * 业务场景：
     * 对同一笔数据扣款，A,B两个业务员同时操作，
     * 数据库中有一条数据，需要获取到当前的值，在当前值的基础上-10，然后再更新回去。
     * 如果此时有两个线程同时并发处理，第一个线程拿到数据是100，-10=90更新回去。
     * 第二个线程原本是要在第一个线程的基础上再-10=80,结果由于并发访问取到更新前的数据为100，-20=80
     */
    @NeedTryAgain
    @Transactional
    @Override
    public void testOptimisticLock() {

        CommonData oldData = testDao.selectCommonDataById("0");
        int ron = 10;
        log.info("本次消费=" + ron);
        oldData.setFront(oldData.getFront().subtract(BigDecimal.valueOf(ron)));
        int count = testDao.updateCommonDataById(oldData);
        if (count == 0) {
            log.error("更新失败");
            // 保存失败，抛出重试异常
            throw new TryAgainException("1", "乐观锁导致修改失败");
        } else {
            log.info("更新成功");
        }

    }

    // Isolation:事务隔离 保证一个事务修改的数据提交后才能被另外一个事务读取。另外一个事务不能读取该事务未提交的数据。
    // 这种事务隔离级别可以避免脏读出现，但是可能会出现不可重复读和幻像读。
    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public void testPressimisticLock() {
        CommonData oldData = testDao.selectCommonDataByIdPressimisticLock("0");
        int ron = 10;
        log.info("本次消费=" + ron);
        oldData.setFront(oldData.getFront().subtract(BigDecimal.valueOf(ron)));
        int count = testDao.updateCommonDataById(oldData);
        if (count == 0) {
            log.error("更新失败");
            // 保存失败，抛出重试异常
            throw new TryAgainException("1", "乐观锁导致修改失败");
        } else {
            log.info("更新成功");
        }
    }

}
