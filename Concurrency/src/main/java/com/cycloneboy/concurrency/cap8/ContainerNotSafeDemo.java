package com.cycloneboy.concurrency.cap8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import lombok.extern.slf4j.Slf4j;

/**
 * new Vector()
 *
 * <p>Collections.synchronizedList(new ArrayList<>())
 *
 * <p>CopyOnWriteArrayList
 *
 * <p>Create by sl on 2020-01-14 16:10
 */
@Slf4j
public class ContainerNotSafeDemo {

  public static void main(String[] args) {
    new ArrayList<Integer>().add(1);

    List<String> list = Arrays.asList("a", "b", "c");

    list.forEach(System.out::println);

    list();
    log.info("-------------------------------");
    map();

    new CopyOnWriteArrayList<Integer>();
  }

  public static void map() {
    Map<String, String> map = new HashMap<>();
    for (int i = 0; i < 10; i++) {
      new Thread(
              () -> {
                map.put(
                    Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                log.info(map.toString());
              })
          .start();
    }
  }

  public static void set() {
    Set<String> set = new CopyOnWriteArraySet<>();
    for (int i = 0; i < 10; i++) {
      new Thread(
              () -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                log.info(set.toString());
              })
          .start();
    }
  }

  private static void list() {
    List<String> list2 = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      new Thread(
              () -> {
                list2.add(UUID.randomUUID().toString().substring(0, 8));
                log.info(list2.toString());
              })
          .start();
    }
  }
}
