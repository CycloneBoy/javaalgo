package com.cycloneboy.dp.ch9;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public abstract class AbstractCreator {
    //创建A产品家族
    public abstract AbstractProductA createProductA();
    //创建B产品家族
    public abstract AbstractProductB createProductB();

}
