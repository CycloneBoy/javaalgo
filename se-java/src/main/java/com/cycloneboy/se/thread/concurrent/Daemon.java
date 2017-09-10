package com.cycloneboy.se.thread.concurrent;

import com.cycloneboy.se.utils.SleepUtils;

/**
 * 守护线程
 * Created by CycloneBoy on 2017/9/10.
 */
public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(),"DaemonThread");
        thread.setDaemon(true);
        thread.start();
    }

    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try{
                SleepUtils.second(10);
            }finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
