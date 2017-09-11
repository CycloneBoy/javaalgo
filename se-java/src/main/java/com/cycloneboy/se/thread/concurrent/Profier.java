package com.cycloneboy.se.thread.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal
 * Created by CycloneBoy on 2017/9/11.
 */
public class Profier {
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>(){
        protected Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begain(){
        TIME_THREADLOCAL .set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profier.begain();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profier.end() + " mills");
    }
}
