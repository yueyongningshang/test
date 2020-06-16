package com.zFrame.design.build.abs;

public abstract class Animal implements Action {

    private int age;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public abstract void sleep();

    public void notAbstract() {
        System.out.println("非抽象方法，不用继承，但多态可以访问。子类调用方法都为此有共同特点时，添加这一遍即可");
    }

}
