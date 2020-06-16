package com.zFrame.control.testThread;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestPlay {

    public static void main(String[] args) {
        Integer a = null;
        System.out.println(a + 1);

        BigDecimal costMain = BigDecimal.valueOf(8987.600000);
        BigDecimal costItem = BigDecimal.valueOf(0.5);
        BigDecimal scale = BigDecimal.valueOf(3);
        System.out.println(costMain.multiply(costItem).divide(scale, 6, BigDecimal.ROUND_HALF_UP));

        String msgBody = "20ZT3062,0038";
        String msgArray[] = msgBody.split(",");
        System.out.println(msgArray.length);
        if (msgArray.length == 2) {
            System.out.println(msgArray[1]);
        }

        List<Integer> integers = new ArrayList<>(5);
        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(4);
        integers.add(5);

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) % 2 == 0) {
                integers.remove(i);
            }
        }

        System.out.println(integers);

        int n = 9;
        System.out.println("f(n)---------" + f(9));

    }

    static int f(int n) {
        // 先写递归结束条件
        if (n <= 2) {
            return 1;
        }
        // 写等价关系式
        return f(n - 1) + f(n - 2);
    }

}
