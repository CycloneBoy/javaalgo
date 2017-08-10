package com.cycloneboy.interview.java;

/**
 * Created by CycloneBoy on 2017/8/1.
 */
public class Test {
    static{
        int x = 5;
    }

    static int x,y;

    public static void main(String[] args) {
        x--;
        System.out.println("main : " + x);
        myMethod();
        System.out.println(x + y++ + x);

    }

    public static void myMethod(){
        y = x++ +  ++x;
        System.out.println("myMethod: x = " + x + " y = " + y );
    }
}
