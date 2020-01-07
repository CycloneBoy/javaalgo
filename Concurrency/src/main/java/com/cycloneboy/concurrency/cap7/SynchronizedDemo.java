package com.cycloneboy.concurrency.cap7;

/**
 * Create by sl on 2020-01-07 10:36
 *
 * <p>synchronized 同步语句块的实现使用的是 monitorenter 和 monitorexit 指令，其中 monitorenter
 * 指令指向同步代码块的开始位置，monitorexit 指令则指明同步代码块的结束位置。 当执行 monitorenter 指令时，线程试图获取锁也就是获取
 * monitor(monitor对象存在于每个Java对象的对象头中，synchronized 锁便是通过这种方式获取锁的，也是为什么Java中任意对象可以作为锁的原因)
 * 的持有权。当计数器为0则可以成功获取，获取后将锁计数器设为1也就是加1。相应的在执行 monitorexit
 * 指令后，将锁计数器设为0，表明锁被释放。如果获取对象锁失败，那当前线程就要阻塞等待，直到锁被另外一个线程释放为止。
 */
public class SynchronizedDemo {

  public void method() {
    synchronized (this) {
      System.out.println("synchronized 代码块");
    }
  }
}
