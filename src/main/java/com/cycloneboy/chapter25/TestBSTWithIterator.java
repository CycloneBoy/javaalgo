package com.cycloneboy.chapter25;

/**
 * Created by CycloneBoy on 2017/7/4.
 */
public class TestBSTWithIterator {
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

        for(String s:tree){
            System.out.print(s.toUpperCase() + " ");
        }
    }
}
