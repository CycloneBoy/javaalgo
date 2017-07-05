package com.cycloneboy.chapter26;

import com.cycloneboy.chapter25.BST;

/**
 * Created by CycloneBoy on 2017/7/5.
 */
public class TestAVLTree {
    public static void main(String[] args) {
        // Create an AVL tree
        AVLTree<Integer> tree = new AVLTree<Integer>(new Integer[]{25,20,5});
        System.out.print("After inserting 25, 20, 5:");
        printTree(tree);

        tree.insert(34);
        tree.insert(50);
        System.out.print("\nAfter inserting 34, 50:");
        printTree(tree);

        tree.insert(30);
        System.out.print("\nAfter inserting 30:");
        printTree(tree);

        tree.insert(10);
        System.out.print("\nAfter inserting 10:");
        printTree(tree);

        tree.delete(34);
        tree.delete(30);
        tree.delete(50);
        System.out.print("\nAfter inserting 34, 30, 50:");
        printTree(tree);

        tree.delete(5);
        System.out.print("\nAfter inserting 5:");
        printTree(tree);

        System.out.println("\nTraversa the elements in the tree: ");
        for(int e : tree){
            System.out.print(e + " ");
        }
    }

    public static void printTree(BST tree) {
        // Traverse tree
        System.out.print("Inorder (sorted): ");
        tree.inorder();
        System.out.print("\nPostorder (sorted): ");
        tree.postorder();
        System.out.print("\nPreorder (sorted): ");
        tree.preorder();
        System.out.print("\nThe number of node is " + tree.getSize());
        System.out.println();
    }
}
