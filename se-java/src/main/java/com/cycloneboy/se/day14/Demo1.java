package com.cycloneboy.se.day14;

/**
 *进程: 进程就是正在运行的应用程序。 进程了负责了内存空间划分。

 线程： 一个进程中的 代码是由线程去执行的，线程也就是进程中一个执行路径。

 多线程： 一个进程中有多个线程可以同时执行任务。


 多线程 的好处：
 1. 解决一个进程中可以同时执行多个任务的问题。
 2. 提高了资源利用率。

 多线程的弊端：
 1. 增加了cpu的负担。
 2. 降低了一个进程中线程 的执行概率。
 3. 出现了线程 安全问题。
 4. 会引发死锁现象。


 自定义线程 的实现方式：

 方式一 ：
 1. 自定义一个类继承Thread类。
 2. 重写Thread类的run方法，把自定义线程的任务代码写在run方法上。
 3. 创建Thread的子类对象，并且调用start方法启动一个线程。

 注意：千万不要直接调用run方法，调用start方法的时候线程就会开启，线程一旦开启就会执行run方法中代码，如果直接调用
 run方法，那么就 相当于调用了一个普通的方法而已。


 线程安全问题：

 线程安全出现 的根本原因：
 1. 存在两个或者两个以上 的线程对象共享同一个资源。
 2. 多线程操作共享资源的代码 有多句。



 线程安全问题的解决方案：

 方式一： 可以使用同步代码块去解决。

 格式：
 synchronized(锁对象){
 需要被同步的代码
 }

 同步代码块要注意的事项：
 1. 锁对象可以是任意的一个对象。
 2. 一个线程在同步代码块中sleep了，并不会释放锁对象。
 3. 如果不存在着线程安全问题，千万不要使用同步代码块，因为会降低效率。
 4. 锁对象必须是多线程共享的一个资源，否则锁不住。


 方式二：同步函数  ：  同步函数就是使用synchronized修饰一个函数。

 同步函数要注意的事项 ：
 1. 如果是一个非静态的同步函数的锁 对象是this对象，如果是静态的同步函数的锁 对象是当前函数所属的类的字节码文件（class对象）。
 2. 同步函数的锁对象是固定的，不能由你来指定 的。


 推荐使用： 同步代码块。
 原因：
 1. 同步代码块的锁对象可以由我们随意指定，方便控制。同步函数的锁对象是固定 的，不能由我们来指定。
 2. 同步代码块可以很方便控制需要被同步代码的范围，同步函数必须是整个函数 的所有代码都被同步了。



 需求： 一个银行账户5000块，两夫妻一个拿着 存折，一个拿着卡，开始取钱比赛，每次只能取一千块，要求不准出现线程安全问题。

 * Created by CycloneBoy on 2017/8/19.
 */
class BankThread extends  Thread{
    static int count= 5000;

    public BankThread(String name) {
        super(name);
    }

    @Override
    public synchronized void run() {
        while(true){
            synchronized("锁"){
                if(count > 0){
                    System.out.println(Thread.currentThread().getName()+"取走了1000元，还剩余"+(count-1000)+"元");
                    count = count -1000;
                }else {
                    System.out.println("取光了...");
                    break;
                }
            }
        }
    }

    //静态的函数---->函数所属 的类的字节码文件对象--->BankThread.class  唯一的。
    public static synchronized void getMoney(){

    }
}
public class Demo1 {
    public static void main(String[] args) {
        //创建两个线程对象
        BankThread thread1 = new BankThread("老公");
        BankThread thread2 = new BankThread("老婆");
        //调用start方法开启线程
        thread1.start();
        thread2.start();

    }
}
