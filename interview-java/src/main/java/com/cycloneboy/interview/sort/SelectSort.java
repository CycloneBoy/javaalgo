package com.cycloneboy.interview.sort;

/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class SelectSort {

    public void select(int[] source){
        int minIndex ;//最小索引
        for(int i=0 ; i< source.length ;i ++){
            minIndex = i;
            for(int j = i+1;j< source.length;j++){
                if(source[minIndex] > source[j]){
                    minIndex = j;
                }
            }
            swap(source,i,minIndex);

            for(int num : source){
                System.out.print(num+" ");
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
            System.out.print(num+" ");
        }
    }
}
