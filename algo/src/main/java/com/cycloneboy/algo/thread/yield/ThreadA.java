package com.cycloneboy.algo.thread.yield;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2019-08-27 22:55 */
@Slf4j
public class ThreadA extends Thread {

  public ThreadA(String s) {
    super(s);
  }

  @Override
  public synchronized void run() {
    for (int i = 0; i < 10; i++) {
      log.info("{}  [{}]:{}", this.getName(), this.getPriority(), i);
      // i整除4时，调用yield
      if (i % 4 == 0) Thread.yield();
    }
  }
}
