package com.cycloneboy.concurrency.cap6.mypool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2020-01-06 21:36 */
@ToString
@Slf4j
public class MyThreadPool {

  /** 默认线程池中的个数 */
  private static int WORK_NUMBER = 5;

  /** 默认任务数 */
  private static int TASK_NUMBER = 100;

  /** 工作线程租 */
  private final BlockingQueue<Runnable> taskQueue;

  /** 用户希望启动的线程数量 */
  private final int workNumber;

  /** 工作线程族 */
  private WorkThread[] workThreads;

  private int taskNumber;

  public MyThreadPool() {
    this(WORK_NUMBER, TASK_NUMBER);
  }

  public MyThreadPool(int workNumber, int taskCount) {
    if (workNumber <= 0) workNumber = WORK_NUMBER;
    if (taskCount <= 0) taskCount = TASK_NUMBER;

    this.workNumber = workNumber;
    taskQueue = new ArrayBlockingQueue<>(taskCount);
    workThreads = new WorkThread[workNumber];
    for (int i = 0; i < workNumber; i++) {
      workThreads[i] = new WorkThread();
      workThreads[i].start();
    }
  }

  public void execute(Runnable task) {
    try {
      taskQueue.put(task);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void destroy() {
    log.info("ready close pool....");

    for (int i = 0; i < workNumber; i++) {
      workThreads[i].stopWork();
      workThreads[i] = null;
    }

    taskQueue.clear();
  }

  private class WorkThread extends Thread {

    @Override
    public void run() {
      Runnable r = null;

      try {

        while (!isInterrupted()) {

          r = taskQueue.take();
          if (r != null) {
            log.info("{} ready exec: {}", getId(), r);
          }
          r.run();
        }

      } catch (Exception e) {
        // TODO:
        e.printStackTrace();
      }
    }

    public void stopWork() {
      isInterrupted();
    }
  }
}
