package com.cycloneboy.interview.jzoffer;

/**
 * 题目描述
 输入两棵二叉树A，B，判断B是不是A的子结构。
 （ps：我们约定空树不是任意一个树的子结构）
 * Created by CycloneBoy on 2017/8/24.
 */
public class Solution_17 {

    /*思路：参考剑指offer
    1、首先设置标志位result = false，因为一旦匹配成功result就设为true，
    剩下的代码不会执行，如果匹配不成功，默认返回false
    2、递归思想，如果根节点相同则递归调用DoesTree1HaveTree2（），
    如果根节点不相同，则判断tree1的左子树和tree2是否相同，
    再判断右子树和tree2是否相同
    3、注意null的条件，HasSubTree中，如果两棵树都不为空才进行判断，
    DoesTree1HasTree2中，如果Tree2为空，则说明第二棵树遍历完了，即匹配成功，
    tree1为空有两种情况（1）如果tree1为空&&tree2不为空说明不匹配，
    （2）如果tree1为空，tree2为空，说明匹配。
     
    */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        boolean result = false;

        if(root1 != null && root2 != null){
            if(root1.val == root2.val){
                result = DoseTree1HaveTree2(root1,root2);
            }
            if(!result){
                result = HasSubtree(root1.left,root2);
            }
            if (!result){
                result = HasSubtree(root1.right,root2);
            }
        }

        return  result;
    }

    //判断两个树是否是同一个二叉树
    public boolean DoseTree1HaveTree2(TreeNode root1,TreeNode roo2){
        if(roo2 == null){
            return  true;
        }
        if(root1 == null){
            return  false;
        }

        if(root1.val != roo2.val){
            return  false;
        }

        return DoseTree1HaveTree2(root1.left,roo2.left) && DoseTree1HaveTree2(root1.right,roo2.right);

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

        TreeNode[] root2 = new TreeNode[3];
        for(int i=0;i<3;i++ ){
            root2[i] = new TreeNode(i+1);
            root2[i].left = root2[i].right = null;
        }

        root2[0].left = root2[1];
        root2[0].right = root2[2];

        Solution_17 solution17 = new Solution_17();
        System.out.println(solution17.HasSubtree(root1[0],root1[0]));
    }
}
