package com.cycloneboy.dp.ch2;

import java.util.Collection;
import java.util.Map;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Son extends Father {
    // 放大输入参数类型
    public Collection  doSomething(Map map){
        System.out.println("子类被执行...");
        return map.values();
    }
}
