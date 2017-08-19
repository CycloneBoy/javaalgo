package com.cycloneboy.se.day14;

/**
 * 生产者消费者
 * 线程通讯： 一个线程完成了自己的任务时，要通知另外一个线程去完成另外一个任务.

 生产者与消费者


 wait():  等待   如果线程执行了wait方法，那么该线程会进入等待的状态，等待状态下的线程必须要被其他线程调用notify方法才能唤醒。
 notify()： 唤醒    唤醒线程池等待线程其中的一个。
 notifyAll() : 唤醒线程池所有等待 线程。


 wait与notify方法要注意的事项：
 1. wait方法与notify方法是属于Object对象 的。
 2. wait方法与notify方法必须要在同步代码块或者是同步函数中才能 使用。
 3. wait方法与notify方法必需要由锁对象调用。

 问题一：出现了线程安全问题。 价格错乱了...
 * Created by CycloneBoy on 2017/8/19.
 */
//产品类
class Product{

    String name ; //名字

    double price; //价格

    boolean flag = false;//产品是否生产完毕的标识，默认情况是没有生产完毕

}

//生产者
class Producer extends  Thread{

    Product p ;

    public Producer(Product p){
        this.p = p;
    }

    @Override
    public void run() {
        int i= 0;
        while (true){
            synchronized (p){
                if(p.flag==false){
                    if(i%2==0){
                        p.name = "苹果";
                        p.price=6.5;
                    }else {
                        p.name="香蕉";
                        p.price =2.0;
                    }
                    System.out.println("生产者生产出了: " + p.name + "价格是: " + p.price);
                    p.flag = true;
                    i++;
                    p.notifyAll();//唤醒消费者去消费
                }else{
                    //已经生产完毕，等待消费者先去消费
                    try {
                        p.wait();//生产者等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}

//消费者
class Customer extends Thread{

    Product p;

    public Customer(Product p){
        this.p = p;
    }

    @Override
    public void run() {
        while (true){
            synchronized(p){
                if(p.flag == true){ //生产者已经生产完毕
                    System.out.println("消费者消费了: " +p.name +"价格是: " + p.price);
                    p.flag = false;
                    p.notifyAll();//唤醒生产者去生产
                }else{
                    //产品还没有生产，应该等待生产者先生产
                    try {
                        p.wait();//消费者也等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
public class Demo5 {
    public static void main(String[] args) {
        Product product = new Product(); //产品
        //创建生产对象
        Producer producer = new Producer(product);
        //创建消费者
        Customer customer = new Customer(product);
        //调用start方法开启线程
        producer.start();
        customer.start();
    }
}
