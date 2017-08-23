package com.cycloneboy.interview.jzoffer;

/**
 * 题目描述
 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
        解法: f(n) = f(n-1) + f(n-2)
 * Created by CycloneBoy on 2017/8/23.
 */
public class Solution_8 {
    public int JumpFloor(int target) {
        int[] result = {0,1,2};
        if( target < 3){
            return  result[target];
        }

        int fibOne = 2;
        int fibTwo = 1;
        int fibN = 0;
        for(int i  =3 ; i <= target;i++){
            fibN = fibOne + fibTwo;
            fibTwo = fibOne;
            fibOne = fibN;
        }
        return fibN;
    }
}
