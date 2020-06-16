package com.zFrame.design.factory.abstrac;

public class WindowsFactory implements InterFactory {

    @Override
    public CPU createCPU() {
        return new WindowsCPU();
    }

    @Override
    public RAM createRAM() {
        return new WindowsRAM();
    }

}
