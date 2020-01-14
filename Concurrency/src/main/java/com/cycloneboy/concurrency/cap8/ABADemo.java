package com.cycloneboy.concurrency.cap8;

import java.util.concurrent.atomic.AtomicReference;

/** Create by sl on 2020-01-14 15:30 */
public class ABADemo {

  static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

  public static void main(String[] args) {}
}
