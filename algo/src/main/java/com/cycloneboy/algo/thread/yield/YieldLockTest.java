package com.cycloneboy.algo.thread.yield;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2019-08-27 22:58 */
@Slf4j
public class YieldLockTest {
  private static Object obj = new Object();

  public static void main(String[] args) {
    ThreadA t1 = new ThreadA("t1");
    ThreadA t2 = new ThreadA("t2");
    t1.start();
    t2.start();
  }

  static class ThreadA extends Thread {
    public ThreadA(String name) {
      super(name);
    }

    public void run() {
      // 获取obj对象的同步锁
      synchronized (obj) {
        for (int i = 0; i < 10; i++) {
          log.info("{}  [{}]:{}", this.getName(), this.getPriority(), i);
          // i整除4时，调用yield
          if (i % 4 == 0) Thread.yield();
        }
      }
    }
  }
}
