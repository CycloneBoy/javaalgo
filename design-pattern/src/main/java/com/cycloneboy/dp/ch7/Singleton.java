package com.cycloneboy.dp.ch7;

/**
 * 单例模式: 懒汉方式 ,需要考虑线程同步
 * Created by CycloneBoy on 2017/8/18.
 */
public class Singleton {
    private static Singleton singleton = new Singleton();

    // 限制产生多个对象
    private Singleton() {
    }

    //通过该方法获得实例对象
    private synchronized static Singleton  getInstance() {
       if(singleton == null){
           singleton = new Singleton();
       }
        return  singleton;
    }


}
