package com.cycloneboy.interview.huawei;

import java.util.Scanner;

/**
 * 题目描述
 •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 输入描述:
 连续输入字符串(输入2次,每个字符串长度小于100)
 输出描述:
 输出到长度为8的新字符串数组
 * Created by CycloneBoy on 2017/9/12.
 */
public class Main5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String s= new String(sc.nextLine());
            if(s.length() %8 != 0){
                s  = s+ "00000000";
            }

            while (s.length() >=8){
                System.out.println(s.substring(0,8));
                s = s.substring(8);
            }
        }
    }



    public static void main1(String[] args) {
        Scanner input = new Scanner(System.in);
        String str1 = input.nextLine();
        String str2 = input.nextLine();

        test(str1);
        System.out.println();
        test(str2);
    }

    public static void  test(String str){
        if(str == null || str.trim() == " "){
            return ;
        }

       int m = str.length()/8;
        int n = str.length() % 8;


        for(int i = 0 ; i < str.length()/8 ;i++){
            System.out.println(str.substring(i* 8,i*8+8));
        }

        for(int i = 0 ; i < 8 && m*8 < str.length() ;i++){
            if(m*8 +i < str.length()){
                System.out.print(str.charAt(m*8 +i));
            }else{
                System.out.print("0");
            }
        }
    }
}
