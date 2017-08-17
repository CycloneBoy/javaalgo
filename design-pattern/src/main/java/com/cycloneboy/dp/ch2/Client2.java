package com.cycloneboy.dp.ch2;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Client2 {
    public static void main(String[] args) {
        // 产生一个狙击手
        Snipper sanMao = new Snipper();
        sanMao.setAug(new AUG());
        //sanMao.setAug((AUG)(new Rifle()));
        sanMao.killEnemy();
    }
}
