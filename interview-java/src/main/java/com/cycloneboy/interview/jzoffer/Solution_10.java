package com.cycloneboy.interview.jzoffer;

/**
 *
 * 题目描述
 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 通过分析：仍然是斐波那契的问题
 * Created by CycloneBoy on 2017/8/23.
 *
 * 链接：https://www.nowcoder.com/questionTerminal/72a5a919508a4251859fb2cfb987a0e6
 来源：牛客网

 逆向分析

 应为可以横着放或竖着放，多以f(n)可以是2*(n-1)的矩形加一个竖着放的2*1的矩形或2*(n-2)的矩形加2横着放的，即f(n)=f(n-1)+f(n-2)
 当到了最后，f(1)=1,f(2)=2
 */
public class Solution_10 {
    public int RectCover(int target) {
        if( target == 1){
            return 1;
        }else if( target == 2){
            return 2;
        }else{
            int f1 = 1;
            int f2 = 2;
            int f3 =0;
            for(;target -2 > 0;target--){
                f3 = f1 + f2;
                f1 = f2;
                f2 = f3;
            }
            return f3;
        }
    }

    public static void main(String[] args) {
        Solution_10 solution10 = new Solution_10();
        System.out.println(solution10.RectCover(8));
    }
}
