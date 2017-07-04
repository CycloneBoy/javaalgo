package com.cycloneboy.chapter25;

/**
 * Created by CycloneBoy on 2017/7/4.
 */
public abstract class AbstractTree<E> implements  Tree<E> {

    public void inorder() {

    }

    public void preorder() {

    }

    public void postorder() {

    }


    public boolean isEmpty() {
        return getSize() == 0;
    }
}
