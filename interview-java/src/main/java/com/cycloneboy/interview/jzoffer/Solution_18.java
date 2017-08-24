package com.cycloneboy.interview.jzoffer;

import java.util.Stack;

/**
 * 题目描述
 操作给定的二叉树，将其变换为源二叉树的镜像。
 * Created by CycloneBoy on 2017/8/24.
 */
public class Solution_18 {

    /**
     * 递归实现
     * 先前序遍历这棵树的每个结点，如果遍历到的结点有子结点，
     * 就交换它的两个子节点，
     当交换完所有的非叶子结点的左右子结点之后，就得到了树的镜像
     * @param root
     */
    public void Mirror(TreeNode root) {
       if(root == null || (root.left == null && root.right == null)){
           return;
       }

       TreeNode temp = root.left;
       root.left = root.right;
       root.right = temp;

       if(root.left != null){
           Mirror(root.left);
       }
       if(root.right != null){
           Mirror(root.right);
       }
    }

    //非递归实现
    public void Mirror2(TreeNode root) {
        if(root == null){
            return;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if(node.left != null || node.right != null){
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
            }
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
    }

}
