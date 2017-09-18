package com.cycloneboy.interview.huawei;

import java.util.Scanner;

/**
 *题目描述
 功能:输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ）
 最后一个数后面也要有空格

 详细描述：

 函数接口说明：
 public String getResult(long ulDataInput)
 输入参数：
 long ulDataInput：输入的正整数
 返回值：
 String


 输入描述:
 输入一个long型整数
 输出描述:
 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。最后一个数后面也要有空格。
 * Created by CycloneBoy on 2017/9/12.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
           String str = sc.nextLine().substring(2);
           System.out.println(Integer.parseInt(str,16));
        }
    }

    static String getResult(long ulDataInput){

        return null;
    }

}
