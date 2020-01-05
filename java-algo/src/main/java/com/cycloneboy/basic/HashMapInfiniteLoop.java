package com.cycloneboy.basic;

import java.util.HashMap;

/** Create by sl on 2020-01-15 23:03 */
public class HashMapInfiniteLoop {

  private static HashMap<Integer, String> map = new HashMap<Integer, String>(2, 0.75f);

  public static void main(String[] args) {
    map.put(5, "C");

    new Thread("Thread1") {
      public void run() {
        map.put(7, "B");
        System.out.println(map);
      };
    }.start();
    new Thread("Thread2") {
      public void run() {
        map.put(3, "A");
        System.out.println(map);
      };
    }.start();
  }
}
