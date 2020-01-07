package com.cycloneboy.concurrency.cap8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * 显示锁,可重入锁,公平锁和非公平锁
 *
 * <p>非公平锁的效率更好
 *
 * <p>Create by sl on 2020-01-07 18:10
 */
@Slf4j
public class LockExample {

  private Lock lock = new ReentrantLock();
  private int count;

  public static void main(String[] args) {}

  public void increment() {
    lock.lock();

    try {
      count++;
    } finally {
      lock.unlock();
    }
  }

  public synchronized void increment2() {
    count++;
  }
}
