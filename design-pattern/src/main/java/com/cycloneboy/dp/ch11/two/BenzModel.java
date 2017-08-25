package com.cycloneboy.dp.ch11.two;

import com.cycloneboy.dp.ch11.one.CarModel;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class BenzModel extends CarModel {


    @Override
    protected void start() {
        System.out.println("奔驰车跑起来是这样子的...");
    }

    @Override
    protected void stop() {
        System.out.println("奔驰车应该这样停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("奔驰车的喇叭声音是这样子的...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("奔驰车的引擎声音是这样的...");
    }

}
