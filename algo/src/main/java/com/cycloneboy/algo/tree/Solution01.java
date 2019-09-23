package com.cycloneboy.algo.tree;

/** Create by sl on 2019-09-17 22:50 */
public class Solution01 {

  public int run(TreeNode root) {
    if (root == null) {
      return 0;
    }

    if (root.left == null && root.right == null) {
      return 1;
    }

    if ((root.left != null && root.right == null)) {
      return run(root.left) + 1;
    }

    if (root.left == null) {
      return run(root.right) + 1;
    }

    return run(root.left) > run(root.right) ? run(root.left) + 1 : run(root.right) + 1;
  }
}
