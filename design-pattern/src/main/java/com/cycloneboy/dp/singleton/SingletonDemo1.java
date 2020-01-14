package com.cycloneboy.dp.singleton;

import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2020-01-14 12:25 */
@Slf4j
public class SingletonDemo1 {

  private static SingletonDemo1 instance = null;

  private SingletonDemo1() {
    log.info(
        "{}, 我是构造方法{}", Thread.currentThread().getName(), SingletonDemo2.class.getSimpleName());
  }

  public static synchronized SingletonDemo1 getInstance() {
    if (instance == null) {
      instance = new SingletonDemo1();
    }

    return instance;
  }

  public static void main(String[] args) {

    for (int i = 0; i < 10; i++) {

      new Thread(
              () -> {
                log.info("{}, {}", Thread.currentThread().getName(), SingletonDemo1.getInstance());
              })
          .start();
    }
  }
}
