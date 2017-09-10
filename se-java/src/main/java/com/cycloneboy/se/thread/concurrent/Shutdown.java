package com.cycloneboy.se.thread.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * 安全的终止线程
 * Created by CycloneBoy on 2017/9/10.
 */
public class Shutdown
{
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(two,"CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.cancel();

    }

    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;

        @Override
        public void run() {
            while(on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel(){
            this.on = false;
        }
    }
}
