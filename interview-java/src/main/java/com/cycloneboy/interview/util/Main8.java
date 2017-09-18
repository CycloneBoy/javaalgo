package com.cycloneboy.interview.util;


import java.math.BigInteger;
import java.util.Scanner;

public class Main8 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = Integer.valueOf(in.nextLine());

        String input = in.nextLine();

        String[] str = input.split(" ");

//        for(String s1 : str){
//            System.out.println(s1);
//        }
        System.out.println("total: "+ judge(str));
    }

    public static int judge(String[] str){
        int result = 0;
        System.out.println("str: " +str.length );
        for(int i = 0; i < str.length ;i++){
            for(int j = 0;j< str.length ;j++){
                if(i == j){
                    continue;
                }
                String temp = str[i] + str[j];
                BigInteger b1 = new BigInteger(temp);
                BigInteger b7 = new BigInteger(new String("7"));
                BigInteger b2 = b1.divide(b7);
                System.out.println(i + " " + j+ "   ---  "+b1 + "    " + b2 + "    " + b1.compareTo(b2.multiply(b7)));
                if(b1.compareTo(b2.multiply(b7)) == 0){
                    result++;
                }
            }
        }
        return  result;
    }
}
