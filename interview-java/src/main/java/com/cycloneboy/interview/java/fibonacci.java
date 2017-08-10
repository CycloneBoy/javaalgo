package com.cycloneboy.interview.java;

import java.util.Scanner;

/**
 * Created by CycloneBoy on 2017/8/10.
 * 说明: 递归调用产生斐波那契对列
 */
public class fibonacci {
    public static int k=0;

    public static void main(String[] args)  throws Exception{
        Scanner cin = new Scanner(System.in);
        System.out.println("请输入一个正整数:");
        long a = cin.nextLong();
        System.out.println(fibonacci(a));
        System.out.println("共递归调用了" + k + "次");
    }

    public static  long fibonacci(long m){
        if(m == 0 || m == 1){
            k++;
            return m;
        }else{
            return fibonacci((m-1)) + fibonacci((m-2));
        }
    }
}
