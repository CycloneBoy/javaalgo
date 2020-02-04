package com.cycloneboy.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/** Create by sl on 2020-01-06 05:20 */
@Slf4j
public class SortTest {

  int SIZE = 1000000;
  Integer[] array = new Integer[SIZE];

  Long start, end;

  @Before
  public void setUp() {

    for (int i = 0; i < SIZE; i++) {
      array[i] = i;
    }

    start = System.currentTimeMillis();
    log.info("============= start test :{}==============", start);
  }

  @After
  public void end() {
    end = System.currentTimeMillis();

    log.info("============= end   test: {}==============", end);
    log.info("查找数量:{},耗时:{} s.", SIZE, (end - start) / 1000.0);
    log.info("=====================================================");
  }

  @Test
  public void testBinarySearch() {

    for (int i = 0; i < SIZE; i++) {
      if (i != BinarySearch.binarySearch(array, i)) {
        System.err.println("Found " + i + " at ");
      }
    }
  }
}
