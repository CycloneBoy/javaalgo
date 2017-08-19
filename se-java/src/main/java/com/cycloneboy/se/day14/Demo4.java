package com.cycloneboy.se.day14;

/**
 * Created by CycloneBoy on 2017/8/19.
 */
class SaleTicket implements Runnable{

    int num = 50;//票数

    @Override
    public void run() {
        while(true){
            synchronized ("锁"){
                if(num>0){
                    System.out.println(Thread.currentThread().getName()+"售出了第"+num+"号票");
                    num--;
                }else{
                    System.out.println("售罄了....");
                    break;
                }
            }
        }
    }
}
public class Demo4 {
    public static void main(String[] args) {
        //创建了一个Runnable实现类的对象
        SaleTicket saleTicket = new SaleTicket();
        //创建三个线程对象模拟三个窗口
        Thread thread1 = new Thread(saleTicket);
        Thread thread2 = new Thread(saleTicket);
        Thread thread3 = new Thread(saleTicket);
        //开启线程售票
        thread1.start();
        thread2.start();
        thread3.start();


    }
}
