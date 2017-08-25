package com.cycloneboy.dp.ch12.two;

import com.cycloneboy.dp.ch12.one.*;


/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class GamePlayerProxy implements IGamePlayer{
    private IGamePlayer gamePlayer = null;

    public GamePlayerProxy(String name){
        try {
            gamePlayer = new GamePlayer(this,name);
        }catch (Exception e){
            e.printStackTrace();
        }
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
    }
}
