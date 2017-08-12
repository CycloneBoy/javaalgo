package com.cycloneboy.interview.jzoffer;

/**
 * Created by CycloneBoy on 2017/8/12.
 * 说明: 二维数组中的查找
 *      题目描述
        在一个二维数组中，每一行都按照从左到右递增的顺序排序，
        每一列都按照从上到下递增的顺序排序。请完成一个函数，
        输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Solution_1 {

    /**
     * 思路:
     * 矩阵是有序的，从左下角来看，向上数字递减，向右数字递增，
     * 因此从左下角开始查找，当要查找数字比左下角数字大时。右移
     * 要查找数字比左下角数字小时，上移
     *
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int [][] array) {
        int i=0,j=0;
        for(i = array.length-1,j=0; i >=0 && j < array[0].length;){
            if(target == array[i][j]){
                return  true;
            }else if (target < array[i][j]){
                i--;
                continue;
            }else if(target > array[i][j]){
                j++;
                continue;
            }

        }


        return  false;
    }




    /**
     * 暴力查找
     * @param target
     * @param array
     * @return
     */
    public boolean Find1(int target, int [][] array) {


        for(int i = 0; i < array.length;i++){
            for (int j = 0; j < array[i].length;j++){
                if(target == array[i][j]){
                    return  true;
                }
            }
        }

        return  false;
    }

    /**
     * 一种是：
        把每一行看成有序递增的数组，
        利用二分查找，
        通过遍历每一行得到答案，
        时间复杂度是nlogn
     * @param target
     * @param array
     * @return
     */
    public boolean Find2(int target, int [][] array) {
        for(int i =0 ; i < array.length;i++){
            int low =0;
            int high = array[i].length -1;
            while (low <= high){
                int mid = (low + high)/2;
                if(target > array[i][mid]){
                    low = mid+1;
                }else if (target < array[i][mid]){
                    high = mid - 1;
                }else {
                    return  true;
                }
            }
        }

        return  false;
    }

    /**
     *
     另外一种思路是：
     利用二维数组由上到下，由左到右递增的规律，
     那么选取右上角或者左下角的元素a[row][col]与target进行比较，
     当target小于元素a[row][col]时，那么target必定在元素a所在行的左边,
     即col--；
     当target大于元素a[row][col]时，那么target必定在元素a所在列的下边,
     即row++；
     * @param target
     * @param array
     * @return
     */
    public boolean Find3(int target, int [][] array) {
        int row =0;
        int col = array[0].length -1;
        while (row < array.length -1 && col >=0){
            if(target == array[row][col]){
                return  true;
            }else if(target > array[row][col]){
                row++;
            }else if (target < array[row][col]){
                col--;
            }
        }
        return  false;
    }

    public static void main(String[] args) {

        int [][] array = new int[][]{{1,2,3,4},{2,3,4,5},{3,4,5,9}};

        Solution_1 solution = new Solution_1();
        System.out.println(solution.Find(5,array));

        System.out.println();
    }
}
