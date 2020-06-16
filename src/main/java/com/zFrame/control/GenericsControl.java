package com.zFrame.control;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zFrame.entity.Student;
import com.zFrame.entity.Teacher;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：GenericsAndReflectControl    
 * 类描述：泛型    
 * 创建人：Gz    
 * 创建时间：2019年2月13日 上午11:10:36    
 * 修改人：Gz    
 * 修改时间：2019年2月13日 上午11:10:36    
 * 修改备注：    
 * @version     
 *
 */
public class GenericsControl {

    /**
     * 第一，声明一个泛型类或泛型方法。
     * 第二，使用泛型类或泛型方法。
     * 类型参数“<T>”主要用于第一种，声明泛型类或泛型方法。
     * 无界通配符“<?>”主要用于第二种，使用泛型类或泛型方法
     */
    class Test<T> {

    }

    class Test2 {
    }

    /**
     * 1.泛型中占位符T和?有什么区别 https://blog.csdn.net/woshizisezise/article/details/79374460
     */

    // show1方法中我们使用了T，大家都知道这是泛型的常见写法，那么这里的T指的是某一类具体的对象，list集合里只能存放同一类型数据，如果插入不同类型数据则会报错。
    public static <T> void show1(List<T> list) {
        for (Object object : list) {
            System.out.println(JSONObject.toJSONString(object));
        }
    }

    // show2方法中我们使用的是？，可以看到在void前面并没有<T>，？可以表示成占位符，它自己也不知道list集合中会存放多少种类型的数据，
    // 所以这样就表明我们的list中存放N种数据类型也是可以的。
    public static void show2(List<?> list) {
        for (Object object : list) {
            System.out.println(JSONObject.toJSON(object));
        }
    }

    // 只接受该类及其子类 或泛型
    public static void show3(List<? extends Teacher> list) {
        for (Object object : list) {
            System.out.println(JSONObject.toJSON(object));
        }
    }

    // 只接受该类及其父类 或泛型
    public static void show4(List<? super Teacher> list) {
        for (Object object : list) {
            System.out.println(JSONObject.toJSON(object));
        }
    }

    public static void main(String[] args) {
        List<Student> list1 = new ArrayList<Student>();
        list1.add(new Student("zhangsan", 18, 0));
        list1.add(new Student("lisi", 28, 0));
        list1.add(new Student("wangwu", 24, 1));
        // 这里如果add(new Teacher(...));就会报错，因为我们已经给List指定了数据类型为Student
        show1(list1);

        System.out.println("************分割线**************");

        // 这里我们并没有给List指定具体的数据类型，可以存放多种类型数据
        List list2 = new ArrayList<>();
        list2.add(new Student("zhaoliu", 22, 1));
        list2.add(new Teacher("sunba", 30, 0));
        show2(list2);

        System.out.println("************分割线**************");
        List<Teacher> list3 = new ArrayList<Teacher>();
        show3(list3);// list2 或 list3 必须是teacher类或子类
    }

}
