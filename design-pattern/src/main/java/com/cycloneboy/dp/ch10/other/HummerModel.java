package com.cycloneboy.dp.ch10.other;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public abstract class HummerModel {

    protected abstract void start();

    protected abstract void stop();

    protected abstract  void alarm();

    protected abstract void engineBoom();

    final public void run(){
        this.start();
        this.engineBoom();

        if(this.isAlarm()){
            this.alarm();
        }

        this.stop();
    }

    protected boolean isAlarm(){
        return true;
    }
}
