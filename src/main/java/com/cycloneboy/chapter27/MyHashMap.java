package com.cycloneboy.chapter27;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by CycloneBoy on 2017/7/8.
 */
public class MyHashMap<K,V> implements MyMap<K,V>{
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

    LinkedList<MyMap.Entry<K,V>> [] table;

    /** Contruct a map with the default capacity and load factor */
    public MyHashMap(){
        this(DEFAULT_INITAL_CAPITY,DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Construct a map with the specified initial capacity and
     *  default load factor
     */
    public MyHashMap(int initialCapacity){
        this(initialCapacity,DEFAULT_MAX_LOAD_FACTOR);
    }

    /** Contruct a map with the specified initiall capacity and
     * load factor
     */
    public MyHashMap(int initialCapacity,float loadFctorThreshold){
        if (initialCapacity > MAXIMUM_CAPACITY){
            this.capacity = MAXIMUM_CAPACITY;
        }else{
            this.capacity = initialCapacity;
        }

        this.loadFctorThreshold = loadFctorThreshold;
        table = new LinkedList[capacity];

    }

    public void clear() {
        size = 0;
        removeEntries();
    }

    private void removeEntries() {
    }

    public boolean containKey(K key) {
        if(get(key) != null){
            return  true;
        }else {
            return  false;
        }
    }

    public boolean containValue(V value) {
        for(int i = 0; i< capacity; i++){
            if(table[i] != null){
                LinkedList<Entry<K,V>> bucket = table[i];
                for(Entry<K,V> entry : bucket){
                    if(entry.getValue().equals(value)){
                        return  true;
                    }
                }
            }
        }

        return  false;
    }

    public Set<Entry<K, V>> entrySet() {
       Set<MyMap.Entry<K,V>> set = new HashSet<Entry<K, V>>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K,V>> bucket = table[i];
                for(Entry<K,V> entry : bucket){
                    set.add(entry);
                }
            }
        }

        return  set;
    }

    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        if(table[bucketIndex] != null){
            LinkedList<Entry<K,V>> bucket = table[bucketIndex];
            for(Entry<K,V> entry:bucket){
                if(entry.getKey().equals(key)){
                    return entry.getValue();
                }
            }
        }

        return  null;
    }

    private int hash(int i) {
        return 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Set<K> keySet() {
       Set<K> set = new HashSet<K>();

       for(int i =0 ; i< capacity;i++){
           if(table[i] != null){
               LinkedList<Entry<K,V>> bucket = table[i];
               for(Entry<K,V> entry : bucket){
                   set.add(entry.getKey());
               }
           }
       }

       return  set;
    }

    public V put(K key, V value) {
        if(get(key) != null){ // The key is already in the map
            int bucketIndex = hash(key.hashCode());
            LinkedList<Entry<K,V>> bucket = table[bucketIndex];
           //for(Entry<K,V>);
        }

        return  value;
    }

    public void remove(K key) {

    }

    public int size() {
        return 0;
    }

    public Set<V> values() {
        return null;
    }
}
