package com.cycloneboy.dp.ch12.three;


/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class GamePlayerProxy implements IGamePlayer,IProxy{
    private IGamePlayer gamePlayer = null;

    public GamePlayerProxy(IGamePlayer gamePlayer){
        this.gamePlayer = gamePlayer;
    }
    @Override
    public void login(String user, String password) {
        this.gamePlayer.login(user,password);
    }

    @Override
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    @Override
    public void upgrade() {
        this.gamePlayer.upgrade();
        this.count();
    }

    @Override
    public IGamePlayer getProxy() {
        return this;
    }

    @Override
    public void count() {
        System.out.println("升级总费用是: 150元" );
    }
}
