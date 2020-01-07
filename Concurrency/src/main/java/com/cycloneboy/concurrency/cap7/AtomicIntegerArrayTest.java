package com.cycloneboy.concurrency.cap7;

import java.util.concurrent.atomic.AtomicIntegerArray;

/** Create by sl on 2020-01-07 14:46 */
public class AtomicIntegerArrayTest {

  public static void main(String[] args) {
    int temvalue = 0;
    int[] nums = {1, 2, 3, 4, 5, 6};
    AtomicIntegerArray i = new AtomicIntegerArray(nums);
    for (int j = 0; j < nums.length; j++) {
      System.out.println(i.get(j));
    }
    temvalue = i.getAndSet(0, 2);
    System.out.println("temvalue:" + temvalue + ";  i:" + i);
    temvalue = i.getAndIncrement(0);
    System.out.println("temvalue:" + temvalue + ";  i:" + i);
    temvalue = i.getAndAdd(0, 5);
    System.out.println("temvalue:" + temvalue + ";  i:" + i);
  }
}
