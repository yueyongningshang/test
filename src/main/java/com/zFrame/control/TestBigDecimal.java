package com.zFrame.control;

import java.math.BigDecimal;

public class TestBigDecimal {
    public static void main(String[] args) {
        String a = "0.5";
        String b = "0.6";
        double a1 = 0.5;
        double b1 = 0.6;
        BigDecimal c = new BigDecimal(a).add(new BigDecimal(b));
        System.out.println(c);

        BigDecimal c1 = BigDecimal.valueOf(a1).add(BigDecimal.valueOf(b1));
        System.out.println(c1);
    }
}
