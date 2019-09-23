package com.cycloneboy.algo.thread.lock;

/** Create by sl on 2019-08-27 21:35 */
public class LockTest3 {
  Something x = new Something();
  Something y = new Something();

  public static void main(String[] args) {
    LockTest3 demo = new LockTest3();

    demo.test3();
  }

  // 比较(03) x.cSyncA()与y.cSyncB()
  private void test3() {
    // 新建t31, t31会调用 x.isSyncA()
    Thread t31 = new Thread(() -> x.cSyncA(), "t31");

    // 新建t32, t32会调用 x.isSyncB()
    Thread t32 = new Thread(() -> y.cSyncB(), "t32");

    t31.start(); // 启动t31
    t32.start(); // 启动t32
  }
}
