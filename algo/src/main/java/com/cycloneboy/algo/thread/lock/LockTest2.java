package com.cycloneboy.algo.thread.lock;

/** Create by sl on 2019-08-27 21:34 */
public class LockTest2 {

  Something x = new Something();
  Something y = new Something();

  public static void main(String[] args) {
    LockTest2 demo = new LockTest2();

    demo.test2();
  }

  // 比较(02) x.isSyncA()与y.isSyncA()
  private void test2() {
    // 新建t21, t21会调用 x.isSyncA()
    Thread t21 = new Thread(() -> x.isSyncA(), "t21");

    // 新建t22, t22会调用 x.isSyncB()
    Thread t22 = new Thread(() -> y.isSyncA(), "t22");

    t21.start(); // 启动t21
    t22.start(); // 启动t22
  }
}
