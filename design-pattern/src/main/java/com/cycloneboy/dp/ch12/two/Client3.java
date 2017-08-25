package com.cycloneboy.dp.ch12.two;

import com.cycloneboy.dp.ch12.one.IGamePlayer;

/**
 * 普通代理
 * Created by CycloneBoy on 2017/8/25.
 */
public class Client3 {
    public static void main(String[] args) {
        IGamePlayer proxy = new GamePlayerProxy("张三");
        System.out.println("开始时间是： 2017-08-25 12:40" );
        proxy.login("zhangsan","1234");
        proxy.killBoss();
        proxy.upgrade();
        System.out.println("结束时间是：2017-08-25 13:00");
    }
}
