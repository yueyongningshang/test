package com.zFrame.control.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 重试注解（异常重试，乐观锁更新失败重试）
 */
@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedTryAgain {

    /**
     * 重试次数 最小设置为3 
     * @return
     */
    int tryTimes() default 5;

}
