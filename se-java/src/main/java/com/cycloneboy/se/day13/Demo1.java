package com.cycloneboy.se.day13;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public class Demo1 extends Thread{

    @Override
    public void run() {
       for(int i =0;i<100;i++){
           System.out.println("自定义线程：" + i);
       }
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        demo1.start();

        for(int i =0;i<100;i++){
            System.out.println("main线程：" + i);
        }
    }
}
