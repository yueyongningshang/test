package com.zFrame.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：ThreadPoolConfig    
 * 类描述：线程池管理    
 * 创建人：Gz    
 * 创建时间：2019年12月11日 上午9:59:45    
 * 修改人：Gz    
 * 修改时间：2019年12月11日 上午9:59:45    
 * 修改备注：    
 * @version     
 *
 */
@Component
public class ThreadPoolConfig {
    private static final int MAX_SIZE = 10;

    private static final int CORE_SIZE = 5;

    private static final int SECOND = 1000;

    private ThreadPoolExecutor executor;

    public ThreadPoolConfig() {
        executor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, SECOND, TimeUnit.MICROSECONDS,
                new LinkedBlockingQueue<Runnable>());
    }

    public void submit(Thread thread) {
        executor.submit(thread);
    }
}
