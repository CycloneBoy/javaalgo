package com.cycloneboy.se.algo;

/**
 * 排序算法
 * Created by CycloneBoy on 2017/9/14.
 */
public class Sort {
    final static int SIZE = 20;

    public static void quickSort(int[] arr,int left,int right){
        int ltemp,rtemp,x;
        if(left < right){
            ltemp = left;
            rtemp = right;
            x = arr[left];     //选取数组第一个元素作为分界值
            while( ltemp < rtemp){
                while(ltemp < rtemp && arr[rtemp] > x){  //找到右边第一个小于分界值的元素位置
                    rtemp--;
                }
                if(ltemp < rtemp){
                    arr[ltemp] = arr[rtemp];
                    ltemp++;
                }

                while(ltemp < rtemp && arr[ltemp] < x){ //找到左边第一个大于分界值的元素位置
                    ltemp++;
                }
                if(ltemp < rtemp){
                    arr[rtemp] = arr[ltemp];
                    rtemp--;
                }
            }

            arr[ltemp] = x;
            quickSort(arr,left,ltemp -1);
            quickSort(arr,ltemp+1,right);
        }
    }

    //归并排序
    public static void mergeSort(int[] arr,int left,int right){
        if(left < right){
            int mid = (right + left)/2;
            mergeSort(arr,left,mid);
            mergeSort(arr,mid+1,right);
            merge(arr,left,right,mid);
        }
    }

    //归并
    public static void merge(int[] arr,int left,int right, int mid){
        int[] tempArray = new int[right -left +1];
        int tempIndex = 0;
        int leftIndex = left;
        int rightIndex = mid +1;

        while(leftIndex <= mid && rightIndex <= right){
            if(arr[leftIndex] < arr[rightIndex]){
                tempArray[tempIndex++] = arr[leftIndex++];
            }else {
                tempArray[tempIndex++] = arr[rightIndex++];
            }
        }

        while(leftIndex <= mid){
            tempArray[tempIndex++] = arr[leftIndex++];
        }

        while (rightIndex <= right){
            tempArray[tempIndex++] = arr[rightIndex++];
        }

        System.arraycopy(tempArray,0,arr,left,right-left+1);
    }

    public static void main(String[] args) {
        int[] shuzu = new int[SIZE];
        int i = 0;

        for(i = 0; i< SIZE;i++){
            shuzu[i] = (int)(100 + Math.random() * (100+1));
        }

        System.out.println("排序前数组:");
        for(i =0 ;i <SIZE;i++){
            System.out.print( shuzu[i] + " ");
        }
        System.out.println();

        //quickSort(shuzu,0, shuzu.length -1);
        mergeSort(shuzu,0,shuzu.length -1);

        System.out.println("排序后数组:");
        for(i =0 ;i <SIZE;i++){
            System.out.print( shuzu[i] + " ");
        }
        System.out.println();

    }
}
