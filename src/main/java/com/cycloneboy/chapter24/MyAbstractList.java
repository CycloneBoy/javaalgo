package com.cycloneboy.chapter24;

/**
 * Created by CycloneBoy on 2017/7/3.
 */
public abstract class MyAbstractList<E> implements MyList<E> {
    protected int size = 0; // The size of the list

    /** Create a default list */
    public MyAbstractList() {
    }

    /** Create a list from an array of objects */
    protected MyAbstractList(E[] objects){
        for (int i = 0; i < objects.length; i++) {
           add(objects[i]);
        }
    }

    /** Add a new element at the end of this list. */
    public void add(E e) {
        add(size,e);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean remove(E e) {
        if(indexOf(e) >= 0){
            remove(indexOf(e));
            return  true;
        }else {
            return false;
        }
    }
}




