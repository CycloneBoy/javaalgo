package com.cycloneboy.se.algo;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by CycloneBoy on 2017/9/12.
 */
public class P1_1 {
    static int N = 20;

    public static void main(String[] args) {
        int[] arr = new int[N];
        int x,n,i;
        int f= -1;

        Random r = new Random();
        for(i = 0; i< N;i++){
            arr[i] = r.nextInt(100);
        }

        System.out.println("随机生成的数据序列:");

        for(i = 0 ; i<N;i++){
            System.out.print( arr[i] + " ");
        }
        System.out.println();

        System.out.println("输入你要查找的整数:");
        Scanner input = new Scanner(System.in);
        x = input.nextInt();
        for(i = 0 ; i < N;i++){
            if(x==arr[i]){
                f = i;
                break;
            }
        }

        if(f < 0){
            System.out.println("没有找到数据: " + x);
        }else {
            System.out.println("数据："+x + " 位于数组的第 " + (f+1) + " 个元素处");
        }
    }
}
