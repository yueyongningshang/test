package com.zFrame.design.factory.abstrac;

public class AppleFactory implements InterFactory {

    @Override
    public CPU createCPU() {
        return new AppleCPU();
    }

    @Override
    public RAM createRAM() {
        return new AppleRAM();
    }

}
