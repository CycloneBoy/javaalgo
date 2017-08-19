package com.cycloneboy.se.day14;

/**
 * join方法。 加入
 * Created by CycloneBoy on 2017/8/19.
 */
//老妈
class Mon extends Thread{
    @Override
    public void run() {
        System.out.println("妈妈洗菜");
        System.out.println("妈妈切菜");
        System.out.println("妈妈准备炒菜，发现没有酱油了...");
        //叫儿子去打酱油
        Son s = new Son();
        s.start();
        try {
            s.join();//加入。 一个线程如果执行join语句，那么就有新的线程加入，执行该语句的线程必须要让步给新加入的线程先完成任务，然后才能继续执行。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("妈妈切菜");
        System.out.println("妈妈切菜");

    }
}

class Son extends Thread{
    @Override
    public void run() {
        System.out.println("儿子下楼");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("儿子一直往前走");
        System.out.println("儿子打完酱油了");
        System.out.println("上楼，把酱油给老妈");

    }
}
public class Demo8 {
    public static void main(String[] args) {
        Mon m = new Mon();
        m.start();

    }
}
