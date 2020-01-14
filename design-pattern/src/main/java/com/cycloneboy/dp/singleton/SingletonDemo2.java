package com.cycloneboy.dp.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * CL( Double check lock )双端检索机制
 *
 * <p>Create by sl on 2020-01-14 12:25
 */
@Slf4j
public class SingletonDemo2 {

  private static volatile SingletonDemo2 instance = null;

  private SingletonDemo2() {
    log.info(
        "{}, 我是构造方法{}", Thread.currentThread().getName(), SingletonDemo2.class.getSimpleName());
  }

  // DCL( Double check lock  )双端检索机制
  public static SingletonDemo2 getInstance() {
    if (instance == null) {
      synchronized (SingletonDemo2.class) {
        if (instance == null) {
          instance = new SingletonDemo2();
        }
      }
    }
    return instance;
  }

  public static void main(String[] args) {

    for (int i = 0; i < 10; i++) {

      new Thread(
              () -> {
                log.info("{}, {}", Thread.currentThread().getName(), SingletonDemo2.getInstance());
              })
          .start();
    }
  }
}
