package com.cycloneboy.interview.sort;

/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class BubbleSort {

    public void bubbleSort(int[] source){
        boolean needNextPass = true;    //是否需要下一趟排序
        for(int i = source.length -1 ; i> 0 && needNextPass;i--){
            needNextPass = false;
            for(int j =0;j < i;j++){
                if(source[j] > source[j+1]){
                    swap(source,j,j+1);
                    needNextPass =true; //需要下一趟排序
                }
            }
            //打印每一趟排序
            for(int num : source){
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public void swap(int[] source ,int x,int y){
        int temp = source[x];
        source[x] = source[y];
        source[y] = temp;
    }

    public static void main(String[] args) {
        int[] a = {3,5,9,4,7,8,6,1,2};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(a);
        for(int num : a){
            System.out.print(num + " ");
        }
    }
}
