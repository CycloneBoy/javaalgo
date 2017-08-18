package com.cycloneboy.dp.ch9;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public abstract class AbstractProductB {
    //每个产品共有的方法
    public void shareMethod(){}

    //每个产品相同的方法,不同实现
    public abstract void doSomething();
}
