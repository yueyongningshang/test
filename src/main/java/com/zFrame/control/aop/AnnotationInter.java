package com.zFrame.control.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：AnnotateControl    
 * 类描述：注解    
 * 创建人：Gz    
 * 创建时间：2019年11月1日 下午4:06:59    
 * 修改人：Gz    
 * 修改时间：2019年11月1日 下午4:06:59    
 * 修改备注：    
 * @version     
 *
 */
// 自定义切面接口。相当于一个切面
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationInter {

    /**
     * @Target:annotation(注释) 注释作用对象
     * 
     * retention:保持 保留  retention policy 保持原则  Annotation:注解
     * 1、RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；
     * 2、RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；
     * 3、RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；
     * 这3个生命周期分别对应于：Java源文件(.java文件) ---> .class文件 ---> 内存中的字节码。
     * 那怎么来选择合适的注解生命周期呢？
     * 首先要明确生命周期长度 SOURCE < CLASS < RUNTIME ，所以前者能作用的地方后者一定也能作用。
     * 一般如果需要在运行时去动态获取注解信息，那只能用 RUNTIME 注解，比如@Deprecated使用RUNTIME注解
     * 如果要在编译时进行一些预处理操作，比如生成一些辅助代码（如 ButterKnife），就用 CLASS注解；
     * 如果只是做一些检查性的操作，比如 @Override 和 @SuppressWarnings，使用SOURCE 注解。
     */

    public String id() default "1";

    public String name() default "empty name";
}
