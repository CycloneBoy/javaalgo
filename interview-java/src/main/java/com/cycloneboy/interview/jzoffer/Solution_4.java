package com.cycloneboy.interview.jzoffer;

import java.util.Arrays;

/**
 * Created by CycloneBoy on 2017/8/12.
 * 说明:
 *      题目描述
 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
 则重建二叉树并返回。

 结题思路:
 1.先求出根节点（前序序列第一个元素）。
 2.将根节点带入到中序遍历序列中求出左右子树的中序遍历序列。
 3.通过左右子树的中序序列元素集合带入前序遍历序列可以求出左右子树的前序序列。
 4.左右子树的前序序列第一个元素分别是根节点的左右儿子
 5.求出了左右子树的4种序列可以递归上述步骤
 */
public class Solution_4 {

    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root = reConstructBinaryTree(pre,0,pre.length-1,in,0,in.length -1);
        return  root;
    }

    /**
     *  // 前序遍历和中序遍历
     * @param pre
     * @param startPre
     * @param endPre
     * @param in
     * @param startIn
     * @param endIn
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre,int startPre,int endPre,
                                          int[] in,int startIn,int endIn ){
        if(startPre > endPre || startIn > endIn){
            return  null;
        }
        TreeNode root = new TreeNode(pre[startPre]);
        for(int i = startIn;i<= endIn;i++){
            if(in[i] == pre[startPre]){
                root.left = reConstructBinaryTree(pre,startPre +1,startPre+i-startIn,in,startIn,i-1);
                root.right = reConstructBinaryTree(pre,i - startIn + startPre+1 ,endPre,in,i+1,endIn);
            }
        }
        return  root;
    }

    /**
     * 通过划分找到根节点然后进行左子树和右子树的划分后进行递归
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree2(int [] pre,int [] in) {
        if(pre.length == 0 || in.length == 0){
            return  null;
        }
        TreeNode root = new TreeNode(pre[0]); // 通过前序遍历获取根节点
        for(int i = 0 ; i < in.length;i ++ ){
            if(pre[0] == in[i]){  // 在中序遍历中找到根节点
                root.left = reConstructBinaryTree2(Arrays.copyOfRange(pre,1,i+1),
                        Arrays.copyOfRange(in,0,i) );
                root.right = reConstructBinaryTree2(Arrays.copyOfRange(pre,i+1,pre.length),
                        Arrays.copyOfRange(in, i+1,in.length));
            }
        }
        return  root;
    }

    /**
     * 自定义 for循环进行拷贝
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree3(int [] pre,int [] in) {
        int i = 0;
        if(pre.length != in.length || pre.length == 0 || in.length ==0){
            return  null;
        }
        TreeNode root = new TreeNode(pre[0]); //创建根节点
        while(in[i] != root.val){  // 在中序遍历中找到根节点
            i++;
        }
        int[] preLeft = new int[i];
        int[] inLeft = new int[i];
        int[] preRight = new int[pre.length - i -1]; //去除根节点后的长度
        int[] inRight = new int[in.length - i -1];
        for(int j = 0;j < in.length; j++){  // 左右划分进行拷贝
            if(j < i){
                preLeft[j] = pre[j+1];
                inLeft[j] = in[j];
            }else if( j > i){
                preRight[j-i-1] = pre[j];
                inRight[j-i-1] = in[j];
            }
        }
        root.left = reConstructBinaryTree3(preLeft,inLeft);
        root.right = reConstructBinaryTree3(preRight,inRight);
        return  root;
    }

    public TreeNode reConstructBinaryTree4(int [] pre,int [] in) {
         return  reConBTree(pre,0,pre.length-1,in,0,in.length -1);
    }

    public TreeNode reConBTree(int [] pre,int preleft,int preright,
                               int[] in,int inleft,int inright){
        if(preleft > preright || inleft > inright){  //当达到边界条件时候返回null
            return  null;
        }
        //新建一个TreeNode
        TreeNode root = new TreeNode(pre[preleft]); // 获取前序遍历的根节点
        //对中序数组进行输入边界的遍历
        for(int i =inleft;i <= inright; i++){
            if(pre[preleft] == in[i]){ // 在中序遍历中找到 根节点的位置
                // 重构左子树，注意边界条件
                root.left = reConBTree(pre,preleft+1,preleft+i - inleft,in,inleft,i-1);
                root.right = reConBTree(pre,preleft+i+1-inleft,preright,in,i+1,inright);
                // 重构右子树，注意边界条件

            }
        }
        return  root;
    }
    public static void main(String[] args) {
        Solution_4 solution4 = new Solution_4();
        int [] preOrder = {1,2,4,7,3,5,6,8};
        int [] inOrder = {4,7,2,1,5,3,8,6};
        TreeNode root = solution4.reConstructBinaryTree(preOrder,inOrder);
        System.out.println();
    }
}
