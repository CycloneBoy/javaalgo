package com.cycloneboy.chapter24;

import com.cycloneboy.chapter23.Heap;

/**
 * Created by CycloneBoy on 2017/7/3.
 */
public class MyPriorityQueue<E extends Comparable<E>>{
    private Heap<E> heap = new Heap<E>();

    public void enqueue(E newObject){
        heap.add(newObject);
    }

    public E dequeue(){
        return  heap.remove();
    }

    public int getSize(){
        return heap.getSize();
    }
}
