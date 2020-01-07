package com.cycloneboy.concurrency.cap7;

import java.util.concurrent.atomic.AtomicInteger;

/** Create by sl on 2020-01-07 12:01 */
public class AtomicIntegerTest {

  private AtomicInteger count = new AtomicInteger();
  // 使用AtomicInteger之后，不需要对该方法加锁，也可以实现线程安全。
  public void increment() {
    count.incrementAndGet();
  }

  public int getCount() {
    return count.get();
  }
}
