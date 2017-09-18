package com.cycloneboy.interview.huawei;

import java.util.Scanner;

/**
 * 题目描述
 写出一个程序，接受一个十六进制的数值字符串，输出该数值的十进制字符串。（多组同时输入 ）
 输入描述:
 输入一个十六进制的数值字符串。
 输出描述:
 输出该数值的十进制字符串。
 * Created by CycloneBoy on 2017/9/12.
 */
public class Main6 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
           String str = sc.nextLine().substring(2);
           System.out.println(Integer.parseInt(str,16));
        }
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
//       while(sc.hasNext()){
//           String str = sc.nextLine();
//           System.out.println(toTen(str ));
//        }

        System.out.println(toTen("0x0a" ));
        System.out.println(toTen("0x76e" ));
        System.out.println(toTen("0xaB012" ));
        System.out.println(toTen("0x0aB012" ));
    }

    public static int toTen(String str){
        int result = 0;
        int ret = 0;
        int k = 0;
        int tmp=1;
        for(int i = str.length() -1;i >=2;i--){

            char c = str.charAt(i);

           // System.out.println(c);
            switch (c){
                case 'a':
                case 'A':
                    result = 10;
                    break;
                case 'b':
                case 'B':
                    result = 11;
                    break;
                case 'c':
                case 'C':
                    result = 12;
                    break;
                case 'd':
                case 'D':
                    result = 13;
                    break;
                case 'e':
                case 'E':
                    result = 14;
                    break;
                case 'f':
                case 'F':
                    result = 15;
                    break;
                default:
                    result = Integer.valueOf(c - '0');
            }

            for(int j=0;j < k;j++){
                tmp *=16;
            }
            ret += result *tmp;
            k++;
            tmp = 1;

            //System.out.println(c+ " " + result + " " + tmp + " " + ret );
        }
        return  ret;


    }

}
