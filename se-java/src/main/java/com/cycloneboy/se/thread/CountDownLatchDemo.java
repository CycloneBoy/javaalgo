package com.cycloneboy.se.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时门栓
 * Created by CycloneBoy on 2017/9/7.
 */
public class CountDownLatchDemo {
    final static int NTHREADS = 3;

    public static void main(String[] args) {
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(NTHREADS);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    report("entered run()");
                    startSignal.await(); //wait until told to...
                    report("doing work");
                    Thread.sleep((int)Math.random() * 1000);
                    doneSignal.countDown(); // reduce count on which main thread is waiting...
                }catch (InterruptedException ie){
                    System.err.println(ie);
                }
            }

            void report(String s) {
                System.out.println(System.currentTimeMillis() + ":" + Thread.currentThread() +
                            ":" + s);
            }
        };

        ExecutorService exector = Executors.newFixedThreadPool(NTHREADS);
        for(int i=0;i < NTHREADS ;i++){
            exector.execute(r);
        }

        try{
            System.out.println("main thread doing something");
            Thread.sleep(1000); // sleep for 1 second
            startSignal.countDown(); // let all threads proceed
            System.out.println("main thread doing something else");
            doneSignal.await(); // wait for all threads to finish
            exector.shutdownNow();
        }catch (InterruptedException ie){
            System.err.println(ie);
        }
    }


}
