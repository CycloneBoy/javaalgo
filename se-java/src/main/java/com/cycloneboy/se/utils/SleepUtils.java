package com.cycloneboy.se.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by CycloneBoy on 2017/9/10.
 */
public class SleepUtils {
    public static final void second(long seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds);
        }catch (InterruptedException e){
            System.err.println(e.getMessage());
        }
    }
}
