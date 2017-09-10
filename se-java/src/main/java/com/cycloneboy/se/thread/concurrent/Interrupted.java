package com.cycloneboy.se.thread.concurrent;

import com.cycloneboy.se.utils.SleepUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by CycloneBoy on 2017/9/10.
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(),"SleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(),"BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());

        SleepUtils.second(2);

    }
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while(true){
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while(true){

            }
        }
    }
}
