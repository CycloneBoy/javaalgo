package com.cycloneboy.concurrency.cap8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2020-01-15 14:05 */
@Slf4j
public class ThreadPoolDemo1 {

  public static void main(String[] args) {

    ExecutorService executorService = Executors.newFixedThreadPool(1);

    for (int i = 0; i < 10; i++) {
      final int index = i;
      executorService.execute(
          new Runnable() {
            @Override
            public void run() {
              try {
                TimeUnit.SECONDS.sleep(5);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              log.info("task:{} -> {}", Thread.currentThread().getName(), index);
            }
          });
    }
    executorService.shutdown();
  }
}
