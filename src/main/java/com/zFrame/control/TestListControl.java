package com.zFrame.control;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.zFrame.entity.Student;

public class TestListControl {
    public static void main(String[] args) {
        Student stuA1 = new Student("16666666666", "name1", 24, 1);
        Student stuA2 = new Student("18888888888", "name2", 25, 2);
        ArrayList<Student> listA = new ArrayList<Student>();
        ArrayList<Student> copyList = new ArrayList<Student>();
        listA.add(stuA1);
        listA.add(stuA2);
        System.out.println(JSONObject.toJSON(listA));
        // 1.浅copy，指针指向一个地方。listB值改变,listA值亦改变
        // ArrayList<Student> listB = listA;
        // for (Student student : listB) {
        // student.setPhoneNum("111111111111");
        // }
        // System.out.println(JSONObject.toJSON(listA));

        // 2.深copy 注：对象没有实现clone方法，虽然listA与listC引用地址不一样，但存储对象一样
        ArrayList<Student> listC = (ArrayList<Student>) listA.clone();
        for (Student student : listC) {
            listC.add(student.clone());
        }

        // List<String> str = new ArrayList<>();
        // str.add("a");
        // str.add("c");
        // for (String string : str) {
        // for (Student student : listA) {
        // student.setPhoneNum(string);
        // }
        // }
        // System.out.println(JSONObject.toJSON(listA));

        List<String> str = new ArrayList<>();
        str.add("a");
        str.add("c");
        for (String string : str) {
            System.out.println("************main");
            for (Student student : listA) {
                System.out.println("************item");
                setObjectOne(copyList, student, string);
                student.setPhoneNum(string);
            }
        }
        System.out.println(JSONObject.toJSON(copyList));

    }

    private static void setObjectOne(ArrayList<Student> copyList, Student student, String string) {
        Student stuA1 = new Student();
        stuA1.setAge(student.getAge());
        stuA1.setName(student.getName());
        stuA1.setPhoneNum(string);
        stuA1.setSex(student.getSex());
        copyList.add(stuA1);
    }
}
