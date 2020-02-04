package com.cycloneboy.sort;

/** Create by sl on 2020-01-06 05:14 */
public class BinarySearch {

  public static final int NOT_FOUND = -1;

  public static <T extends Comparable<? super T>> int binarySearch(T[] array, T x) {
    int low = 0;
    int high = array.length - 1;
    int mid;

    while (low <= high) {
      mid = low + (high - low) / 2;

      if (array[mid].compareTo(x) < 0) {
        low = mid + 1;
      } else if (array[mid].compareTo(x) > 0) {
        high = mid - 1;
      } else return mid;
    }

    return NOT_FOUND;
  }
}
