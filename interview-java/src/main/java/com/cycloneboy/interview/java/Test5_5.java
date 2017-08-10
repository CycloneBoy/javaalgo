package com.cycloneboy.interview.java;

/**
 * Created by CycloneBoy on 2017/8/10.
 * 说明:
 *      移位操作符右边的参数要先进行摸的32运算，并且移位是对二进制的操作，
 *      而二进制中的8位是一个循环，所以，num>>32等于num>>0,而num>>33等于num>>1.
 */
public class Test5_5 {

    public static void main(String[] args) {
        int num =32;
        System.out.println(num>>32);
        System.out.println(num>>33);
        System.out.println(num>>64);
    }
}
