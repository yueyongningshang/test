package com.zFrame.design.factory.factory;

public class AppleFactory implements MyFactory {

    @Override
    public Product createProduct() {
        return new Apple();
    }

}
