package com.cycloneboy.dp.ch2;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Snipper {
    private AUG aug;

    public void setAug(AUG _aug){
        this.aug = _aug;
    }

    public void killEnemy(){
        // 首先看看敌人的情况，别杀死敌人，自己也被人干掉
        aug.zoomOut();
        // 开始射击
        aug.shoot();
    }
}
