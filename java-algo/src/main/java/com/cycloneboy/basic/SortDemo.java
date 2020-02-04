package com.cycloneboy.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/** Create by sl on 2020-01-15 17:22 */
@Slf4j
public class SortDemo {

  public static void main(String[] args) {
    log.info("test");
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
  public void test() {}

  @Test
  public void testArray() {
    // TODO Auto-generated method stub
    int[] a = new int[10];
    a[0] = 0;
    a[1] = 1;
    a[2] = 2;
    a[3] = 3;
    System.arraycopy(a, 2, a, 3, 3);
    a[2] = 99;
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }

  @Test
  public void testArray2() {
    int[] a = new int[3];
    a[0] = 0;
    a[1] = 1;
    a[2] = 2;
    int[] b = Arrays.copyOf(a, 10);
    System.out.println("b.length: " + b.length);
  }

  @Test
  public void EnsureCapacityTest() {
    ArrayList<Object> list = new ArrayList<Object>();
    final int N = 10000000;
    long startTime = System.currentTimeMillis();
    for (int i = 0; i < N; i++) {
      list.add(i);
    }
    long endTime = System.currentTimeMillis();
    System.out.println("使用ensureCapacity方法前：" + (endTime - startTime));
  }

  @Test
  public void EnsureCapacityTest2() {
    ArrayList<Object> list = new ArrayList<Object>();
    final int N = 10000000;
    list = new ArrayList<Object>();
    long startTime1 = System.currentTimeMillis();
    list.ensureCapacity(N);
    for (int i = 0; i < N; i++) {
      list.add(i);
    }
    long endTime1 = System.currentTimeMillis();
    System.out.println("使用ensureCapacity方法后：" + (endTime1 - startTime1));
  }
}
