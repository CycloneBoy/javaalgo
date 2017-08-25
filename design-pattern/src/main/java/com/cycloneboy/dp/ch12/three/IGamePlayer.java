package com.cycloneboy.dp.ch12.three;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public interface IGamePlayer {
    public void login(String user, String password);

    public void killBoss();

    public void upgrade();

    //获取自己的代理
    public IGamePlayer getProxy();
}
