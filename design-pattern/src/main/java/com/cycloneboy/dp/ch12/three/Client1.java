package com.cycloneboy.dp.ch12.three;

/**
 * 直接访问真实角色
 * Created by CycloneBoy on 2017/8/25.
 */
public class Client1 {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");
        System.out.println("开始时间是： 2017-08-25 12:40" );
        player.login("zhangsan","1234");
        player.killBoss();
        player.upgrade();
        System.out.println("结束时间是：2017-08-25 13:00");
    }
}
