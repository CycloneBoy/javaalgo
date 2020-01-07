package com.cycloneboy.dp.ch12.four;

import com.cycloneboy.dp.ch12.one.GamePlayer;
import com.cycloneboy.dp.ch12.one.IGamePlayer;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/** 动态代理 Created by CycloneBoy on 2017/8/25. */
public class Client {
  public static void main(String[] args) {
    IGamePlayer player = new GamePlayer("张三");
    InvocationHandler handler = new GamePlayIH(player);
    System.out.println("开始时间是: 2017-08-25 13:55 ");
    ClassLoader cl = player.getClass().getClassLoader();
    IGamePlayer proxy =
        (IGamePlayer) Proxy.newProxyInstance(cl, new Class[] {IGamePlayer.class}, handler);

    proxy.login("zhangsan", "1234");
    proxy.killBoss();
    proxy.upgrade();
    System.out.println("结束时间是: 2017-08-25 16:00");
  }
}
