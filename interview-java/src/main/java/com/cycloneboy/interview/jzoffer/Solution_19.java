package com.cycloneboy.interview.jzoffer;

import java.rmi.MarshalException;
import java.util.ArrayList;

/**
 * 题目描述
 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 * Created by CycloneBoy on 2017/8/24.
 */
public class Solution_19 {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if(matrix == null){
            return  result;
        }

        int row = matrix.length; //行数
        int col = matrix[0].length;//列数

        //计算打印的圈数
        int circle = ((row < col? row: col) -1)/ 2 + 1;

        for(int i = 0;i < circle ;i++) {

            int endX = matrix.length - 1 - i;
            int endY = matrix[i].length - 1 - i;
            // 从左到右打印一行
            for (int j = i; j <= endX; j++) {
                result.add(matrix[i][j]);
            }

            // 从上到下打印一列
            if (i < endY) {
                for (int j = i + 1; j <= endY; j++) {
                    result.add(matrix[j][endY]);
                }
            }

            // 从右到左打印一行
            if (i < endX && i < endY) {
                for (int j = endX - 1; j >= i; j--) {
                    result.add(matrix[endY][j]);
                }
            }

            // 从下到上打印一行
            if (i < endX && i < endY - 1) {
                for (int j = endY - 1; j >= i + 1; j--) {
                    result.add(matrix[j][i]);
                }
            }
        }
         return  result;
    }

    public ArrayList<Integer> printMatrix1(int [][] matrix) {
        int row = matrix.length; //行数
        int col = matrix[0].length;//列数
        ArrayList<Integer> result = new ArrayList<Integer>();

        if(row == 0 || col == 0){
            return  result;
        }

        //定义每一圈的关键变量
        int left =0,top=0,right = col -1,bottom=row-1;
        while(left <= right && top <= bottom){
            //从左到右
            for(int i = left;i<= right;i++){
                result.add(matrix[top][i]);
            }
            //从上到下
            for(int i = top + 1;i<= bottom;i++){
                result.add(matrix[i][right]);
            }
            //从右到左
            if(top != bottom){
                for(int i = right -1;i>=left;i--){
                    result.add(matrix[bottom][i]);
                }
            }
            //从下到上
            if(left!=right){
                for(int i= bottom-1;i>top;i--){
                    result.add(matrix[i][left]);
                }
            }

            left++;top++;right--;bottom--;
        }
        return  result;

    }
}
