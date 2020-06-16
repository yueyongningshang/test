package com.zFrame.control.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.zFrame.entity.Student;

/**
 * 
 *     
 * 项目名称：zFrame    
 * 类名称：TestJava8Stream    
 * 类描述：java 8 新特征 stream   
 * 创建人：Gz    
 * 创建时间：2019年6月14日 下午4:19:27    
 * 修改人：Gz    
 * 修改时间：2019年6月14日 下午4:19:27    
 * 修改备注：    
 * @version     
 *
 */
public class TestJava8StreamControl {

    public static void main(String[] args) {
        Student stuA = new Student("15806631533", "name1", 24, 1);
        Student stuB = new Student("12222222222", "name2", 25, 0);
        Student stuC = new Student("13333333333", "name3", 26, 0);
        Student stuD = new Student("14444444444", "name4", 27, 1);
        Student stuE = new Student("15555555555", "name5", 28, 1);
        Student stuF = new Student("16666666666", "name5", 28, 1);
        Student stuG = new Student("16666666666", "name51", 28, 1);
        List<Student> list = new ArrayList<Student>();
        list.add(stuA);
        list.add(stuB);
        list.add(stuC);
        list.add(stuD);
        list.add(stuE);
        list.add(stuF);
        list.add(stuG);
        // 数组集合.stream.collect() toList(Collectors.XXXXX) 数组集合流化收集器作用
        // 将Steam类型转换为集合
        // 1.流转list
        String[] arr = { "aa", "ccc", "sss", "sss" };
        List<String> listStrs = Arrays.stream(arr).collect(Collectors.toList());
        System.out.println("1.流转list：listStrs--list--" + JSONObject.toJSONString(listStrs));
        // 2.流转set 去重
        Set<String> setStrs = Arrays.stream(arr).collect(Collectors.toSet());
        System.out.println("2.流转set：setStrs--set--" + JSONObject.toJSONString(setStrs));
        // 3.流转collection
        Set<Student> collectSet = list.stream().collect(Collectors.toCollection(HashSet::new));
        List<Student> collectList = list.stream().collect(Collectors.toCollection(ArrayList::new));
        List<String> names = list.stream().map(Student::getName).collect(Collectors.toCollection(ArrayList::new));
        String nameStr = list.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println("3.流转collection：nameStr-----" + nameStr);
        // 4.计算流中元素个数
        Long count = list.stream().collect(Collectors.counting());
        System.out.println("4.计算流中元素个数：count--" + count);
        // 5.对流中某个整数属性求和
        // 通过 `::` 关键字来访问类的构造方法，对象方法，静态方法
        Integer sumAges = list.stream().collect(Collectors.summingInt(Student::getAge));
        Double doubleAges = list.stream().collect(Collectors.summingDouble(Student::getAge));
        Double averagAges = list.stream().collect(Collectors.averagingDouble(Student::getAge));
        System.out.println("5.对流中某个整数属性求和：" + sumAges);
        System.out.println("5.对流中某个整数属性求和：" + doubleAges);
        System.out.println("5.对流中某个整数属性平均值：" + averagAges);
        // 6.reduce 聚合函数求值
        int sumAgesReduce = list.stream().map(Student::getAge).reduce(0, (a, b) -> a + b);
        System.out.println("6.reduce 聚合函数求值:" + sumAgesReduce);
        // 7.包裹另一个转换器，对其结果进行转换函数
        // 去重
        List<Student> distinctClass = list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<Student>(Comparator.comparing(o -> o.getName() + ";" + o.getAge()))),
                        ArrayList<Student>::new));
        System.out.println("7.collectingAndThen 去重 list--distinct：" + JSONObject.toJSONString(distinctClass));
        // 8.根据用户名分组 只是一个key值
        Map<String, List<Student>> groupByName = list.stream().collect(Collectors.groupingBy(Student::getPhoneNum));
        // 根据多个字段分组
        Map<String, List<Student>> collect = list.stream()
                .collect(Collectors.groupingBy(e -> e.getAge() + e.getName()));
        System.out.println("8.分组groupByName: " + JSONObject.toJSONString(groupByName));
        System.out.println("8.分组collect: " + JSONObject.toJSONString(collect));
        // 8.1 list->转map 注意：key不能有重复 不然会报Duplicate key 重复
        Map<String, Student> listToMap = list.stream()
                .collect(Collectors.toMap(s -> s.getPhoneNum() + s.getName(), a -> a));
        System.out.println("8.1 listToMap--------------" + JSON.toJSONString(listToMap));
        Map<String, String> listToMap2 = list.stream().collect(Collectors.toMap(Student::getPhoneNum,
                Student::getPhoneNum, (aLong, aLong2) -> aLong2.equals(aLong) ? aLong2 : aLong));
        System.out.println("8.2 listToMap--------------" + JSON.toJSONString(listToMap2));
        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<String, Student> listToMap3 = list.stream()
                .collect(Collectors.toMap(Student::getPhoneNum, a -> a, (k1, k2) -> k1));
        System.out.println("8.3 listToMap--------------" + JSON.toJSONString(listToMap3));
        // 9.filter():过滤条件
        List<Student> filterList = list.stream().filter(student -> student.getSex() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("9.filter():过滤条件: " + JSONObject.toJSONString(filterList));
        System.out.println("***********************************");
        // peek 基本用于调试，没有返回值。
        // map 有返回值，处理之后返回map 处理的参数list集合
        List<Integer> list1 = list.stream()
                .peek(e -> System.out.println("1.Filtered value: " + JSONObject.toJSONString(e))).map(Student::getAge)
                .peek(e -> System.out.println("2.Mapp value: " + e)).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("list----" + JSONObject.toJSONString(list1));
        System.out.println("***********************************");
        // 10.将集合中TestDemo 对象name值以逗号方式隔开转为字符串
        String lastName = list.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(lastName);
        // 11.排序 注意：如果是Set。不管用！Set<Student>
        List<Student> listSorder = list.stream().sorted(Comparator.comparing(Student::getAge))
                .collect(Collectors.toList());
        System.out.println(JSON.toJSON(listSorder));
        // 12.数组中重复数量
        long a = listStrs.stream().distinct().count();
        System.out.println("a---" + a);
        // 12.2
        System.out.println(JSONObject.toJSON(list));
        List<String> distinct = list.stream().filter(distinctByKey(Student::getName)).map(Student::getName)
                .collect(Collectors.toList());
        System.out.println(distinct);
    }

    static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
