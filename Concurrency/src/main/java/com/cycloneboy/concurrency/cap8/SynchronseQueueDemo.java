package com.cycloneboy.concurrency.cap8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2020-01-14 23:35 */
@Slf4j
public class SynchronseQueueDemo {

  public static void main(String[] args) {

    BlockingQueue<String> queue = new SynchronousQueue<>();

    new Thread(
            () -> {
              try {
                log.info("{} put 1", Thread.currentThread().getName());
                queue.put("1");

                log.info("{} put 2", Thread.currentThread().getName());
                queue.put("2");

                log.info("{} put 3", Thread.currentThread().getName());
                queue.put("3");
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            },
            "AAA")
        .start();

    new Thread(() -> {}, "a").start();

    new Thread(
            () -> {
              try {

                try {
                  TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                log.info("{} get {}", Thread.currentThread().getName(), queue.take());

                try {
                  TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                log.info("{} get {}", Thread.currentThread().getName(), queue.take());

                try {
                  TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                log.info("{} get {}", Thread.currentThread().getName(), queue.take());

              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            },
            "BBB")
        .start();
  }
}
