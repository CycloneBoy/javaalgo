package com.cycloneboy.interview.jzoffer;

import sun.security.util.Length;

/**
 * 题目描述
   数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
   例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
   超过数组长度的一半，因此输出2。如果不存在则输出0。
 * Created by CycloneBoy on 2017/9/2.
 */
public class Solution_28 {

    /**
     *
     如果有符合条件的数字，则它出现的次数比其他所有数字出现的次数和还要多。
     在遍历数组时保存两个值：一是数组中一个数字，一是次数。遍历下一个数字时，
     若它与之前保存的数字相同，则次数加1，否则次数减1；若次数为0，则保存下一个数字，
     并将次数置为1。遍历结束后，所保存的数字即为所求。然后再判断它是否符合条件即可
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array == null || array.length <=0){
            return  0 ;
        }

        int result = array[0];
        int times = 1;
        for(int i = 1;i < array.length; i++){
            if(times == 0){
                result = array[i];
                times =1;
            }else if(array[i] == result){
                times++;
            }else {
                times--;
            }
        }

        //检验是否存在这样的数字
        times = 0;
        for(int i=0;i< array.length; i++){
            if(result == array[i]){
                times++;
            }
        }
        if(times * 2 <= array.length){
            result = 0;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution_28 solution28 = new Solution_28();
        int[] array = {4,2,4,1,4,2};
        System.out.println(solution28.MoreThanHalfNum_Solution(array));

    }
}
