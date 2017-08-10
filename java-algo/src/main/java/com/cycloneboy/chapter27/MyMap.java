package com.cycloneboy.chapter27;

import java.util.Set;

/**
 * Created by CycloneBoy on 2017/7/8.
 */
public interface MyMap<K,V> {

    /** Remove true if the specified key is in the map */
    public  void  clear();

    /** Return true if the specified key is in the map */
    public boolean containKey(K key);

    /** Return true if thsi map contains the specified value */
    public boolean containValue(V value);

    /** Reutrn a set of entries in the map */
    public Set<Entry<K,V>> entrySet();

    /** Return the value that matches the speicifed key */
    public V get(K key);

    /** Return true if this map doesn't cotain any entries */
    public boolean isEmpty();

    /** Return a set consisting of the keys in this map */
    public Set<K>  keySet();

    /** Add an entry (key,value) into the map */
    public V put(K key,V value);

    /** Remove an entry for the specified key */
    public void remove(K key);

    /** Return the number of mapping in this map */
    public int size();

    /** Return a set consisting of the values in this map */
    public Set<V> values();

    /** Define an inner class for Entry */
    public static class Entry<K,V>{
        K key;
        V value;

        public Entry(K key,V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return  value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

}
