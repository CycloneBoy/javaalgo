package com.cycloneboy.chapter23;

/**
 * Created by CycloneBoy on 2017/7/2.
 */
public class InsertionSort {
    /** The method for sorting the numbers */
    public static void insertionSort(int[] list){
        for (int i = 1; i < list.length; i++) {
            /** Insert list[i] into a sorted sublist list[0..i-1] so that
             *  list[0..i] is sorted .
             */
            int currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];
            }

            // Insert the current element into list[k + 1]
            list[k + 1] = currentElement;
        }
    }
    public static void main(String[] args) {
        int[] list = {1,9,4,6,5,-4};
        InsertionSort.insertionSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }

    }

}
