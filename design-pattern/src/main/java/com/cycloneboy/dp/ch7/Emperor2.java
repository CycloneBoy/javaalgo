package com.cycloneboy.dp.ch7;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;
import java.util.Random;

/**
 * 单例模式:恶汉模式
 * Created by CycloneBoy on 2017/8/18.
 */
public class Emperor2 {
    //定义最多能产生的实例对象
    private static int maxNumOfEmperor = 2;
    // 每个皇帝都有名字,使用一个ArrayList来容纳，每个对象的私有变量
    private static ArrayList<String> nameList = new ArrayList<String>();
    //定义一个列表，容纳所有的皇帝实例
    private static ArrayList<Emperor2> emperor2List = new ArrayList<Emperor2>();
    //当前皇帝序列号
    private static int countNumOfEmperor = 0;
    //产生所有的对象
    static{
        for(int i = 0; i < maxNumOfEmperor; i ++){
            emperor2List.add(new Emperor2("皇"+ (i+1) + "帝"));
        }
    }
    private static final Emperor2 emperor = new Emperor2(); //初始化一个皇帝

    private Emperor2() {
        //世俗道德约束你，目的就是不希望产生第二个皇帝
    }
    //传入皇帝名称，建立一个皇帝对象
    private Emperor2(String name) {
        nameList.add(name);
    }

    //随机获得一个皇帝
    public static Emperor2 getInstance(){
        Random random = new Random();
        //随机拉出一个皇帝，只要是个精神领袖就成
        countNumOfEmperor = random.nextInt(maxNumOfEmperor);
        return  emperor2List.get(countNumOfEmperor);
    }

    //皇帝发话了
    public static void say(){
        System.out.println(nameList.get(countNumOfEmperor));
    }

}
