package com.cycloneboy.dp.ch2;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Handgun extends AbstractGun{
    // 手枪的特点是携带方便，射程短
    @Override
    public void shot() {
        System.out.println("手枪射击...");
    }
}
