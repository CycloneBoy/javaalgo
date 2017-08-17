package com.cycloneboy.dp.ch2;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Father {
    public Collection doSomething(HashMap map){
        System.out.println("父类被执行...");
        return  map.values();
    }
}
