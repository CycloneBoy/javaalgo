package com.cycloneboy.dp.ch7;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public class Minister {
    public static void main(String[] args) {
        for(int day=0; day< 3;day++){
            Emperor emperor = Emperor.getInstance();
            emperor.say();
        }
    }
}
