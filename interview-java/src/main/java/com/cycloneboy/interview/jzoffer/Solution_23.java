package com.cycloneboy.interview.jzoffer;

import java.util.Arrays;

/**
 *
 * 题目描述
 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同
 * Created by CycloneBoy on 2017/8/24.
 *
 * 思路：二叉搜索树的合法后序序列是： 对于序列的最后一个结点T是树的根结点，
 * 根据T可以把剩余结点分为两部分，前部分都比T小，后部分都比T大，
 * 这两部分都是合法的后序序列
 */
public class Solution_23 {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length == 0){
            return  false;
        }
        if(sequence.length == 1){
            return  true;
        }
        return  juge(sequence,0,sequence.length-1);
    }



    public boolean juge(int[] a,int start,int end){
        if(start >=end) {
            return  true;
        }
        int i = end;
        //从后面开始找
        while(i > start && a[i-1] > a[end]){
            i--;
        }
        //从前面开始找
        for(int j=start;j< i -1;j++){
            if(a[j] > a[end]){
                return  false;
            }
        }

        return  juge(a,start,i-1) && juge(a,i,end-1);
    }

    public boolean VerifySquenceOfBST2(int [] sequence) {
        if(sequence ==  null ){
            return  false;
        }

        int root = sequence[sequence.length -1];

        //在二叉搜索树中左子树的结点小于根节点
        int i=0;
        for(;i<sequence.length- 1;i++){
            if(sequence[i] > root){
                break;
            }
        }
        //在二叉搜索树中右子树的结点大于根节点
        int j = i;
        for(;j < sequence.length -1;j++){
            if(sequence[j]  < root ){
                return  false;
            }
        }

        //判断左子树是不是二叉搜索树
        boolean left = true;

        if(i > 0){
            left=VerifySquenceOfBST(Arrays.copyOfRange(sequence, 0, i));
        }
        //判断右子树是不是二叉搜索树
        boolean right =true;
        if( i < sequence.length -1){
            right=VerifySquenceOfBST(Arrays.copyOfRange(sequence, i, sequence.length-1));
        }

        return  (left&&right);

    }

    public static void main(String[] args) {
        int[] a = {5,7,6,9,11,10,8};

        Solution_23 solution23 = new Solution_23();
        System.out.println(solution23.VerifySquenceOfBST(a));
    }



}
