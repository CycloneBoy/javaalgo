package com.cycloneboy.interview.jzoffer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 题目描述
 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * Created by CycloneBoy on 2017/8/24.
 */
public class Solution_24 {
    private ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if(root == null){
            return  listAll;
        }
        list.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null){
            listAll.add(new ArrayList<Integer>(list));
        }
        FindPath(root.left,target);
        FindPath(root.right,target);
        list.remove(list.size()- 1);
        return listAll;

    }

    public ArrayList<ArrayList<Integer>> FindPath2(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
        if(root == null){
            return listAll;
        }
        Stack<Integer> stack = new Stack<Integer>();
        dfsFind(root,target,stack,listAll);
        return listAll;
    }


    public void dfsFind(TreeNode root, int target,
                         Stack<Integer> path,
                         ArrayList<ArrayList<Integer>> pathList){
        if(root == null){
            return;
        }

        if(root.left == null && root.right == null){
            if(root.val == target){
                ArrayList<Integer> list = new ArrayList<Integer>();
                for(int i:path){
                    list.add(new Integer(i));
                }
                list.add(new Integer(root.val));
                pathList.add(list);
            }
        }else{
            path.push(new Integer(root.val));
            dfsFind(root.left,target -root.val,path,pathList);
            dfsFind(root.right,target -root.val,path,pathList);
            path.pop();
        }
    }
}
