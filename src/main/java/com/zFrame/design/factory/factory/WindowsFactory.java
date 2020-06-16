package com.zFrame.design.factory.factory;

public class WindowsFactory implements MyFactory {

    @Override
    public Product createProduct() {
        return new Windows();
    }

}
