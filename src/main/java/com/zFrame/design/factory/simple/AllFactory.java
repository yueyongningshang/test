package com.zFrame.design.factory.simple;

public class AllFactory {

    static Product createAppleProduct() {
        return new Apple();
    }

    static Product createWindowsProduct() {
        return new Windows();
    }
}
