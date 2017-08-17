package com.cycloneboy.dp.ch2;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class AUG extends Rifle {
    //狙击枪都携带一个精准的望远镜
    public void zoomOut(){
        System.out.println("通过望远镜查看敌人...");
    }

    public void shoot(){
        System.out.println("AUG射击...");
    }
}
