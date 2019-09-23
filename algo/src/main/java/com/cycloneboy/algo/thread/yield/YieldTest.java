package com.cycloneboy.algo.thread.yield;

/** Create by sl on 2019-08-27 22:56 */
public class YieldTest {

  public static void main(String[] args) {
    ThreadA t1 = new ThreadA("t1");
    ThreadA t2 = new ThreadA("t2");
    t1.start();
    t2.start();
  }
}
