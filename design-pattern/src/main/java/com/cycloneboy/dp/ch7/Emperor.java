package com.cycloneboy.dp.ch7;

/**
 * 单例模式:恶汉模式
 * Created by CycloneBoy on 2017/8/18.
 */
public class Emperor {
    private static final Emperor emperor = new Emperor(); //初始化一个皇帝

    private Emperor() {
        //世俗道德约束你，目的就是不希望产生第二个皇帝
    }

    public static Emperor getInstance(){
        return  emperor;
    }

    //皇帝发话了
    public static void say(){
        System.out.println("我是皇帝某某某....");
    }

}
