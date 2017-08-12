package com.cycloneboy.interview.jzoffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by CycloneBoy on 2017/8/12.
 * 说明：
 * 题目描述
            输入一个链表，从尾到头打印链表每个节点的值。
 */
public class Solution_3 {

    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {

        ArrayList<Integer> result = new ArrayList<Integer>();
        ListNode pnode = listNode;

        while (pnode !=null){
            result.add(pnode.val);
            pnode = pnode.next;
        }


        ArrayList<Integer> newResult = new ArrayList<Integer>();
        for(int i=result.size() -1 ;i>=0;i--){
            newResult.add(result.get(i));
        }
        return newResult;

        //Collections.reverse(arrayList);
        //return  arrayList;
    }

    /**
     * java 递归超简洁版本
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        if(listNode != null){
            this.printListFromTailToHead2(listNode.next);
            arrayList.add(listNode.val);
        }

        return  arrayList;
    }

    /**
     * 借助堆栈的“后进先出”实现
     * @param listNode
     * @return
     */
    public ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        Stack<Integer> stack = new Stack<Integer>();
        while (listNode != null){
            stack.push(listNode.val);
            listNode = listNode.next;
        }

        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        while (!stack.isEmpty()){
            arrayList.add(stack.pop());
        }
        return  arrayList;
    }

    public static void main(String[] args) {
        Solution_3 solution3 = new Solution_3();
        ListNode first1 = new ListNode(1);
        ListNode first2 = new ListNode(2);
        ListNode first3 = new ListNode(3);
        ListNode first4 = new ListNode(4);
        first1.next = first2;
        first2.next = first3;
        first3.next = first4;
        first4.next = null;

        ArrayList<Integer> test = solution3.printListFromTailToHead(first1);
        System.out.println(test);

        ArrayList<Integer> test2 = solution3.printListFromTailToHead2(first1);
        System.out.println(test2);

        ArrayList<Integer> test3 = solution3.printListFromTailToHead3(first1);
        System.out.println(test3);
    }
}
