package com.cycloneboy.dp.ch8;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public class NvWa {
    public static void main(String[] args) {
        //声明阴阳八卦炉
        AbstractHumanFactory YingYangLu = new HumanFactory();
        //女娲第一次造人，火候不足，于是白人产生了
        System.out.println("--造出第一批人是白色人种--");
        Human whiteHuman = YingYangLu.createHuman(WhiteHuman.class);
        whiteHuman.getColor();
        whiteHuman.talk();

        //女娲第二次造人，火候过足，于是黑人产生了
        System.out.println("--造出第二批人是黑色人种--");
        Human blackHuman = YingYangLu.createHuman(BlackHuman.class);
        blackHuman.getColor();
        blackHuman.talk();

        //女娲第三次造人，火候刚刚好，于是黄人产生了
        System.out.println("--造出第三批人是黄色人种--");
        Human yellowHuman = YingYangLu.createHuman(YellowHuman.class);
        yellowHuman.getColor();
        yellowHuman.talk();
    }
}
