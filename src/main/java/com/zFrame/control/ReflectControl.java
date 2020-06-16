package com.zFrame.control;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.zFrame.entity.Student;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：ReflectControl    
 * 类描述：反射的使用   
 * 创建人：Gz    
 * 创建时间：2019年2月13日 下午2:36:51    
 * 修改人：Gz    
 * 修改时间：2019年2月13日 下午2:36:51    
 * 修改备注：    
 * @version     
 *
 */
public class ReflectControl {

    // https://blog.csdn.net/sinat_38259539/article/details/71799078

    /**
     * 获取Class对象的三种方式
     * 1 Object ——> getClass();
     * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
     * 3 通过Class类的静态方法：forName（String  className）(常用) 
     */
    public static void main(String[] args) {
        // 第一种方式获取Class对象
        Student stu1 = new Student();// 这一new 产生一个Student对象，一个Class对象。
        Class<? extends Student> stuClass = stu1.getClass();// 获取Class对象
        System.out.println(stuClass.getName());

        // 第二种方式获取Class对象
        Class<? extends Object> stuClass2 = Student.class;
        System.out.println(stuClass == stuClass2);// 判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        // 第三种方式获取Class对象
        try {
            Class<? extends Object> stuClass3 = Class.forName("com.zFrame.entity.Student");// 注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            System.out.println(stuClass3 == stuClass2);// 判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

class Reflect {

    /**
     * 通过Class对象可以获取某个类中的：构造方法、成员变量、成员方法；并访问成员；
     * 
     * 1.获取构造方法：
     *      1).批量的方法：
     *          public Constructor[] getConstructors()：所有"公有的"构造方法
     *          public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)
     *    
     *      2).获取单个的方法，并调用：
     *          public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
     *          public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；
     *      
     *          调用构造方法：
     *          Constructor-->newInstance(Object... initargs)
     */
    public static void main(String[] args) throws Exception {
        // 1.加载Class对象
        Class clazz = Class.forName("com.zFrame.entity.Student");
        // 使用new关键字时你得保证import了这个驱动类才行，而使用class.formname时你只要保证引用了驱动的jar包就行了。
        // newInstance 弱类型。低效率。只能调用无参构造。
        clazz.newInstance();
        // 2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for (Constructor c : conArray) {
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        // 1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        // 2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        // 调用构造方法
        Object obj = con.newInstance();
        // System.out.println("obj = " + obj);
        // Student stu = (Student)obj;

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(char.class);
        System.out.println(con);
        // 调用构造方法
        con.setAccessible(true);// 暴力访问(忽略掉访问修饰符)
        obj = con.newInstance('男');
    }
}

class Fields {
    /**
     * 获取成员变量并调用：
     * 
     * 1.批量的 1).Field[] getFields():获取所有的"公有字段" 2).Field[]
     * getDeclaredFields():获取所有字段，包括：私有、受保护、默认、公有； 2.获取单个的： 1).public Field
     * getField(String fieldName):获取某个"公有的"字段； 2).public Field
     * getDeclaredField(String fieldName):获取某个字段(可以是私有的)
     * 
     * 设置字段的值： Field --> public void set(Object obj,Object value): 参数说明：
     * 1.obj:要设置的字段所在的对象； 2.value:要为字段设置的值；
     * 
     */
    public static void main(String[] args) throws Exception {
        // 1.获取Class对象
        Class stuClass = Class.forName("com.zFrame.entity.Student");
        // 2.获取字段
        System.out.println("************获取所有公有的字段********************");
        Field[] fieldArray = stuClass.getFields();
        for (Field f : fieldArray) {
            System.out.println(f);
        }
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");
        fieldArray = stuClass.getDeclaredFields();
        for (Field f : fieldArray) {
            System.out.println(f);
        }
        System.out.println("*************获取公有字段**并调用***********************************");
        Field f = stuClass.getField("phoneNum");
        System.out.println("f----------------------:" + f);

        // Class<?> cla = param.getClass();
        // String phoneNum = f.get(stuClass).toString();
        // System.out.println("phoneNumvalue----------------------:" +
        // phoneNum);
        // 获取一个对象
        Object obj = stuClass.getConstructor().newInstance();// 产生Student对象--》Student
                                                             // stu = new
                                                             // Student();
        // 为字段设置值
        f.set(obj, "刘德华");// 为Student对象中的name属性赋值--》stu.name = "刘德华"
        // 验证
        Student stu = (Student) obj;
        System.out.println("验证姓名：" + stu.phoneNum);

        System.out.println("**************获取私有字段****并调用********************************");
        f = stuClass.getDeclaredField("phoneNum");
        System.out.println(f);
        f.setAccessible(true);// 暴力反射，解除私有限定
        f.set(obj, "18888889999");
        System.out.println("验证电话：" + stu);

        System.out.println("**************反射实例：例如用于Excel导出********************************");
        Field[] fieldArray2 = stuClass.getDeclaredFields();
        String[] modelName = new String[fieldArray2.length];
        for (int i = 0; i < fieldArray.length; i++) {
            modelName[i] = fieldArray[i].getName();
        }
        List<Student> list = new ArrayList<>();
        handleData(list);
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            Object object = (Object) it.next();

            for (int i = 0; i < modelName.length; i++) {
                Field field = stuClass.getDeclaredField(modelName[i]);
                field.setAccessible(true);
                System.out.println("it.next()-----------" + it.next());
                // 取得obj对象这个Field上的值
                Object value = field.get(it.next());
                System.out.println(value);
            }
        }
    }

    private static void handleData(List<Student> list) {
        Student stuA = new Student("15806631533", "name1", 24, 1);
        list.add(stuA);
        Student stuB = new Student("12222222222", "name2", 25, 0);
        list.add(stuB);
        Student stuC = new Student("13333333333", "name3", 26, 0);
        list.add(stuC);
    }
}

class MethodClass {
    /**
     * 获取成员方法并调用：
     * 
     * 1.批量的：
     *      public Method[] getMethods():获取所有"公有方法"；（包含了父类的方法也包含Object类）
     *      public Method[] getDeclaredMethods():获取所有的成员方法，包括私有的(不包括继承的)
     * 2.获取单个的：
     *      public Method getMethod(String name,Class<?>... parameterTypes):
     *                  参数：
     *                      name : 方法名；
     *                      Class ... : 形参的Class类型对象
     *      public Method getDeclaredMethod(String name,Class<?>... parameterTypes)
     * 
     *   调用方法：
     *      Method --> public Object invoke(Object obj,Object... args):
     *                  参数说明：
     *                  obj : 要调用方法的对象；
     *                  args:调用方式时所传递的实参；
    ):
     */
    public static void main(String[] args) throws Exception {
        // 1.获取Class对象
        Class stuClass = Class.forName("com.zFrame.entity.Student");
        // 2.获取所有公有方法
        System.out.println("***************获取所有的”公有“方法*******************");
        stuClass.getMethods();
        Method[] methodArray = stuClass.getMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }
        System.out.println("***************获取所有的方法，包括私有的*******************");
        methodArray = stuClass.getDeclaredMethods();
        for (Method m : methodArray) {
            System.out.println(m);
        }
        System.out.println("***************获取公有的show1()方法*******************");
        Method m = stuClass.getMethod("show1", String.class);
        System.out.println(m);
        // 实例化一个Student对象
        Object obj = stuClass.getConstructor().newInstance();
        m.invoke(obj, "刘德华");

        System.out.println("***************获取私有的show4()方法******************");
        m = stuClass.getDeclaredMethod("show4", int.class);
        System.out.println(m);
        m.setAccessible(true);// 解除私有限定
        Object result = m.invoke(obj, 20);// 需要两个参数，一个是要调用的对象（获取有反射），一个是实参
        System.out.println("返回值：" + result);
    }
}