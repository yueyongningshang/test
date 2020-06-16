package com.zFrame.design.factory.abstrac;

public class TestDemo {

    public static void main(String[] args) {
        InterFactory f = new WindowsFactory();
        f.createCPU().CPUbuild();
        f.createRAM().RAMbuild();
    }

}
