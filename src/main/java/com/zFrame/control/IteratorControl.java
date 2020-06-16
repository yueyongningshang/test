package com.zFrame.control;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.zFrame.entity.Student;

public class IteratorControl extends BaseData {

    public static void main(String[] args) {

        Map<String, List<Student>> map = list.stream().collect(Collectors.groupingBy(Student::getPhoneNum));
        Iterator<Entry<String, List<Student>>> stuList = map.entrySet().iterator();
        while (stuList.hasNext()) {
            Entry<String, List<Student>> stu = stuList.next();
            System.out.println("stu.getKey()----" + stu.getKey() + " stu.getValue()----" + stu.getValue());
        }

        Iterator<Entry<String, String>> mapTwo = strMap.entrySet().iterator();
        while (mapTwo.hasNext()) {
            Entry<String, String> strEntry = mapTwo.next();
            System.out.println("key-----------" + strEntry.getKey() + " value------------" + strEntry.getValue());

        }
    }

}
