package com.zFrame.control;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.zFrame.entity.Student;

public class BaseData {
    static Student stuA = new Student("15806631533", "name1", 24, 1);

    static Student stuB = new Student("12222222222", "name2", 25, 0);

    static Student stuC = new Student("13333333333", "name3", 26, 0);

    static Student stuD = new Student("14444444444", "name4", 27, 1);

    static Student stuE = new Student("15555555555", "name5", 28, 1);

    static Student stuF = new Student("16666666666", "name5", 28, 1);

    static Student stuG = new Student("16666666666", "name5", 28, 1);

    static List<Student> list = new ArrayList<Student>();

    static String a = "a";

    static String b = "b";

    static String c = "c";

    static String d = "d";

    static List<String> strList = new ArrayList<>();

    static Map<String, String> strMap = new HashMap<String, String>();

    static {
        list.add(stuA);
        list.add(stuB);
        list.add(stuC);
        list.add(stuD);
        list.add(stuE);
        list.add(stuF);
        list.add(stuG);

        strList.add(a);
        strList.add(b);
        strList.add(c);
        strList.add(d);

        strMap.put("num1", "value1");
        strMap.put("num2", "value2");
        strMap.put("num3", "value3");
        strMap.put("num4", "value4");
    }

    @Autowired
    protected HttpServletRequest request;

    public static void main(String[] args) {
        try {
            Class<?> cla = stuA.getClass();
            Field field = cla.getDeclaredField("phoneNum");
            field.setAccessible(true);
            System.out.println(field.get(stuA).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
