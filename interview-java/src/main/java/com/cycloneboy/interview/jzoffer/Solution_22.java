package com.cycloneboy.interview.jzoffer;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;


/**
 * 题目描述
 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 * Created by CycloneBoy on 2017/8/24.
 */
public class Solution_22 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(root == null){
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.add(root);
        while (queue.size() > 0){
            TreeNode temp = queue.poll();
            result.add(temp.val);
            if(temp.left != null){
               queue.add(temp.left);
            }
            if(temp.right!= null){
                queue.add(temp.right);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode[] root1 = new TreeNode[5];
        for(int i=0;i<5;i++ ){
            root1[i] = new TreeNode(i+1);
            root1[i].left = root1[i].right = null;
        }

        root1[0].left = root1[1];
        root1[0].right = root1[2];

        root1[2].left = root1[3];
        root1[2].right = root1[4];

        Solution_22 solution22 = new Solution_22();

        ArrayList<Integer> result = solution22.PrintFromTopToBottom(root1[0]);

        for(Integer num : result){
            System.out.print( num.intValue() + " ");
        }
    }
}
