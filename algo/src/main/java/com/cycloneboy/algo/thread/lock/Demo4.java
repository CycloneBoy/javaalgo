package com.cycloneboy.algo.thread.lock;

/** Create by sl on 2019-08-27 21:27 */
public class Demo4 {

  public static void main(String[] args) {
    Demo4 demo = new Demo4();

    long start, diff;
    start = System.currentTimeMillis(); // 获取当前时间(millis)
    demo.synMethod(); // 调用“synchronized方法”
    diff = System.currentTimeMillis() - start; // 获取“时间差值”
    System.out.println("synMethod() : " + diff);

    start = System.currentTimeMillis(); // 获取当前时间(millis)
    demo.synBlock(); // 调用“synchronized方法块”
    diff = System.currentTimeMillis() - start; // 获取“时间差值”
    System.out.println("synBlock()  : " + diff);
  }

  public synchronized void synMethod() {
    for (int i = 0; i < 1000000; i++) ;
  }

  public void synBlock() {
    synchronized (this) {
      for (int i = 0; i < 1000000; i++) ;
    }
  }
}
