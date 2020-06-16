package com.zFrame.control.lock;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * 重试切面
 * @author gourd
 */
@Aspect // 标注增强处理类（切面类）
@Component // 交由Spring容器管理
@Slf4j
public class TryAgainAspect implements Ordered {
    private int maxRetries;

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }

    @Pointcut("@annotation(com.zFrame.control.lock.NeedTryAgain)")
    // @Pointcut("execution(* com.zFrame.service.imp..*.*(..)) ")
    public void retryOnOptFailure() {
    }

    @Around("retryOnOptFailure()")
    @Transactional(rollbackFor = Exception.class)
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("*********into***********切面");
        // 获取拦截的方法名
        MethodSignature msig = (MethodSignature) pjp.getSignature();
        // 返回被织入增加处理目标对象
        Object target = pjp.getTarget();
        // 为了获取注解信息
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        // 获取注解信息
        NeedTryAgain annotation = currentMethod.getAnnotation(NeedTryAgain.class);
        // 设置重试次数
        this.setMaxRetries(annotation.tryTimes());
        // 重试次数
        int numAttempts = 0;
        do {
            numAttempts++;
            try {
                System.out.println("再次执行业务代码");
                return pjp.proceed();
            } catch (TryAgainException ex) {
                System.out.println("再次执行业务代码====错误 次数：" + numAttempts);
                if (numAttempts > maxRetries) {
                    // 如果大于 默认的重试机制 次数，我们这回就真正的抛出去了
                    throw new TryAgainException("1", "服务内部错误-重试结束");
                } else {
                    // 如果 没达到最大的重试次数，将再次执行
                    System.out.println("=== 正在重试=====" + numAttempts + "次");
                }
            }
        } while (numAttempts <= this.maxRetries);

        return null;
    }

    // 实现Ordered接口是为了让这个aop增强的执行顺序在事务之前
    // 或者直接用@Order(0) //设置优先级，值越低优先级越高
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
