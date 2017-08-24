package com.cycloneboy.interview.jzoffer;


import java.util.Stack;

/**
 * 题目描述
    输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * Created by CycloneBoy on 2017/8/24.
 *
 * 中序遍历的思想
 */
public class Solution_26 {
    TreeNode head = null;
    TreeNode realHead = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
            ConvertSub(pRootOfTree);
            return realHead;
    }

    private void ConvertSub(TreeNode pRootOfTree){
        if(pRootOfTree == null){
            return;
        }
        ConvertSub(pRootOfTree.left);
        if(head == null){
            head = pRootOfTree;
            realHead = pRootOfTree;
        }else {
            head.right = pRootOfTree;
            pRootOfTree.left = head;
            head = pRootOfTree;
        }
        ConvertSub(pRootOfTree.right);
    }

    /**
     * 非递归
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if(pRootOfTree == null){
            return  null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = pRootOfTree;
        TreeNode pre = null;// 用于保存中序遍历序列的上一节点
        boolean isFirst = true;
        while(p!= null || !stack.isEmpty()){
            while (p != null){      //到达最左边的结点
                stack.push(p);
                p = p.left;
            }
            p = stack.pop();
            if(isFirst){
                pRootOfTree = p;// 将中序遍历序列中的第一个节点记为root
                pre = pRootOfTree;
                isFirst = false;
            }else {
                pre.right = p;
                p.left = pre;
                pre = p;
            }
            p = p.right;
        }

        return pRootOfTree;
    }
}
