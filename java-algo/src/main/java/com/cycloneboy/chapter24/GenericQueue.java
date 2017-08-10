package com.cycloneboy.chapter24;

import java.util.LinkedList;

/**
 * Created by CycloneBoy on 2017/7/3.
 */
public class GenericQueue<E>{
    private LinkedList<E> list = new LinkedList<E>();

    public void enqueue(E e){
        list.addLast(e);
    }

    public E dequeue(){
        return list.removeFirst();
    }

    public int getSize(){
        return list.size();
    }
    public String toString(){
        return "Queue: " + list.toString();
    }
}
