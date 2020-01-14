package com.cycloneboy.concurrency.cap8;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2020-01-14 23:11 */
@Slf4j
public class BlockingQueueDemo {

  public static void main(String[] args) throws InterruptedException {
    testBlockingQueueTime();

    //    testQueueBlocking1();

    //    testQueueBlockingNull();

    //    testQueueException();
  }

  private static void testBlockingQueueTime() throws InterruptedException {
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

    log.info("{}", blockingQueue.offer("a", 2, TimeUnit.SECONDS));
    log.info("{}", blockingQueue.offer("b", 2, TimeUnit.SECONDS));
    log.info("{}", blockingQueue.offer("c", 2, TimeUnit.SECONDS));
    log.info("{}", blockingQueue.offer("d", 2, TimeUnit.SECONDS));

    log.info("{}", blockingQueue.peek());

    log.info("{}", blockingQueue.poll(2, TimeUnit.SECONDS));
    log.info("{}", blockingQueue.poll(2, TimeUnit.SECONDS));
    log.info("{}", blockingQueue.poll(2, TimeUnit.SECONDS));
    log.info("{}", blockingQueue.poll(2, TimeUnit.SECONDS));
    log.info("{}", blockingQueue.poll(2, TimeUnit.SECONDS));
  }

  private static void testQueueBlocking1() throws InterruptedException {
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

    blockingQueue.put("a");
    blockingQueue.put("b");
    blockingQueue.put("c");
    log.info("---------------------------------");
    //    blockingQueue.put("d");

    log.info("{}", blockingQueue.take());
    log.info("{}", blockingQueue.take());
    log.info("{}", blockingQueue.take());
    log.info("{}", blockingQueue.take());
  }

  private static void testQueueBlockingNull() {
    BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

    log.info("{}", blockingQueue.offer("a"));
    log.info("{}", blockingQueue.offer("b"));
    log.info("{}", blockingQueue.offer("c"));
    log.info("{}", blockingQueue.offer("a"));

    log.info("{}", blockingQueue.peek());

    log.info("{}", blockingQueue.poll());
    log.info("{}", blockingQueue.poll());
    log.info("{}", blockingQueue.poll());

    log.info("{}", blockingQueue.poll());
  }

  private static void testQueueException() {
    Queue queue = new ArrayBlockingQueue<String>(3);

    log.info("{}", queue.add("a"));
    log.info("{}", queue.add("b"));
    log.info("{}", queue.add("c"));
    //    log.info("{}", queue.add("c"));
    log.info("{}", queue.element());

    log.info("{}", queue.remove());
    log.info("{}", queue.remove());
    log.info("{}", queue.remove());
    //    log.info("{}", queue.remove());
  }
}
