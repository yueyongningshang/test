package com.zFrame.quartz;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class TestCronService {

    // https://blog.csdn.net/zhaominpro/article/details/84561966
    // https://www.jianshu.com/p/1defb0f22ed1
    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd hh:mm:ss");
    // 备注：
    // 消息队列 https://blog.csdn.net/kangbin825/article/details/53966618
    // https://blog.csdn.net/he90227/article/details/50800646
    // https://blog.csdn.net/weixin_30546189/article/details/98344215

    // 当然了，构建一个合理的线程池也是一个关键，否则提交的任务也会在自己构建的线程池中阻塞
    ExecutorService service = Executors.newFixedThreadPool(5);

    /**每五秒钟重试发送错误的信息 必须在运行类中添加@EnableScheduling
    * SpringBoot使用@scheduled定时执行任务的时候是在一个单线程中，如果有多个任务，其中一个任务执行时间过长，
    * 则有可能会导致其他后续任务被阻塞直到该任务执行完成。也就是会造成一些任务无法定时执行的错觉
    */
    @Scheduled(cron = "*/1 * * * * ?")
    public void test() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info(e.toString());
        }
        log.info("每五秒执行一次 one" + " " + fdf.format(new Date()));
    }

    @Scheduled(cron = "*/1 * * * * ?")
    @Async // 异步调用
    public void test2() {
        log.info("每五秒执行一次 two" + " " + fdf.format(new Date()));
    }

    @Scheduled(cron = "*/1 * * * * ?")
    public void test3() {
        log.info("每五秒执行一次 three" + " " + fdf.format(new Date()));
    }

    @Scheduled(cron = "0/1 * * * * ? ")
    public void deleteFile() throws InterruptedException {
        service.execute(() -> {
            // log.info("111delete success, time:" + new Date().toString());
            try {
                Thread.sleep(1000 * 5);// 改成异步执行后，就算你再耗时也不会印象到后续任务的定时调度了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    @Scheduled(cron = "0/1 * * * * ? ")
    public void syncFile() {
        service.execute(() -> {
            // log.info("222sync success, time:" + new Date().toString());
        });
    }

    public TestCronService() {
        System.out.println("TestCronService");
    }
}
