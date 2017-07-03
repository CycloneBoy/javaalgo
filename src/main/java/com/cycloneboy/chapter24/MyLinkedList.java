package com.cycloneboy.chapter24;

import com.cycloneboy.chapter23.InsertionSort;
import com.sun.org.apache.xml.internal.utils.res.IntArrayWrapper;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import java.util.Iterator;

/**
 * Created by CycloneBoy on 2017/7/3.
 */
public class MyLinkedList<E> extends  MyAbstractList<E> {
    private Node<E> head,tail;

    public MyLinkedList() {
    }

    /** Create a list from an array of Object */
    public MyLinkedList(E[] objects){
        super(objects);
    }

    /** Return the head element in the list */
    public  E getFirst(){
        if (size == 0 ){
            return  null;
        }else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public  E getLast(){
        if (size == 0 ){
            return  null;
        }else {
            return tail.element;
        }
    }

    /** Add an element to the begain in the list */
    public void addFirst(E e ){
        Node<E> newNode = new Node<E>(e); // Create a new node
        newNode.next = head; // Link the new node with the head
        head = newNode; // head points to the new node
        size++; // Increase list size

        if (tail == null) // The new node is the only node in list
            tail = head;

    }

    /** Add an element to the last in the list */
    public void addLast(E e ){
        Node<E> newNode = new Node<E>(e); // Create a new node for e

        if (tail == null){
           head = tail = newNode; // The only node in list
        }else {
            tail.next = newNode ;  // Link the new node with the last node
            tail = tail.next; // tail now points to the last node
        }

        size ++ ; // Increase size
    }
    public void add(int index, E e) {
        if (index  == 0) addFirst(e); // Insert first
        else if (index >= size) addLast(e); // Insert last
        else{ // Insert in the middle
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<E>(e);
            (current.next).next = temp;
            size++;
        }
    }

    /** Remove the head node and return the object that is contained in the removed node*/
    public E removeFirst(){
        if (size == 0) return  null ; // Nothing to delete
        else{
            Node<E> temp = head; // Keep the first node temporarily
            head = head.next; // Move head to point to next node
            size--; // Reduce size by 1
            if (head == null) tail = null; // List becomes empty
            return  temp.element ;  // Return the deleted element
        }
    }

    /** Remove the last node and return the object that is contained in the removed node*/
    public E removeLast(){
        if (size == 0) return  null; // Nothing to remove
        else if(size == 1) { // Only one element in the list
            Node<E> temp = head;
            head = tail = null; // List becomes empty
            size = 0;
            return  temp.element;
        }else {
            Node<E> current = head;

            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size --;
            return  temp.element;
        }
    }

    public String toString(){
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head ;
        for(int i =0;i < size;i++){
            result.append(current.element);
            current = current.next;
            if(current != null){
                result.append(", "); // Separate two elements with a comma
            }else {
                result.append("]"); // Insert the closing  ] in the string
            }
        }

        return  result.toString();
    }
    /** Remove the element at the specified position in this list.
     * Return the element that was removed from the list.
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= size) return  null; // Out of range
        else if(index == 0) return  removeFirst(); // Remove first
        else if(index == size -1) return  removeLast(); // Remove last
        else{
            Node<E> previous = head;

            for(int i = 0;i < index ; i ++){
                previous = previous.next;
            }

            Node<E> current = previous.next;
            previous.next = current.next;
            size -- ;
            return  current.element;
        }
    }

    public void clear() {
        size = 0;
        head = tail = null;
    }

    public boolean contains(E e) {
        //System.out.println("Implementation left as an exercise");
        if (size == 0) return  false;
        Node<E> current = head ;
        for (int i = 0; i < size - 1; i++) {
             if (current.element.equals(e)) return  true;
             current = current.next;
        }

        return false;
    }

    public E get(int index) {
        // System.out.println("Implementation left as an exercise");
        if (index < 0 || index >= size) return  null;
        else if(index == 0) return  head.element;
        else {
            Node<E> current = head ;
            for (int i = 0; i < index ; i++) {
                current = current.next;
            }
            return current.element;
        }

    }

    public int indexOf(E e) {
        System.out.println("Implementation left as an exercise");
        return 0;
    }

    public int lastIndexOf(E e) {
        System.out.println("Implementation leaf as an exercise");
        return 0;
    }


    public Object set(int index, E e) {
        System.out.println("Implementation left as an exercise");
        return null;
    }

    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements  java.util.Iterator<E>{
        private Node<E> current = head ; // Current index


        public boolean hasNext() {
            return (current != null);
        }

        public E next() {
           E e = current.element;
           current = current.next;
           return  e;
        }

        public void remove() {
            System.out.println("Implementation left as an exercise");
        }
    }
    // This class if only used in LinkList, so it is private.
    // This class does not need to access any instance members of LinkedList ,so
    // it is defined static.
    private static class Node<E>{
        E element;
        Node<E> next;

        public Node(E element){
            this.element = element;
        }
    }
}
