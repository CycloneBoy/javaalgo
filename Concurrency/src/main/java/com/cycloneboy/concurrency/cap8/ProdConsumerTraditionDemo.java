package com.cycloneboy.concurrency.cap8;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2020-01-15 10:17 */
@Slf4j
@Data
class ShareData {

  private int number = 0;
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  public void increment() throws Exception {

    lock.lock();
    try {

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}

public class ProdConsumerTraditionDemo {}
