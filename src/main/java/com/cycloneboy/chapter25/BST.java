package com.cycloneboy.chapter25;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by CycloneBoy on 2017/7/4.
 */
public class BST<E extends  Comparable<E>> extends AbstractTree<E>{
    protected  TreeNode<E> root;
    protected  int size = 0;

    /** Create a default binary search tree. */
    public BST(){
    }

    /** Create a binary search tree from an array of objects */
    public  BST(E[] objects){
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    public boolean search(E e) {
        TreeNode<E> current = root; // start from the root

        while (current != null){
            if (e.compareTo(current.elemtent) < 0 ){
                current = current.left;
            }else if (e.compareTo(current.elemtent) > 0){
                current = current.right;
            }else { // element matches current.element
                return  true; // Element is found
            }
        }

        return false;
    }

    public boolean insert(E e) {
        if(root == null){
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null){
                if(e.compareTo(current.elemtent) < 0 ){
                    parent = current;
                    current = current.left;
                }else if (e.compareTo(current.elemtent) > 0){
                    parent = current;
                    current = current.right;
                }else {
                    return  false; // Duplicate node not inserted
                }
            }

            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.elemtent) < 0){
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted successfully
    }

    private TreeNode<E> createNewNode(E e) {
        return  new TreeNode<E>(e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.elemtent + " ");
        inorder(root.right);
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    private void preorder(TreeNode<E> root) {
        if (root == null ) return;
        System.out.print( root.elemtent + " ");
        preorder(root.left);
        preorder(root.right);
       /* postorder(root.left);
        postorder(root.right);
        System.out.print( root.elemtent + " ");*/
    }

    @Override
    public void postorder() {
       postorder(root);
    }

    private void postorder(TreeNode<E> root) {
        if(root == null ) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.elemtent + " ");
        /*System.out.print(root.elemtent + " ");
        preorder(root.left);
        preorder(root.right);*/
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    public boolean delete(E e) {
        // Locate true node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null){
            if(e.compareTo(current.elemtent) < 0){
                parent = current;
                current = current.left;
            }else if(e.compareTo(current.elemtent) < 0){
                parent = current;
                current = current.right;
            }else {
                break; // Element is in the tree pointed at by current
            }
        }

        if(current == null){
            return  false; // Element is not in the tree
        }

        // Case 1: current has no left child
        if(current.left == null){
            // Connect the parent with the right child of the current node
            if(parent == null){
                root = current.right;
            }else {
                if (e.compareTo(parent.elemtent) < 0 ){
                    parent.left = current.right;
                }else{
                    parent.right = current.right;
                }
            }
        }else {
            // Case 2: The current node has a left child.
            // Locate the rightmost node in the left subtree of the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null){
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
               /* System.out.println("current: " + current.elemtent + " - rightMost: " + rightMost.elemtent +
                    " - parentOfRightMost : "  + parentOfRightMost.elemtent);*/
            }

            // Replace the element in current by the element in rightMost
            current.elemtent = rightMost.elemtent;

            // Eliminate rightMost node
            if(parentOfRightMost.right == rightMost){
                parentOfRightMost.right = rightMost.left;
            }else {
                // Special case : parentOfRightMost == current
                parentOfRightMost.left = rightMost.left;
            }
        }
        size-- ;
        return true; // Element deleted successfully
    }

    public int getSize() {
        return size;
    }

    public Iterator<E> iterator() {
        return new InorderIterator();
    }


    /** This inner class in static ,because it does not access any instance members defined
     *  in its outer class
     * @param <E>
     */
    public static class TreeNode<E extends Comparable<E>> {
        protected E elemtent;
        protected TreeNode<E> left;
        protected TreeNode<E> right;

        public TreeNode(E e){
            elemtent = e;
        }
    }

    /** Return the root of the tree */
    public  TreeNode<E> getRoot(){
        return  root;
    }

    /** Returns a path from the root leading to the specified element */
    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<TreeNode<E>>();

        TreeNode<E> current = root ; // Start from the root

        while(current != null){
            list.add(current) ; // Add the node to the list
            if(e.compareTo(current.elemtent) < 0){
                current = current.left;
            }else if(e.compareTo(current.elemtent) > 0 ){
                current = current.right;
            }else {
                break;
            }
        }
        return  list ; // Return an array list of nodes
    }

    private class InorderIterator implements Iterator<E> {
        // Store the elements in the list
        private  ArrayList<E> list = new ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator(){
            inorder();
        }

        /** Inorder traversal from the root */
        private void inorder(){
            inorder(root);
        }

        /** Inorder traversal from a subtree */
        private void inorder(TreeNode<E> root){
            if(root == null) return;
            inorder(root.left);
            list.add(root.elemtent);
            inorder(root.right);
        }

        public boolean hasNext() {
            if(current < list.size()){
                return  true;
            }

            return false;
        }

        public E next() {
            return list.get(current++);
        }

        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear();
            inorder(); // Rebuild the list
        }
    }

    /** Remove all elements from the tree */
    public void clear(){
        root = null;
        size = 0;
    }
}
