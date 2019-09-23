package com.cycloneboy.algo.thread.wait;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2019-08-27 22:38 */
@Slf4j
public class WaitTest {

  public static void main(String[] args) {

    ThreadA t1 = new ThreadA("t1");

    synchronized (t1) {
      try {
        // 启动“线程t1”
        log.info(Thread.currentThread().getName() + " start t1");
        t1.start();

        // 主线程等待t1通过notify()唤醒。
        log.info(Thread.currentThread().getName() + " wait()");
        t1.wait();

        log.info(Thread.currentThread().getName() + " continue");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
