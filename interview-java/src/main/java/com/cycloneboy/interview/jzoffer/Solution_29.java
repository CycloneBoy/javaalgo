package com.cycloneboy.interview.jzoffer;

import java.util.ArrayList;

/**
 * 题目描述
 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，
 则最小的4个数字是1,2,3,4,。
 * Created by CycloneBoy on 2017/9/2.
 */
public class Solution_29 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        //检查输入的特殊情况
        if(input == null || input.length <= 0 || input.length < k){
            return  list;
        }
        //构建最大堆
        for(int length = k/2 -1;length >=0; length--){
            adjustMaxHeapSort(input,length,k-1);
        }
        //从第k个元素开始分别与最大堆的最大值做比较，如果比最大值小，则替换并调整堆。
        //最终堆里的就是最小的K个数。
        int temp;
        for(int i = k;i < input.length;i++){
            if(input[i] < input[0]){
                temp = input[0];
                input[0]=input[i];
                input[i] = temp;
                adjustMaxHeapSort(input,0,k-1);
            }
        }

        for(int j = 0; j <k;j++){
            list.add(input[j]);
        }
        return  list;
    }

    public void adjustMaxHeapSort(int[] input,int pos, int length){
        int temp;
        int child;
        for(temp=input[pos];2*pos + 1 <= length; pos = child){
            child = 2*pos +1;
            if(child < length && input[child] < input[child+1]){
                child++;
            }
            if(input[child] > temp){
                input[pos] = input[child];
            }else {
                break;
            }
        }
        input[pos] = temp;
    }

    public static void main(String[] args) {
        Solution_29 solution29 = new Solution_29();
        int[] array = {1,4,6,4,7,77,444,3,31,67,8,97,4,2,0};
        System.out.println(solution29.GetLeastNumbers_Solution(array,5));
    }
}
