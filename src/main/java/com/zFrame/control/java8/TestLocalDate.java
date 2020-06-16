package com.zFrame.control.java8;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TestLocalDate {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println(localDate.toString() + " " + localTime.toString());

        // **************************************************
        // 线程安全
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate now = LocalDate.now();
        String paramDate = "2020-06-09";

        String paramDate2 = "2020-06-17";

        LocalDate svnAfter = now.plusDays(7);
        LocalDate TwoBefore = now.minusDays(4);
        LocalDate paramLocal1 = LocalDate.parse(paramDate, dtf);
        System.out.println(svnAfter);

        LocalDate paramLocal2 = LocalDate.parse(paramDate2, dtf);

        System.out.println(svnAfter.isBefore(paramLocal1));

        System.out.println(svnAfter.isBefore(paramLocal2));

        // https://www.cnblogs.com/qingyunfc/p/10236734.html
    }

}
