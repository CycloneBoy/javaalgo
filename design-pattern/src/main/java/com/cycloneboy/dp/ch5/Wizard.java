package com.cycloneboy.dp.ch5;

import java.util.Random;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Wizard {
    private Random rand = new Random(System.currentTimeMillis());
    //第一步
    private int first(){
        System.out.println("执行第一个方法...");
        return rand.nextInt(100);
    }

    //第一步
    private int second(){
        System.out.println("执行第二个方法...");
        return rand.nextInt(100);
    }

    //第一步
    private int third(){
        System.out.println("执行第三个方法...");
        return rand.nextInt(100);
    }

    //软件安装过程
    public void installWizard(){
        int first = this.first();
        // 根据first返回的解雇，看是否需要执行second
        if(first > 50){
            int second = this.second();
            if(second > 50){
                int thrid = this.third();
                if(thrid > 50){
                    this.first();
                }
            }
        }
    }
}
