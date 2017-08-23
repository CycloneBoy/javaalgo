package com.cycloneboy.interview.jzoffer;

/**
 * 题目描述
 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，
 并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * Created by CycloneBoy on 2017/8/23.
 */
public class Solution_13 {
    //类似插入排序，当前数是奇数，就往前找，遇到偶数就往它前面插
    public void reOrderArray(int [] array) {
        if(array.length <= 1){
            return;
        }
           int leftIndex = 0;
           for(int i = 0 ; i < array.length;  i++){
               int temp = array[i];
               if( (temp &1) ==1){//奇数
                   for(int j = i; j > 0; j--){
                       if((array[j-1]&1) ==0 ) {//偶数
                        int t = array[j];
                        array[j] = array[j-1];
                        array[j-1] = t;
                       }
                   }
               }
           }
    }

    public static void main(String[] args) {
        int [] a = {4,8,9,2,10,1,3,44,55,66,77};
        Solution_13 solution13 = new Solution_13();
        solution13.reOrderArray(a);

        for(int num : a){
            System.out.print(num + " ");
        }
    }
}
