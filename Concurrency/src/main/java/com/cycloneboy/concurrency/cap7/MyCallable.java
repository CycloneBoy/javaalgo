package com.cycloneboy.concurrency.cap7;

import java.util.concurrent.Callable;

/** Create by sl on 2020-01-07 12:42 */
public class MyCallable implements Callable<String> {

  @Override
  public String call() throws Exception {
    Thread.sleep(1000);
    // 返回执行当前 Callable 的线程名字
    return Thread.currentThread().getName();
  }
}
