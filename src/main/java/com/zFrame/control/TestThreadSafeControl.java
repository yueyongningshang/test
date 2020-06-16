package com.zFrame.control;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：TestThreadSafeControl    
 * 类描述：线程安全问题    
 * 创建人：Gz    
 * 创建时间：2019年11月21日 下午3:06:24    
 * 修改人：Gz    
 * 修改时间：2019年11月21日 下午3:06:24    
 * 修改备注：    
 * @version     
 *
 */
public class TestThreadSafeControl {

    // 关于线程安全：
    // 1） 不会变的常量是线程安全的，因为只存在读操作。
    // 2）每次调用方法前都新建一个实例是线程安全的，因为不会访问共享的资源。
    // 3）局部变量是线程安全的。因为每执行一个方法，都会在独立的空间创建局部变量，它不是共享的资源。局部变量包括方法的参数变量和方法内变量。

}

// 有状态bean。 存在可存储属性 a，如果值改变，设计线程安全问题(除非new 对象)
class hasStateBean {
    private String a;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

}

// 无状态bean。
class TestDao {

    public void setA(String a) {
    }

}