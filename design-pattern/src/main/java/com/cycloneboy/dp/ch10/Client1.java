package com.cycloneboy.dp.ch10;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class Client1 {
    public static void main(String[] args) {
        AbstractClass class1 = new ConcreteClass1();
        AbstractClass class2 = new ConcreteClass2();

        //调用模板方法
        class1.templateMethod();
        class2.templateMethod();
    }
}
