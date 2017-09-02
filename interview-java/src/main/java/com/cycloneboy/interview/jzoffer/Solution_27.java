package com.cycloneboy.interview.jzoffer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 题目描述
 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 输入描述:
 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
 * Created by CycloneBoy on 2017/8/24.
 */
public class Solution_27 {

    //递归算法
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<String>();

        if(str != null && str.length() > 0){
            PermutationHelper(str.toCharArray(),0,result);
            Collections.sort(result);
        }
        return result;
    }

    private static void PermutationHelper(char[] cs,int i,ArrayList<String> list){
        if(i == cs.length -1) {//解空间的一个叶子节点
            list.add(String.valueOf(cs));//找到一个解
        }else{
            for( int j=i;j < cs.length ; ++j){
                if(j == i || cs[j] != cs[i]){
                    swap(cs,i,j);
                    PermutationHelper(cs,i+1,list);
                    swap(cs,i,j); //复位
                }
            }
        }
    }

    private static void swap(char[] cs ,int i,int j){
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }

    public static void main(String[] args) {
        Solution_27 solution27 = new Solution_27();
        System.out.println(solution27.Permutation("abc"));
    }
}
