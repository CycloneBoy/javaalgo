package com.cycloneboy.interview.java;

/**
 * Created by CycloneBoy on 2017/8/10.
 */
public class Sandys {
    private int count;

    Sandys(int ballcount){
        count = ballcount;
    }

    public static void main(String[] args) {
        Sandys s = new Sandys(99);
        System.out.println(s.count);
    }
}
