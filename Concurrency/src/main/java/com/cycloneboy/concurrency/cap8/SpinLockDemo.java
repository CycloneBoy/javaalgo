package com.cycloneboy.concurrency.cap8;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * 自学三板斧
 *
 * <p>理论 代码 小总结
 *
 * <p>Create by sl on 2020-01-14 18:34
 */
@Slf4j
public class SpinLockDemo {

  // 原子引用线程
  AtomicReference<Thread> atomicReference = new AtomicReference<>();

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      new Thread(() -> {}).start();
    }

    Lock lock = new ReentrantLock();
    try {
      lock.lock();
    } catch (Exception e) {

    } finally {
      lock.unlock();
    }
  }

  public void myLock() {
    Thread thread = Thread.currentThread();

    log.info("{} come in ", Thread.currentThread().getName());
    while (!atomicReference.compareAndSet(null, thread)) {}
  }

  public void myUnLock() {
    Thread thread = Thread.currentThread();
    atomicReference.compareAndSet(thread, null);
    log.info("{} invoked myUnLock ", Thread.currentThread().getName());
  }
}
