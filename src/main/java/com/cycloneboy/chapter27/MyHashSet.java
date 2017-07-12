package com.cycloneboy.chapter27;

import com.sun.xml.internal.ws.runtime.config.TubelineFeatureReader;
import org.w3c.dom.ls.LSInput;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by CycloneBoy on 2017/7/12.
 */
public class MyHashSet<E> implements  MySet<E>{
    // Define the default hash-table size, Must be a power of 2
    private static  int DEFAULT_INITAL_CAPITY = 4;

    // Define the maximum hash-table size .  1 <<30 is same as 2^30
    private static int MAXIMUM_CAPACITY = 1 << 30;

    // Current hash-talbe capacity. Capacity is a power of 2
    private int capacity;

    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    private static float loadFctorThreshold;

    // The number of entries in the map
    private int size = 0;

    // Hash table is an array with each cell being a linked list
    private LinkedList<E>[] table;

    /** Contruct a map with the default capacity and load factor */
    public MyHashSet(){
        this(DEFAULT_INITAL_CAPITY,DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Construct a map with the specified initial capacity and
     *  default load factor
     */
    public MyHashSet(int initialCapacity){
        this(initialCapacity,DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Contruct a map with the specified initiall capacity and
     * load factor
     */
    public MyHashSet(int initialCapacity,float loadFctorThreshold){
        if (initialCapacity > MAXIMUM_CAPACITY){
            this.capacity = MAXIMUM_CAPACITY;
        }else{
            this.capacity = initialCapacity;
        }

        this.loadFctorThreshold = loadFctorThreshold;
        table = new LinkedList[capacity];

    }

    public void clear() {
        size =0 ;
        removeElements();
    }

    private void removeElements() {
        for(int i = 0; i< capacity ; i++){
            if(table[i] != null){
                table[i].clear();
            }
        }
    }

    public boolean contains(E e) {
       int bucketIndex = hash(e.hashCode());
       if(table[bucketIndex] != null){
           LinkedList<E> bucket = table[bucketIndex];
           for(E element : bucket){
               if(element.equals(e)){
                   return  true;
               }
           }
       }

       return  false;
    }

    public boolean add(E e) {
       if (contains(e)){ // Duplicate element not stored
           return  false ;
       }

       if(size + 1 > capacity * loadFctorThreshold ){
           if(capacity == MAXIMUM_CAPACITY){
               throw new RuntimeException("Exceeding maximum capacity");
           }

           rehash();
       }

       int bucketIndex = hash(e.hashCode());

       // Create a linked list for the bucket if not already created
        if(table[bucketIndex] == null){
            table[bucketIndex] = new LinkedList<E>();
        }

        // Add e to hashTable[index]
        table[bucketIndex].add(e);

        size++; // Increase size

        return true;
    }

    /** Rehash the map */
    private void rehash() {
        ArrayList<E> list = setTolist(); // Copy to a list
        capacity <<= 1; // Same as capacity *=2 . <= 1 is more efficient
        table = new LinkedList[capacity]; // Create a hash table
        size = 0; // Reset size to 0;

        for(E element :list){
           add(element); // Add from the old table to the new table
        }
    }

    public boolean remove(E e) {
        if(!contains(e)){
            return  false;
        }

        int bucketIndex = hash(e.hashCode());

        // Remove the element from the bucket
        if(table[bucketIndex] != null){
            LinkedList<E> bucket = table[bucketIndex];
            for(E element : bucket){
                if(e.equals(element)){
                    bucket.remove(element);
                    break;
                }
            }
        }

        size--; // Decrease size
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Iterator<E> iterator() {
        return new MyHashSetIterator(this);
    }

    private class MyHashSetIterator implements java.util.Iterator<E>{
        // Store the elements in a list
        private ArrayList<E> list;
        private int current = 0; // Point to the current element in list
        private MyHashSet<E> set;

        /** Create a list from the set */
        public MyHashSetIterator(MyHashSet<E> set){
            this.set = set;
            list = setTolist();
        }

        public boolean hasNext(){
            if(current < list.size()){
                return  true;
            }

            return  false;
        }

        public E next(){
            return list.get(current++);
        }

        public void remove(){
            // Delete the current element from the hash set
            set.remove(list.get(current));
            list.remove(current); // Remove current element from the list
        }
    }

    private int hash(int hashcode) {
        return supplementalHash(hashcode) & (capacity -1);
    }

    private static int supplementalHash(int h) {
        h^= (h >>> 20)^(h>>>12);
        return  h ^(h>>>7)^(h>>>4);
    }

    /** Return a power of 2 for initialCapacity */
    private int trimToPowerOf2(int initialCapacity){
        int capacity = 1;
        while (capacity < initialCapacity ){
            capacity <<= 1 ; // Same as capacity *=2 . <= is more efficient
        }

        return capacity;
    }

    private ArrayList<E> setTolist() {
        ArrayList<E> list = new ArrayList<E>();

        for (int i = 0; i < capacity; i++) {
           if(table[i] != null){
               for(E e : table[i]){
                   list.add(e);
               }
           }
        }

        return list;
    }

    public String toString(){
        ArrayList<E> list = setTolist();
        StringBuilder builder = new StringBuilder("[");

        // Add the element except the last one to the string builder
        for(int i = 0; i< list.size()-1; i++){
            builder.append(list.get(i) + ", ");
        }

        // Add the last element in the list to the string builder
        if(list.size() == 0){
            builder.append("]");
        }else {
            builder.append(list.get(list.size() - 1) + "]");
        }

        return  builder.toString();
    }
}
