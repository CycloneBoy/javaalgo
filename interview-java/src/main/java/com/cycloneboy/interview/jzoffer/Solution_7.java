package com.cycloneboy.interview.jzoffer;

/**
 * 题目描述
 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 n<=39
 * Created by CycloneBoy on 2017/8/23.
 */
public class Solution_7 {

    public int Fibonacci(int n) {
        int[] result = {0,1};
        if( n < 2){
            return  result[n];
        }

        int fibOne = 1;
        int fibTwo = 0;
        int fibN = 0;
        for(int i  =2 ; i <= n;i++){
            fibN = fibOne + fibTwo;
            fibTwo = fibOne;
            fibOne = fibN;
        }
        return fibN;
    }

    public static void main(String[] args) {
        Solution_7 solution7 = new Solution_7();
        System.out.println(solution7.Fibonacci(4));

    }
}
