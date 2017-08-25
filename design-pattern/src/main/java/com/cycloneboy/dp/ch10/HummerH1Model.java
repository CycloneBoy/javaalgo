package com.cycloneboy.dp.ch10;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class HummerH1Model extends HummerModel{
    @Override
    public void start() {
        System.out.println("悍马H1发动...");
    }

    @Override
    public void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    public void alarm() {
        System.out.println("悍马H1鸣笛...");
    }

    @Override
    public void engineBoom() {
        System.out.println("悍马H1引擎声音是这样的...");
    }

}
