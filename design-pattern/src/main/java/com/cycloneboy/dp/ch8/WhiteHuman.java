package com.cycloneboy.dp.ch8;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public class WhiteHuman implements Human{
    @Override
    public void getColor() {
        System.out.println("白色人种的皮肤颜色是白色的!");
    }

    @Override
    public void talk() {
        System.out.println("白色人种会说话，一般都是单字节！");
    }
}
