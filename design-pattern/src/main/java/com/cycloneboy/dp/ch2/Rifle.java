package com.cycloneboy.dp.ch2;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Rifle extends AbstractGun {
    // 步枪的特点是射程远,威力大
    @Override
    public void shot() {
        System.out.println("步枪射击...");
    }
}
