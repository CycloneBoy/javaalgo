package com.cycloneboy.concurrency.cap8;

import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2020-01-14 17:24 */
@Slf4j
class Phone {

  public synchronized void sendSms() {
    log.info("{}, send sms", Thread.currentThread().getName());
    sendEmail();
  }

  public synchronized void sendEmail() {
    log.info("{}, send Email", Thread.currentThread().getName());
  }
}

public class TestTransferValue {

  public static void main(String[] args) {

    ReentrantLock lock = new ReentrantLock();

    new Thread(() -> {}, "t1").start();
  }
}
