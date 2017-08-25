package com.cycloneboy.dp.ch10;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public abstract  class AbstractClass {

    protected  abstract void doSomething();

    protected  abstract void doAnything();

    public void templateMethod(){
        this.doSomething();
        this.doAnything();
    }
}
