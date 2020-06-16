package com.zFrame.control;

import com.zFrame.exception.TestException;

public class TestExceptionControl {

    public static void main(String[] args) {
        try {
            throw new TestException("code", "message");
        } catch (TestException e) {
            System.out.println("222");
        } catch (Exception e) {
            System.out.println("11111");
        }
    }

}
