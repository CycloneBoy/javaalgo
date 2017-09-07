package com.cycloneboy.interview.thread;


import sun.security.krb5.internal.ccache.CCacheOutputStream;

/**
 * Created by CycloneBoy on 2017/9/5.
 */
public class ThreadDemo {

    public static void testDemo1(String[] args){
        boolean isDaemon = args.length !=0;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Thread thd = Thread.currentThread();
                while (true){
                    System.out.printf("%s is %s alive and in %s " +
                                    "state%n",
                            thd.getName(),
                            thd.isAlive() ? "" : "not ",
                            thd.getState());
                }
            }
        };

        Thread t1 = new Thread(r,"th1");
        if(isDaemon) t1.setDaemon(true);
        System.out.printf("%s is %s alive and in %s " +
                        "state%n",
                t1.getName(),
                t1.isAlive() ? "" : "not ",
                t1.getState());

        Thread t2 = new Thread(r);
        t2.setName("th2");
        if(isDaemon) t1.setDaemon(true);
        System.out.printf("%s is %s alive and in %s " +
                        "state%n",
                t2.getName(),
                t2.isAlive() ? "" : "not ",
                t2.getState());

        t1.start();
        t2.start();
    }

    public static void testDemo2(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name= Thread.currentThread().getName();
                int count =0;
                while (true){
                    System.out.println(name + ":" + count++);
                }
            }
        };

        Thread thdA = new Thread(r);
        Thread thdB = new Thread(r);
        thdA.start();
        thdB.start();

        while (true){
            double n = Math.random();
            if(n >= 0.499999999 && n < 0.50000001){
                break;
            }
        }

        thdA.interrupt();
        thdB.interrupt();

    }

    public static void testDemo3(){
        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name= Thread.currentThread().getName();
                int count =0;
                while (true){
                    System.out.println(name + ":" + count++);
                }
            }
        };

        Thread thdA = new Thread(r);
        Thread thdB = new Thread(r);
        thdA.start();
        thdB.start();

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        thdA.interrupt();
        thdB.interrupt();

    }
    public static void main(String[] args) {
       // testDemo1(args);
        //testDemo2();
        //testDemo3();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                String name= Thread.currentThread().getName();
                int count =0;
                while (true){
                    System.out.println(name + ":" + count++);
                }
            }
        };

        Thread thdA = new Thread(r);
        Thread thdB = new Thread(r);
        thdA.start();
        thdB.start();

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        thdA.interrupt();
        thdB.interrupt();
    }
}
