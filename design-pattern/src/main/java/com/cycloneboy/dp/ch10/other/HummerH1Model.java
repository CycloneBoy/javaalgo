package com.cycloneboy.dp.ch10.other;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class HummerH1Model extends HummerModel {
    private boolean alarmFlag = true;//要响喇叭

    @Override
    protected void start() {
        System.out.println("悍马H1发动...");
    }

    @Override
    protected void stop() {
        System.out.println("悍马H1停车...");
    }

    @Override
    protected void alarm() {
        System.out.println("悍马H1鸣笛...");
    }

    @Override
    protected void engineBoom() {
        System.out.println("悍马H1引擎声音是这样的...");
    }

    @Override
    protected boolean isAlarm() {
        return this.alarmFlag;
    }

    public void setAlarm(boolean isAlarm){
        this.alarmFlag = isAlarm;
    }
}
