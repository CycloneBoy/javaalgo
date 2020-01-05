package com.cycloneboy.interview.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;
import org.junit.Test;

/** Create by sl on 2020-01-15 17:22 */
public class SortDemo {

  public static void main(String[] args) {
    ArrayList<Integer> arrayList = new ArrayList<Integer>();
    arrayList.add(-1);
    arrayList.add(3);
    arrayList.add(3);
    arrayList.add(-5);
    arrayList.add(7);
    arrayList.add(4);
    arrayList.add(-9);
    arrayList.add(-7);
    System.out.println("原始数组:");
    System.out.println(arrayList);
    // void reverse(List list)：反转
    Collections.reverse(arrayList);
    System.out.println("Collections.reverse(arrayList):");
    System.out.println(arrayList);

    Collections.rotate(arrayList, 4);
    System.out.println("Collections.rotate(arrayList, 4):");
    System.out.println(arrayList);

    // void sort(List list),按自然排序的升序排序
    Collections.sort(arrayList);
    System.out.println("Collections.sort(arrayList):");
    System.out.println(arrayList);

    // void shuffle(List list),随机排序
    Collections.shuffle(arrayList);
    System.out.println("Collections.shuffle(arrayList):");
    System.out.println(arrayList);

    // void swap(List list, int i , int j),交换两个索引位置的元素

    Collections.swap(arrayList, 2, 5);
    System.out.println("Collections.swap(arrayList, 2, 5):");
    System.out.println(arrayList);

    // 定制排序的用法
    Collections.sort(
        arrayList,
        new Comparator<Integer>() {

          @Override
          public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
          }
        });
    System.out.println("定制排序后：");
    System.out.println(arrayList);
  }

  @Test
  public void testSort() {
    String[] strs = {"abcdehg", "abcdefg", "abcdeag"};
    Arrays.sort(strs);
    System.out.println(Arrays.toString(strs)); // [abcdeag, abcdefg, abcdehg]
  }

  @Test
  public void testSort2() {
    // *************复制 copy****************
    // copyOf 方法实现数组复制,h为数组，6为复制的长度
    int[] h = {
      1, 2, 3, 3, 3, 3, 6, 6, 6,
    };
    int i[] = Arrays.copyOf(h, 6);
    System.out.println("Arrays.copyOf(h, 6);：");
    // 输出结果：123333
    for (int j : i) {
      System.out.print(j);
    }
    // 换行
    System.out.println();
    // copyOfRange将指定数组的指定范围复制到新数组中
    int j[] = Arrays.copyOfRange(h, 6, 11);
    System.out.println("Arrays.copyOfRange(h, 6, 11)：");
    // 输出结果66600(h数组只有9个元素这里是从索引6到索引11复制所以不足的就为0)
    for (int j2 : j) {
      System.out.print(j2);
    }
    // 换行
    System.out.println();
  }

  @Test
  public void test() {
    ArrayList<String> list = new ArrayList<String>();
    ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<>();
  }

  @Test
  public void testSet() {
    HashSet<Integer> integers = new HashSet<>();
    ReentrantLock lock = new ReentrantLock();

    try {
      lock.lock();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
}
