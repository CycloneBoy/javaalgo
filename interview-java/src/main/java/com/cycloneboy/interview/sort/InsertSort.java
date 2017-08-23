package com.cycloneboy.interview.sort;


/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class InsertSort {

    public void insertSort(int[] source){
        for(int i= 1 ;i < source.length;i ++){
            for(int j = i ; (j > 0) && (source[j] < source[j-1]);j--){
                swap(source,j,j-1);
            }

            for(int num : source){
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public void swap(int[] a ,int x,int y){
        int temp;
        temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public static void main(String[] args) {
        int[] a =  { 5, 9, 1, 4, 2, 6, 3, 8, 0, 7 };
        SelectSort selectSort = new SelectSort();
        selectSort.select(a);
        for(int num : a){
            System.out.print(num + " ");
        }
    }
}
