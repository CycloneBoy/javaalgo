package com.cycloneboy.interview.jzoffer;

/**
 * Created by CycloneBoy on 2017/8/17.
 * 题目:
 *     把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class Solution_6 {
    /**
     * 时间: O(n)
     */
    public int minNumberInRotateArray(int [] array) {
        int min = 0;
        if(array.length == 0){
            return min;
        }
        min = array[0];
        for(int i=0; i< array.length ;i++){
            if(min > array[i]){
                min = array[i];
                break;
            }
        }
        return  min;
    }

    public int minNumberInRotateArray2(int [] array) {
        int low = 0; int high = array.length-1;
        if(array.length == 0){
            return 0;
        }
        while(low < high){
            int mid = low + (high -low)/2;
            if(array[mid] > array[high]){
                low = mid +1;
            }else if(array[mid] == array[high]){
                high = high -1;
            }else{
                high = mid;
            }
        }

        return  array[low];

    }

    public static void main(String[] args) {
        Solution_6 solution6 = new Solution_6();

        int[] input = new int[]{3,4,5,1,2};
        System.out.println(solution6.minNumberInRotateArray2(input));
    }
}
