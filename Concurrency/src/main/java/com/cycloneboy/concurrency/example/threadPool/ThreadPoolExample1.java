package com.cycloneboy.concurrency.example.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadPoolExample1 {

  public static void main(String[] args) {

    //    ExecutorService executorService = Executors.newCachedThreadPool();
    //
    //    for (int i = 0; i < 10; i++) {
    //      final int index = i;
    //      executorService.execute(
    //          new Runnable() {
    //            @Override
    //            public void run() {
    //              log.info("task:{}", index);
    //            }
    //          });
    //    }
    //    executorService.shutdown();

    ThreadPoolExecutor poolExecutor =
        new ThreadPoolExecutor(
            2,
            5,
            1,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new DiscardOldestPolicy());

    for (int i = 0; i < 9; i++) {
      poolExecutor.execute(
          () -> {
            log.info("{} - {}", Thread.currentThread().getName(), "办理业务");
          });
    }

    log.info("核心数量: {}", Runtime.getRuntime().availableProcessors());
    poolExecutor.shutdown();
  }
}
