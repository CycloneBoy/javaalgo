package com.cycloneboy.algo.thread.lock;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2019-08-27 21:13 */
@Slf4j
public class Count2 {

  public void syncMethod() {
    synchronized (this) {
      try {
        for (int i = 0; i < 5; i++) {
          Thread.sleep(100); // 休眠100ms
          log.info("{} synMethod loop {}", Thread.currentThread().getName(), i);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void noneSyncMethod() {
    synchronized (this) {
      try {
        for (int i = 0; i < 5; i++) {
          Thread.sleep(100); // 休眠100ms
          log.info("{} noneSyncMethod loop {}", Thread.currentThread().getName(), i);
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
