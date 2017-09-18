package com.cycloneboy.interview.util;

import java.util.BitSet;
import java.util.Scanner;

public class Main9 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = Integer.parseInt(in.nextLine());
        String str = in.nextLine();

        String[] str1 = str.split(" ");
        BitSet bitSet = new BitSet(total);
        for(int i = 0; i < total;i++ ){
            if(str1[i].equals("1")){
                bitSet.set(i);
            }else if(str1[i].equals("0")){
                bitSet.clear(i);
            }
        }

        System.out.println(bitSet.toString());

    }


}
