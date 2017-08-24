package com.cycloneboy.interview.jzoffer;

/**
 * 题目描述
 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 * Created by CycloneBoy on 2017/8/24.
 */
public class Solution_16 {

    /**
     * 递归版本
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null){
            return  list2;
        }else if(list2 == null){
            return  list1;
        }

        ListNode head = null;

        if(list1.val < list2.val){
            head = list1;
            head.next = Merge(list1.next,list2);
        }else{
            head = list2;
            head.next = Merge(list1,list2.next);
        }

        return head;
    }

    /**
     * 非递归版本
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge2(ListNode list1,ListNode list2) {
        if(list1 == null){
            return  list2;
        }else if(list2 == null){
            return  list1;
        }

        ListNode head = null;
        ListNode current = null;

        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                if(head == null){
                    head = current = list1;
                }else{
                    current.next = list1;
                    current = current.next;
                }
                list1 = list1.next;
            }else{
                if(head == null){
                    head = current = list2;
                }else{
                    current.next = list2;
                    current = current.next;
                }
                list2 = list2.next;
            }
        }

        if(list1 == null){
            current.next = list2;
        }else {
            current.next = list1;
        }
        return  head;
    }

    public ListNode Merge3(ListNode list1,ListNode list2) {
        if(list1 == null){
            return  list2;
        }else if(list2 == null){
            return  list1;
        }

        ListNode head = new ListNode(-1); //头结点
        head.next = null;
        ListNode root = head;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                head.next = list1;
                head = list1;
                list1 = list1.next;
            }else{
                head.next = list2;
                head = list2;
                list2 = list2.next;
            }
        }
        if(list1 == null){
            head.next = list2;
        }else {
            head.next = list1;
        }
        return root.next;
    }

    public static void main(String[] args) {
        ListNode[] list1 = new ListNode[6];
        ListNode[] list2 = new ListNode[6];
        for(int i = 0;i < 6;i++){
            list1[i] = new ListNode(2*i+1);
            list2[i] = new ListNode(2*i);
        }
        for(int i = 0;i < 5;i++){
            list1[i].next = list1[i+1];
            list2[i].next = list2[i+1];
        }
        list1[5].next = null;
        list1[5].val = 11;
        list2[5].next = null;
        list2[5].val = 12;

        Solution_16 solution16 = new Solution_16();
        ListNode head = solution16.Merge3(list1[0],list2[0]);

        while (head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}
