package com.cycloneboy.chapter25;

/**
 * Created by CycloneBoy on 2017/7/4.
 */
public class TestBSTDelete {
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
        printTree(tree);

        System.out.println("\nAfter delete George:");
        tree.delete("George");
        printTree(tree);

        System.out.println("\nAfter delete Adam:");
        tree.delete("Adam");
        printTree(tree);

        System.out.println("\nAfter delete Michael:");
        tree.delete("Michael");
        printTree(tree);

    }

    private static void printTree(BST<String> tree) {
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
