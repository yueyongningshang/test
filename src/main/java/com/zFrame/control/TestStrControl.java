package com.zFrame.control;

public class TestStrControl {

    public static void main(String[] args) {
        String cellphone = "15585458544";
        String pwd = cellphone.substring(cellphone.length() - 6);
        System.out.println(pwd);
        Integer a = Integer.parseInt("0001") + 1;
        System.out.println(a);
        // 向前补6个零
        String str = String.format("%06d", a);
        System.out.println("Str----------" + str);
    }

}
