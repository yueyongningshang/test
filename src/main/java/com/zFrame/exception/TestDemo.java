package com.zFrame.exception;

public class TestDemo {

    public static void main(String[] args) {
        String a = "1";
        System.out.println(TestEnum.TEST_ONE.getCode());
        if (a.equals(TestEnum.TEST_ONE)) {
            throw new TestException("1", "message:......");
        }
    }

}
