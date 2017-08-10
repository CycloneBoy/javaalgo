package com.cycloneboy.chapter23;

/**
 * Created by CycloneBoy on 2017/7/2.
 */
public class MergeSort {
    /** The method for sorting the numbers */
    public static void mergeSort(int[] list){
        if (list.length > 1){

            // Merge sort the first half
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list,0,firstHalf,0,list.length /2);
            mergeSort(firstHalf);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length /2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list,list.length /2,secondHalf,0,secondHalfLength);
            mergeSort(secondHalf);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf,secondHalf,list);
        }
    }

    /** Merge two sorted lists */
    public static void merge(int[] firstHalf, int[] secondHalf, int[] list) {
        int current1 =0; // Current index in firstHalf
        int current2 =0; // Current index in secondHalf
        int current3 =0; // Current index in list

        while (current1 < firstHalf.length && current2 < secondHalf.length){
            if(firstHalf[current1] < secondHalf[current2]){
                list[current3++] = firstHalf[current1++];
            }else{
                list[current3++] = secondHalf[current2++];
            }
        }

        while (current1 < firstHalf.length){
            list[current3++] = firstHalf[current1++];
        }

        while (current2 < secondHalf.length){
            list[current3++] = secondHalf[current2++];
        }
    }

    /** A test method */
    public static void main(String[] args) {
        int[] list = {2,3,2,5,6,1,-2,3,14,12};
        System.out.println("mergeSort:" );
        mergeSort(list);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
    }
}
