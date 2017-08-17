package com.cycloneboy.dp.ch2;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Soldier {
    // 定义士兵的枪支
    private AbstractGun gun;
    // 给士兵一支枪
    public void setGun(AbstractGun _gun){
        this.gun = _gun;
    }

    public void killEnemy(){
        System.out.println("士兵开始杀敌人...");
        gun.shot();
    }
}
