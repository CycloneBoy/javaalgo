package com.cycloneboy.se.day14;

/**
 * java中同步机制解决了线程安全问题，但是也同时引发死锁现象。

 死锁现象：

 死锁现象出现 的根本原因：
 1. 存在两个或者两个以上的线程。
 2. 存在两个或者两个以上的共享资源。

 死锁现象的解决方案： 没有方案。只能尽量避免发生而已。

 * Created by CycloneBoy on 2017/8/19.
 */
class DeadLock extends  Thread{
    public DeadLock(String name) {
        super(name);
    }

    @Override
    public void run() {
        if("张三".equals(Thread.currentThread().getName())){
            synchronized("遥控器"){
                System.out.println("张三拿到了遥控器,准备去拿电池！！");
                synchronized("电池"){
                    System.out.println("张三拿到了遥控器和电池了，开着空调爽歪歪的吹着...");
                }
            }
        }else if ("李四".equals(Thread.currentThread().getName())){
            synchronized("电池"){
                System.out.println("李四拿到了电池,准备去拿遥控器！！");
                synchronized("遥控器"){
                    System.out.println("李四拿到了遥控器和电池了，开着空调爽歪歪的吹着...");
                }
            }
        }
    }
}
public class Demo2 {
    public static void main(String[] args) {
        DeadLock thread1 = new DeadLock("张三");
        DeadLock thread2 = new DeadLock("李四");
        thread1.start();
        thread2.start();

    }
}
