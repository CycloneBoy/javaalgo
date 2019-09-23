package com.cycloneboy.algo.thread.lock;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2019-08-27 21:30 */
@Slf4j
public class Something {

  public static synchronized void cSyncA() {
    try {
      for (int i = 0; i < 5; i++) {
        Thread.sleep(100); // 休眠100ms
        log.info(Thread.currentThread().getName() + " : cSyncA");
      }
    } catch (InterruptedException ie) {
    }
  }

  public static synchronized void cSyncB() {
    try {
      for (int i = 0; i < 5; i++) {
        Thread.sleep(100); // 休眠100ms
        log.info(Thread.currentThread().getName() + " : cSyncB");
      }
    } catch (InterruptedException ie) {
    }
  }

  public synchronized void isSyncA() {
    try {
      for (int i = 0; i < 5; i++) {
        Thread.sleep(100); // 休眠100ms
        log.info(Thread.currentThread().getName() + " : isSyncA");
      }
    } catch (InterruptedException ie) {
    }
  }

  public synchronized void isSyncB() {
    try {
      for (int i = 0; i < 5; i++) {
        Thread.sleep(100); // 休眠100ms
        log.info(Thread.currentThread().getName() + " : isSyncB");
      }
    } catch (InterruptedException ie) {
    }
  }
}
