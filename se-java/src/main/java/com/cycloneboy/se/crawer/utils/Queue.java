package com.cycloneboy.se.crawer.utils;

import java.util.LinkedList;

/**
 * 队列，保存将要访问的URL
 * Created by CycloneBoy on 2017/10/11.
 */
public class Queue {
    private LinkedList queue = new LinkedList();

    public void enQueue(Object t){
        queue.addLast(t);
    }

    public Object deQueue(){
        return queue.removeFirst();
    }

    public boolean isQueueEmpty(){
        return queue.isEmpty();
    }

    public boolean contains(Object t){
        return queue.contains(t);
    }

    public boolean empty(){
        return queue.isEmpty();
    }
}
