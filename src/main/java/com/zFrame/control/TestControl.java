package com.zFrame.control;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class TestControl {

    public final static String TEST_PARAM = "11111111";

    public static void main(String[] args) {
        BigDecimal zero1 = BigDecimal.valueOf(0);
        BigDecimal zero2 = BigDecimal.valueOf(0.00);
        if (zero1.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("zero1");
        }
        if (zero2.compareTo(BigDecimal.ZERO) == 0) {
            System.out.println("zero2");
        }
        System.out.println("*****************************************");

        Map<String, BigDecimal> aMap = new HashMap<String, BigDecimal>();
        aMap.put("a", null);
        System.out.println(aMap.get("a"));
        System.out.println("*****************************************");
        // 1.split
        String str = "a,b,c,,";
        // "，"隔开的最后一个参数为null，那么只能截取到前面为非空的字段。
        String[] strArray = str.split(",", -1);
        System.out.println(strArray.length);

        // 2.equals判断
        String a = null;
        if (TEST_PARAM.equals(a)) {
            System.out.println("11111111111111111111111");
        }
        if (a.equals(TEST_PARAM)) {
            System.out.println("2222222222222");
        }

        // date
        Instant i = Instant.now();

    }

}
