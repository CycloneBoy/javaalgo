package com.cycloneboy.algo.thread.lock;

/** Create by sl on 2019-08-27 21:37 */
public class LockTest4 {
  Something x = new Something();
  Something y = new Something();

  public static void main(String[] args) {
    LockTest4 demo = new LockTest4();

    demo.test4();
  }

  // 比较(04) x.isSyncA()与Something.cSyncA()
  private void test4() {
    // 新建t41, t41会调用 x.isSyncA()
    Thread t41 = new Thread(() -> x.isSyncA(), "t41");

    // 新建t42, t42会调用 x.isSyncB()
    Thread t42 = new Thread(() -> Something.cSyncA(), "t42");

    t41.start(); // 启动t41
    t42.start(); // 启动t42
  }
}
