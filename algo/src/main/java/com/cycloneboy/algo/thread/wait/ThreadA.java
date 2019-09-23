package com.cycloneboy.algo.thread.wait;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2019-08-27 21:46 */
@Slf4j
public class ThreadA extends Thread {

  public ThreadA(String name) {
    super(name);
  }

  @Override
  public void run() {
    synchronized (this) {
      log.info("{} call notify", Thread.currentThread().getName());
      notify();
    }
  }
}
