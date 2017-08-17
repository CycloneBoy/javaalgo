package com.cycloneboy.dp.ch2;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Client {

    public static void main(String[] args) {
        // 产生三毛这个士兵
        Soldier sanMao = new Soldier();
        // 给三毛一支枪
        sanMao.setGun(new Rifle());
        sanMao.killEnemy();


    }
}
