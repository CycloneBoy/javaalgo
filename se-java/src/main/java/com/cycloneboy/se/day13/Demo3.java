package com.cycloneboy.se.day13;

/**
 * Created by CycloneBoy on 2017/8/18.
 * 线程常用的方法：
 Thread(String name)     初始化线程的名字
 setName(String name)    设置线程对象名
 getName()             返回线程的名字


 sleep()                 线程睡眠指定的毫秒数。 静态的方法， 那个线程执行了sleep方法代码那么就是那个线程睡眠。

 currentThread()      返回当前的线程对象，该方法是一个静态的方法， 注意： 那个线程执行了currentThread()代码就返回那个线程 的对象。

 getPriority()             返回当前线程对象的优先级   默认线程的优先级是5
 setPriority(int newPriority) 设置线程的优先级    虽然设置了线程的优先级，但是具体的实现取决于底层的操作系统的实现（最大的优先级是10 ，最小的1 ， 默认是5）。

 */
public class Demo3 extends Thread {

    public Demo3(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+ ":" + i);
        }
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3("张三");
        demo3.setPriority(10);
        demo3.start();//设置线程 的优先级。 优先级的数字越大，优先级越高  ， 优先级的范围是1~10

        for (int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+ ":" + i);
        }
    }
}
