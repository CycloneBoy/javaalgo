package com.cycloneboy.se.day14;

/**
 * 线程的停止：
 1. 停止一个线程 我们一般都会通过一个变量去控制的。
 2. 如果需要停止一个处于等待状态下的线程，
    那么我们需要通过变量配合notify方法或者interrupt()来使用。
 * Created by CycloneBoy on 2017/8/19.
 */
public class Demo6 extends Thread {

    boolean flag = true;

    public Demo6(String name){
        super(name);
    }

    @Override
    public synchronized  void run() {
        int i=0;
        while (flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("接收到了异常...");
            }
            System.out.println(Thread.currentThread().getName()+":"+i);
            i++;
        }
    }


    public static void main(String[] args) {
        Demo6 d = new Demo6("张三");
        d.setPriority(10);
        d.start();
        //当主线程的i是80的时候停止张三线程。
        //d.interrupt();  // interrupt()根本就是无法停止一个线程。
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
            if(i==80){
                d.flag=false;
                d.interrupt(); //把线程的等待状态强制清除，被清除状态的线程会接收到一个InterruptedException。
                /*synchronized (d) {
					d.notify();
				}*/
            }
        }
    }
}
