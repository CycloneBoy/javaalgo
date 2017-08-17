package com.cycloneboy.dp.ch2;

import java.util.HashMap;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Client3 {
    public static void invoker(){
        // 父类存在的地方，子类就应该能够存在
        //Father f = new Father();
        Father f = new Son();
        HashMap map = new HashMap();
        f.doSomething(map);
    }
    public static void main(String[] args) {
       invoker();
    }

}
