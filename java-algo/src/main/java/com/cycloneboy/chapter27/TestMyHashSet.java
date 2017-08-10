package com.cycloneboy.chapter27;

/**
 * Created by CycloneBoy on 2017/7/12.
 */
public class TestMyHashSet {
    public static void main(String[] args) {
        // Create a MyHashSet
        MySet<String> set = new MyHashSet<String>();
        set.add("Smith");
        set.add("Anderson");
        set.add("Lewis");
        set.add("Cook");
        set.add("Smith");

        System.out.println("Element in set : " + set);
        System.out.println("Number of elements in set : " + set.size());
        System.out.println("Is Smith in set? " + set.contains("Smith"));

        set.remove("Smith");
        System.out.println("Names in set in uppercase are :");
        for(String s : set){
            System.out.print( s.toUpperCase() + " ");
        }
        set.clear();
        System.out.println("\nElements in set : " + set);
    }
}
