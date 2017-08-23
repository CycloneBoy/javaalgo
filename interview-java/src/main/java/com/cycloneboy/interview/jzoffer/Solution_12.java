package com.cycloneboy.interview.jzoffer;

import java.awt.*;

/**
 * 题目描述
 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * Created by CycloneBoy on 2017/8/23.
 */
public class Solution_12 {
    public double Power(double base, int exponent) {
        //处理特殊情况
        if( base - 0.0 >-0.0000001 && base - 0.0 < 0.0000001 && exponent < 0){
            return 0.0;
        }

        int absExponent = exponent;
        if( exponent < 0){
            absExponent = -exponent;
        }

        double result = 1.0;
        for(int i =1;i <= absExponent; i++){
            result*= base;
           // System.out.println(i + " " + result);
        }

        //System.out.println(result + " " +absExponent);
        if(exponent < 0){
            result = 1.0/result;
        }

        return result;
    }

    /*剑指书中细节：
    *1.当底数为0且指数<0时
    *会出现对0求倒数的情况，需进行错误处理，设置一个全局变量；
    *2.判断底数是否等于0
    *由于base为double型，不能直接用==判断
    *3.优化求幂函数
    *当n为偶数，a^n =（a^n/2）*（a^n/2）
    *当n为奇数，a^n = a^[(n-1)/2] * a^[(n-1)/2] * a
    *时间复杂度O(logn)
    */
    public double Power1(double base, int exponent) {

        return 0.0;
    }

    /**
      * 1.全面考察指数的正负、底数是否为零等情况。
      * 2.写出指数的二进制表达，例如13表达为二进制1101。
      * 3.举例:10^1101 = 10^0001*10^0100*10^1000。
      * 4.通过&1和>>1来逐位读取1101，为1时将该位代表的乘数累乘到最终结果。
      */
    public double Power2(double base, int exponent) {
        double result = 1,current = base;
        int absExponent = exponent;
        if(exponent > 0){
            absExponent = exponent;
        }else if (exponent < 0){
            if(base == 0 ){
                throw  new RuntimeException("分母不能为0");
            }
            absExponent = -exponent;
        }else {
            return  1;
        }

        while (absExponent != 0){
            if((absExponent&1) == 1){
                result *= current;
            }
            current *= current;//翻倍
            absExponent>>=1;//右移一位
        }
        return  exponent >=0 ? result : (1.0/result);
    }

    public static void main(String[] args) {
        Solution_12 solution12 = new Solution_12();

        System.out.println(solution12.Power(2.0,3));

        //System.out.println(solution12.Power1(2.0,-3));
        System.out.println(solution12.Power2(2.0,-3));
    }
}
