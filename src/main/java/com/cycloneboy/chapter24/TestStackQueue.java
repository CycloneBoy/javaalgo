package com.cycloneboy.chapter24;

/**
 * Created by CycloneBoy on 2017/7/3.
 */
public class TestStackQueue {
    public static void main(String[] args) {
        // Create a stack
        GenericStack<String> stack = new GenericStack<String>();

        // And elements to the stack
        stack.push("Tom") ;
        System.out.println("(1)" + stack);

        stack.push("Susan") ;
        System.out.println("(2)" + stack);

        stack.push("Kim") ;
        System.out.println("(3)" + stack);

        stack.push("Michale") ;
        System.out.println("(4)" + stack);

        System.out.println("(5)" + stack.pop());
        System.out.println("(6)" + stack.pop());
        System.out.println("(7)" + stack);

        // Greate a queue
        GenericQueue<String> queue = new GenericQueue<String>();
        queue.enqueue("Tom");
        System.out.println("(8)" + queue);

        queue.enqueue("Suan");
        System.out.println("(9)" + queue);
        queue.enqueue("Kim");
        System.out.println("(10)" + queue);
        queue.enqueue("Michael");
        System.out.println("(11)" + queue);

        System.out.println("(12)" + queue.dequeue());
        System.out.println("(13)" + queue.dequeue());
        System.out.println("(14)" + queue);

    }

}
