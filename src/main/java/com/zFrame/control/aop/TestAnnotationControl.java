package com.zFrame.control.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.zFrame.entity.AnnotationEntity;

public class TestAnnotationControl {
    @AnnotationInter(id = "1", name = "test1")
    public void test() {
        System.out.println("go ---- test------ method");
    }

    public static void main(String[] args) {
        Class<?> c = TestAnnotationControl.class;
        // 获得声明中所有方法
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            // 获得所有声明的注解
            AnnotationInter inter = method.getDeclaredAnnotation(AnnotationInter.class);
            if (inter != null) {
                System.out.println("Found annotation 1:" + inter.id() + " " + inter.name());
            }
        }
        // 获得声明中所有属性
        Class<?> entityC = AnnotationEntity.class;
        Field[] fields = entityC.getDeclaredFields();

        for (Field field : fields) {
            AnnotationInter inter = field.getDeclaredAnnotation(AnnotationInter.class);
            if (inter != null) {
                System.out.println("Found annotation 2.1:" + inter.id() + " " + inter.name());
            }
        }

        // 第二种麻烦
        for (Field field : fields) {
            Annotation[] annotationArray = field.getAnnotations();
            for (Annotation annotation : annotationArray) {
                if (annotation instanceof AnnotationInter) {
                    AnnotationInter interTwo = (AnnotationInter) annotation;
                    System.out.println("Found annotation 2.2:" + interTwo.id() + " " + interTwo.name());
                }
            }
        }

    }
}
