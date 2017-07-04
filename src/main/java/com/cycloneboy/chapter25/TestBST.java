package com.cycloneboy.chapter25;

import java.util.ArrayList;

/**
 * Created by CycloneBoy on 2017/7/4.
 */
public class TestBST {
    public static void main(String[] args) {
        // Create a BST
        BST<String> tree = new BST<String>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Peter");
        tree.insert("Daniel");

        // Traverse tree
        System.out.print("Inorder (sorted): ");
        tree.inorder();
        System.out.print("\nPostorder (sorted): ");
        tree.postorder();
        System.out.print("\nPreorder (sorted): ");
        tree.preorder();

        System.out.print("\n The number of node is "  + tree.getSize());

        // Search for an element
        System.out.print("\n Is Peter in the tree? " + tree.search("Peter"));

        // Get a path from the root to Peter
        System.out.print("\n A path from the root to Peter is : " );
        ArrayList<BST.TreeNode<String>> path = tree.path("Peter");

        for (int i = 0; path != null && i < path.size(); i++) {
            System.out.print(path.get(i).elemtent + " ");
        }

        Integer[] numbers = {2,4,3,1,8,5,6,7};
        BST<Integer> intTree = new BST<Integer>(numbers);
        System.out.println("\nInorder (sorted) : ");
        intTree.inorder();

    }
}
