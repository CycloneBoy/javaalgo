package com.cycloneboy.se.day14;

/**
 * 守护线程（后台线程）:在一个进程中如果只剩下 了守护线程，那么守护线程也会死亡。

 需求： 模拟QQ下载更新包。

 一个线程默认都不是守护线程。
 * Created by CycloneBoy on 2017/8/19.
 */
public class Demo7 extends Thread{

    public Demo7(String name){
        super(name);
    }

    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println("更新包目前下载了" +i+"%");
            if(i==100){
                System.out.println("更新包下载完毕，装备安装..");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Demo7 d = new Demo7("后台线程");
        d.setDaemon(true); //setDaemon() 设置线程是否为守护线程，true为守护线程， false为非守护线程。
        //System.out.println("是守护线程吗？"+ d.isDaemon()); //判断线程是否为守护线程。
        d.start();

        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
