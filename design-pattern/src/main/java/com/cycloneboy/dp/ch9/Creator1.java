package com.cycloneboy.dp.ch9;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public class Creator1 extends AbstractCreator {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
