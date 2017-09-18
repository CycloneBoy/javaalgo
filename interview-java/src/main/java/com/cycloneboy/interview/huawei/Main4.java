package com.cycloneboy.interview.huawei;

import java.util.Scanner;

/**
 * 题目描述
 写出一个程序，接受一个有字母和数字以及空格组成的字符串，
 和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 输入描述:
 输入一个有字母和数字以及空格组成的字符串，和一个字符。
 输出描述:
 输出输入字符串中含有该字符的个数。
 * Created by CycloneBoy on 2017/9/12.
 */
public class Main4 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String str = input.nextLine();
            String c = input.nextLine();

            System.out.println(testCount(str,c));
        }
    }

    static  int testCount(String str,String c){
        if(str == null){
            return -1;
        }
        int result = 0;
        String tmpStr = str.trim().toLowerCase();
        String c1 = c.toLowerCase();
        for(int i = 0; i <tmpStr.length() ;i++){
            if(tmpStr.charAt(i) == c1.charAt(0) ){
                result++;
            }
        }
        return result;
    }

}
