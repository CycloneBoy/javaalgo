package com.cycloneboy.interview.huawei;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 题目描述
 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。请你协助明明完成“去重”与“排序”的工作。


 Input Param
 n               输入随机数的个数
 inputArray      n个随机整数组成的数组

 Return Value
 OutputArray    输出处理后的随机整数


 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。

 输入描述:
 输入多行，先输入随机整数的个数，再输入相应个数的整数
 输出描述:
 返回多行，处理后的结果
 * Created by CycloneBoy on 2017/9/12.
 */
public class Main3 {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            TreeSet<Integer> set = new TreeSet<>();
            int n = input.nextInt();
            if(n > 0){
                for(int i = 0; i<n;i++){
                    set.add(input.nextInt());
                }
            }
            for(Integer i : set){
                System.out.println(i);
            }
        }
    }




    public static void main1(String[] args) {
        Scanner input = new Scanner(System.in);
        int total = 0;
        total = Integer.valueOf(input.nextLine());
        int[] tmp = new int[total];

        for(int i = 0 ; i < total;i++){
            tmp[i] = Integer.valueOf(input.nextLine());
        }

        //System.out.println(total + " " + tmp.length );

        List<Integer> result = test(tmp,total);

        for(int i = 0; i < result.size();i++){
            System.out.println(result.get(i));
        }
    }

    static  List<Integer>  test(int[] number,int total){
        List<Integer> list = new LinkedList<Integer>();

        int temp =0;
        for(int i =0 ; i < total-1;i++ ){
            for(int j =i+1;j <total ;j++ ){

                if( number[j] < number[i]){
                    int tmp = number[i];
                    number[i] = number[j];
                    number[j] = tmp;
                }
            }
            if(number[i] != temp){
                list.add(number[i]);
                //System.out.println(number[i]);
                temp = number[i];
            }
        }
        list.add(number[total -1]);
        return list;
    }

}
