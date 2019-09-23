package com.cycloneboy.algo.thread.interrupt;

/** Create by sl on 2019-08-27 23:08 */
// Demo1.java的源码
class MyThread extends Thread {

  public MyThread(String name) {
    super(name);
  }

  @Override
  public void run() {
    try {
      int i = 0;
      while (!isInterrupted()) {
        Thread.sleep(100); // 休眠100ms
        i++;
        System.out.println(
            Thread.currentThread().getName() + " (" + this.getState() + ") loop " + i);
      }
    } catch (InterruptedException e) {
      System.out.println(
          Thread.currentThread().getName()
              + " ("
              + this.getState()
              + ") catch InterruptedException.");
    }
  }
}
