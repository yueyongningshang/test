package com.zFrame.control.a;

import javax.servlet.http.Cookie;

import com.zFrame.entity.Student;

public class TestTest {

    private static Student a;

    public static void main(String[] args) {
        Cookie userCookie = new Cookie("JSESSIONID", "1CBBED7D2B6AEBDDA3776EC2B3005C64");

        userCookie.setMaxAge(30 * 24 * 60 * 60); // 存活期为一个月 30*24*60*60
        userCookie.setPath("/");
        System.out.println(userCookie.getValue());
    }

}
