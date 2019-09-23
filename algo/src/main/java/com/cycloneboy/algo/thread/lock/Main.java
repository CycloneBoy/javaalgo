package com.cycloneboy.algo.thread.lock;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2019-08-27 21:20 */
@Slf4j
public class Main {

  public static void main(String[] args) {
    //    testCount();

    log.info("--------------");
    testCount2();
  }

  public static void testCount() {
    final Count count = new Count();
    Thread th1 = new Thread(() -> count.syncMethod(), "th1");

    Thread th2 = new Thread(() -> count.noneSyncMethod(), "th2");

    th1.start();
    th2.start();
  }

  public static void testCount2() {
    final Count2 count = new Count2();
    Thread th1 = new Thread(() -> count.syncMethod(), "th1");

    Thread th2 = new Thread(() -> count.noneSyncMethod(), "th2");

    th1.start();
    th2.start();
  }
}
