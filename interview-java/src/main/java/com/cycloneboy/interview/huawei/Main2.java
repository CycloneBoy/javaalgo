package com.cycloneboy.interview.huawei;

import java.util.Scanner;

/**
 * 题目描述
 计算字符串最后一个单词的长度，单词以空格隔开。
 输入描述:
 一行字符串，非空，长度小于5000。
 输出描述:
 整数N，最后一个单词的长度。
 * Created by CycloneBoy on 2017/9/12.
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();

        System.out.println(test(str));
    }

    static int test(String str){
        if(str == null){
            return -1;
        }

        int result = 0;
        for(int i = str.trim().length() -1;i >=0;i-- ){
            if(str.charAt(i) != ' '){
                result++;
            }else {
                break;
            }
        }

        return  result;
    }

    //正则表达式
    public static int lengthOfLast(String str){
        String[] s= str.split(" ");
        return s[s.length -1].length();
    }
}
