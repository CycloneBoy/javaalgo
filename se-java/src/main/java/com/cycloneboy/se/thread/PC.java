package com.cycloneboy.se.thread;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition 条件 生产者消费者
 * Created by CycloneBoy on 2017/9/8.
 */
public class PC {
    public static void main(String[] args) {
        Shared shared = new Shared();
        new Producer(shared).start();
        new Consumer(shared).start();
    }
}

class Shared{
    private char c;

    private volatile  boolean available;

    private final Lock lock;
    private final Condition condition;

    Shared(){
        available = false;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    Lock getLock(){
        return lock;
    }

    char getSharedChar(){
        lock.lock();
        try{
            while (!available){
                try{
                    condition.await();
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }
            available = false;
            condition.signal();
        }finally {
            lock.unlock();
            return c;
        }
    }

    void setSharedChar(char c){
        lock.lock();
        try{
            while (available){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.c = c;
            available = true;
            condition.signal();
        }finally {
            lock.unlock();
        }
    }
}

class Producer extends Thread{
    private final  Lock lock;

    private final Shared shared;

    Producer(Shared shared){
        this.shared = shared;
        lock = shared.getLock();
    }

    @Override
    public void run() {
        for(char ch = 'A';ch <= 'Z';ch++){
            lock.lock();
            shared.setSharedChar(ch);
            System.out.println(ch + " produced by producer.");
            lock.unlock();
        }
    }
}

class Consumer extends  Thread{
    private final Lock lock;

    private final Shared shared;

    Consumer(Shared shared){
        this.shared = shared;
        lock = shared.getLock();
    }

    @Override
    public void run() {
        char ch;
        do{
            lock.lock();
            ch = shared.getSharedChar();
            System.out.println(ch + " consumed by consumer.");
            lock.unlock();
        }while (ch != 'Z');
    }
}
