package com.cycloneboy.algo.thread.wait;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2019-08-27 22:50 */
@Slf4j
public class NotifyAllTest {
  private static Object obj = new Object();

  public static void main(String[] args) {

    ThreadA t1 = new ThreadA("t1");
    ThreadA t2 = new ThreadA("t2");
    ThreadA t3 = new ThreadA("t3");
    t1.start();
    t2.start();
    t3.start();

    try {
      log.info(Thread.currentThread().getName() + " sleep(3000)");
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    synchronized (obj) {
      // 主线程等待唤醒。
      log.info(Thread.currentThread().getName() + " notifyAll()");
      obj.notifyAll();
    }
  }

  static class ThreadA extends Thread {

    public ThreadA(String name) {
      super(name);
    }

    public void run() {
      synchronized (obj) {
        try {
          // 打印输出结果
          log.info(Thread.currentThread().getName() + " wait");

          // 唤醒当前的wait线程
          obj.wait();

          // 打印输出结果
          log.info(Thread.currentThread().getName() + " continue");
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
