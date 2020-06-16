package com.zFrame.service;

public interface TestService {

    /**
     * 
     * testOne(模拟操作数据库)  
     * @return void
     * @author Gz
     * @date 2019年11月20日 下午1:12:47  
     * @Exception 异常对象  
     * @version 1.0
     */
    void testOne(int i);

    /**
     * 
     * testOne(模拟操作数据库)  
     * @return void
     * @author Gz
     * @date 2019年11月20日 下午1:12:47  
     * @Exception 异常对象  
     * @version 1.0
     */
    void testTwo(int i);

    /**
     * 
     * testOptimisticLock(乐观锁测试)  
     * @return void
     * @author Gz
     * @date 2019年12月10日 上午11:23:12  
     * @Exception 异常对象  
     * @version 1.0
     */
    void testOptimisticLock();

    /**
     * 
     * notLock(未加锁执行)  
     * @return void
     * @author Gz
     * @date 2019年12月10日 下午5:10:57  
     * @Exception 异常对象  
     * @version 1.0
     */
    void notLock();

    /**
     * 
     * notLockConcurrency(模拟并发)  
     * @return void
     * @author Gz
     * @date 2019年12月11日 上午9:53:41  
     * @Exception 异常对象  
     * @version 1.0
     */
    void notLockConcurrency();

    /**
     * 
     * testPressimisticLock(悲观锁模拟)  
     * @return void
     * @author Gz
     * @date 2019年12月11日 下午4:23:49  
     * @Exception 异常对象  
     * @version 1.0
     */
    void testPressimisticLock();

}
