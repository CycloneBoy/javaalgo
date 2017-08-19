package com.cycloneboy.se.day14;

/**
 * 自定义线程的创建方式:

 方式一 ：
 1. 自定义一个类继承Thread类。
 2. 重写Thread类的run方法，把自定义线程的任务代码写在run方法上。
 3. 创建Thread的子类对象，并且调用start方法启动一个线程。

 注意：千万不要直接调用run方法，调用start方法的时候线程就会开启，线程一旦开启就会执行run方法中代码，如果直接调用
 run方法，那么就 相当于调用了一个普通的方法而已。

 方式二：
 1. 自定义一个类实现Runnable接口。
 2. 实现Runnable接口 的run方法，把自定义线程的任务定义在run方法上。
 3. 创建Runnable实现类对象。
 4. 创建Thread类 的对象，并且把Runnable实现类的对象作为实参传递。
 5. 调用Thread对象 的start方法开启一个线程。


 问题1： 请问Runnable实现类的对象是线程对象吗？
 Runnable实现类的对象并 不是一个线程对象，只不过是实现了Runnable接口 的对象而已。
 只有是Thread或者是Thread的子类才是线程 对象。

 问题2： 为什么要把Runnable实现类的对象作为实参传递给Thread对象呢？作用是什么？
 作用就是把Runnable实现类的对象的run方法作为了线程的任务代码去执行了。

 推荐使用： 第二种。 实现Runable接口的。
 原因： 因为java单继承 ,多实现的。

 * Created by CycloneBoy on 2017/8/19.
 */
public class Demo3 implements Runnable{
    @Override
    public void run() {
        //System.out.println("this: " +this);
        //System.out.println("当前线程: " + Thread.currentThread().getName());
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+ ":"+i);
        }
    }

    public static void main(String[] args) {
        //创建Runnable实现类的对象
        Demo3 d = new Demo3();
        //创建Thread类的对象， 把Runnable实现类对象作为实参传递。
        Thread thread = new Thread(d);
        //调用thread对象的start方法开启线程。
        thread.start();

        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName()+ ":"+i);
        }

        /*
	  Thread类 的run方法

	 *  @Override
	    public void run() {
	        if (target != null) {
	            target.run();  //就相当于Runnable实现类的对象的run方法作为了Thread对象的任务代码了。
	        }
	    }
	*/
    }
}
