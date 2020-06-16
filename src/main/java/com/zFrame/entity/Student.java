package com.zFrame.entity;

public class Student {
    public String phoneNum;

    private String name;

    private int age;

    private int sex;

    public Student(String name, int age, int sex) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public Student(String phoneNum, String name, int age, int sex) {
        this.phoneNum = phoneNum;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    // 有一个参数的构造方法
    public Student(char name) {
        System.out.println("姓名：" + name);
    }

    @Override
    public Student clone() {
        Student stuent = new Student(this.phoneNum, this.name, this.age, this.sex);
        return stuent;
    }

    public Student() {
        super();
        // System.out.println("无参构造");
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    // **************成员方法***************//
    public void show1(String s) {
        System.out.println("调用了：公有的，String参数的show1(): s = " + s);
    }

    protected void show2() {
        System.out.println("调用了：受保护的，无参的show2()");
    }

    void show3() {
        System.out.println("调用了：默认的，无参的show3()");
    }

    private String show4(int age) {
        System.out.println("调用了，私有的，并且有返回值的，int参数的show4(): age = " + age);
        return "abcd";
    }

}
