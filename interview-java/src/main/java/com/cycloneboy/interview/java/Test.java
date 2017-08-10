package com.cycloneboy.interview.java;

/**
 * Created by CycloneBoy on 2017/8/1.
 * 中间缓存机制：j=j++
 *  -> temp = j; j= j+ 1; j=temp;shao
 */
public class Test {
    public static void main(String[] args) {
        int j =0;
        for(int i =0;i< 100 ;i++){
            j = j++;
            System.out.println("i = " + i + " j = " + j);
        }
    }
}
