package com.cycloneboy.se.thread.concurrent;

import com.cycloneboy.se.utils.SleepUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程暂停、恢复、停止
 * Created by CycloneBoy on 2017/9/10.
 */
public class Deprecated {

    public static void main(String[] args) throws InterruptedException {
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        Thread printThread = new Thread(new Runner(),"PrintThread");
        printThread.setDaemon(true);
        printThread.start();
        TimeUnit.SECONDS.sleep(3);

        printThread.suspend(); //线程暂停
        System.out.println("main suspend PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);

        printThread.resume(); //线程恢复
        System.out.println("main resume PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);

        printThread.stop(); //线程停止
        System.out.println("main stop PrintThread at " + format.format(new Date()));
        TimeUnit.SECONDS.sleep(3);

    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            DateFormat format = new SimpleDateFormat("HH:mm:ss");
            while (true){
                System.out.println(Thread.currentThread().getName() + " Run at "+
                    format.format(new Date()));;
                SleepUtils.second(1);
            }
        }
    }
}
