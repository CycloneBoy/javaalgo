package com.cycloneboy.dp.ch12.one;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class Client2 {
    public static void main(String[] args) {
        IGamePlayer player = new GamePlayer("张三");
        IGamePlayer proxy = new GamePlayerProxy(player);
        System.out.println("开始时间是： 2017-08-25 12:40" );
        proxy.login("zhangsan","1234");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是：2017-08-25 13:00");
    }
}
