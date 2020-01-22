package com.cycloneboy.sort;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/** Create by sl on 2020-01-10 09:58 */
@Slf4j
public class SortAlgo<T extends Comparable<? super T>> {

  public static <T extends Comparable<? super T>> void bubbleSort(T[] a) {

    for (int i = 0; i < a.length - 1; i++) {

      boolean flag = false;

      for (int j = 0; j < a.length - 1 - i; j++) {
        if (a[j].compareTo(a[j + 1]) > 0) {
          T tmp = a[j + 1];
          a[j + 1] = a[j];
          a[j] = tmp;
          flag = true;
        }
      }

      if (!flag) {
        break;
      }
    }
  }

  /**
   * 简单插入排序
   *
   * @param a
   * @param <T>
   */
  public static <T extends Comparable<? super T>> void insertionSort(T[] a) {
    int j;
    for (int p = 1; p < a.length; p++) {
      T tmp = a[p];
      for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--) {
        a[j] = a[j - 1];
      }
      a[j] = tmp;
    }
  }

  /**
   * 希尔排序
   *
   * <p>增量为可变的插入排序,当增量固定为1的时候就退化成了插入排序
   *
   * @param a
   * @param <T>
   */
  public static <T extends Comparable<? super T>> void shellSort(T[] a) {
    int j;
    for (int gap = a.length / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < a.length; i++) {

        T tmp = a[i];
        for (j = i; j > gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
          a[j] = a[j - gap];
        }
        a[j] = tmp;
      }
    }
  }

  /**
   * 堆排序: 返回左孩子
   *
   * @param i
   * @return
   */
  private static int leftChild(int i) {
    return 2 * i + 1;
  }

  /**
   * 堆排序: 用于删除最大元素和重建堆
   *
   * @param a 数组
   * @param i 哪一个位置需要下沉
   * @param n 逻辑上需要退排序的大小
   * @param <T>
   */
  private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {

    int child = 0;
    T tmp;

    for (tmp = a[i]; leftChild(i) < n; i = child) {
      child = leftChild(i);
      if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0) {
        child++;
      }
      if (tmp.compareTo(a[child]) < 0) {
        a[i] = a[child];
      } else {
        break;
      }
    }
    a[i] = tmp;
  }

  /**
   * 标准的堆排序
   *
   * @param a
   * @param <T>
   */
  public static <T extends Comparable<? super T>> void heapSort(T[] a) {
    T tmp;
    // buildHeap
    for (int i = a.length / 2 - 1; i > +0; i--) {
      percDown(a, i, a.length);
    }
    // deleteMax
    for (int i = a.length - 1; i > 0; i--) {
      //      swapReferences(a,0,i);
      tmp = a[0];
      a[0] = a[i];
      a[i] = tmp;
      percDown(a, 0, i);
    }
  }

  /**
   * 归并排序
   *
   * @param a
   * @param tmpArray
   * @param left
   * @param right
   * @param <T>
   */
  private static <T extends Comparable<? super T>> void mergeSort(
      T[] a, T[] tmpArray, int left, int right) {
    if (left < right) {
      int center = (left + right) / 2;
      mergeSort(a, tmpArray, left, center);
      mergeSort(a, tmpArray, center + 1, right);
      merge(a, tmpArray, left, center + 1, right);
    }
  }

  /**
   * 归并两个数组
   *
   * @param a
   * @param tmpArray
   * @param leftPos
   * @param rightPos
   * @param rightEnd
   * @param <T>
   */
  private static <T extends Comparable<? super T>> void merge(
      T[] a, T[] tmpArray, int leftPos, int rightPos, int rightEnd) {
    int leftEnd = rightPos - 1;
    int tmpPos = leftPos;
    int numElements = rightEnd - leftPos + 1;

    // Main loop
    while (leftPos <= leftEnd && rightPos <= rightEnd) {
      if (a[leftPos].compareTo(a[rightPos]) <= 0) {
        tmpArray[tmpPos++] = a[leftPos++];
      } else {
        tmpArray[tmpPos++] = a[rightPos++];
      }
    }

    // copyt rest of first haft
    while (leftPos <= leftEnd) {
      tmpArray[tmpPos++] = a[leftPos++];
    }

    // copyt rest of right haft
    while (rightPos <= rightEnd) {
      tmpArray[tmpPos++] = a[rightPos++];
    }

    // copy tmpArray back
    for (int i = 0; i < numElements; i++, rightEnd--) {
      a[rightEnd] = tmpArray[rightEnd];
    }
  }

  /**
   * 归并排序
   *
   * @param a
   * @param <T>
   */
  public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
    T[] tmpArray = (T[]) new Comparable[a.length];
    mergeSort(a, tmpArray, 0, a.length);
  }

  /**
   * 递归的简单排序
   *
   * @param items
   * @param <T>
   */
  public static <T extends Comparable<? super T>> void sampleSort(List<T> items) {
    if (items.size() > 1) {
      ArrayList<T> smaller = new ArrayList<>();
      ArrayList<T> same = new ArrayList<>();
      ArrayList<T> larger = new ArrayList<>();

      T chosenItem = items.get(items.size() / 2);
      for (T item : items) {
        if (item.compareTo(chosenItem) < 0) {
          smaller.add(item);
        } else if (item.compareTo(chosenItem) > 0) {
          larger.add(item);
        } else {
          same.add(item);
        }
      }

      sampleSort(smaller);
      sampleSort(larger);

      items.clear();
      items.addAll(smaller);
      items.addAll(same);
      items.addAll(larger);
      items.addAll(larger);
    }
  }

  public static <T extends Comparable<? super T>> void heapSort2(T[] a) {}
}
