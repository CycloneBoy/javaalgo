package com.cycloneboy.interview.sort;

/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class MergeSort {

    public void mergeSort(int[] array,int from,int end){
        if(from < end){
            int mid = (from + end)/2;
            mergeSort(array,from,mid);
            mergeSort(array,mid+1,end);
            merge(array,from,end,mid);
        }
    }

    private void merge(int[] array, int from, int end, int mid) {
        int[] tempArray = new int[end - from + 1];
        int tempArrayIndex = 0;
        int part1ArrayIndex = from;
        int part2ArrayIndex = mid + 1;

        while(part1ArrayIndex <= mid && part2ArrayIndex <= end ){
            if (array[part1ArrayIndex] < array[part2ArrayIndex]) {
                tempArray[tempArrayIndex++] = array[part1ArrayIndex++];
            }else {
                tempArray[tempArrayIndex++] = array[part2ArrayIndex++];
            }
        }

        while (part1ArrayIndex <= mid){
            tempArray[tempArrayIndex++] = array[part1ArrayIndex++];
        }

        while (part2ArrayIndex <= end){
            tempArray[tempArrayIndex++] = array[part2ArrayIndex++];
        }

        System.arraycopy(tempArray,0,array,from,end-from+1);
    }

    public static void main(String[] args) {
        int[] list = {2,3,2,5,6,1,-2,3,14,12};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(list,0,list.length-1);
        for(int num : list){
            System.out.print(num + " ");
        }

    }
}
