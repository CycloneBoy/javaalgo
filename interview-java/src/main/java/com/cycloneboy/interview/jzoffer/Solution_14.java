package com.cycloneboy.interview.jzoffer;

/**
 * 题目描述
 输入一个链表，输出该链表中倒数第k个结点。
 * Created by CycloneBoy on 2017/8/23.
 */
public class Solution_14 {

    /**
     * 最佳代码：Java代码，通过校验。代码思路如下：两个指针，
     * 先让第一个指针和第二个指针都指向头结点，然后再让第一个指正走(k-1)步，
     * 到达第k个节点。然后两个指针同时往后移动，当第一个结点到达末尾的时候，
     * 第二个结点所在位置就是倒数第k个节点了。。
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head,int k) {
       if(head == null || k <=0){ //参数判断
           return  null;
       }

        ListNode pAhead = head;
        ListNode pBehind = head;

        for (int i = 0; i< k-1;i++){
            if(pAhead.next != null){
                pAhead = pAhead.next;
            }else {
                return  null;    //参数判断
            }
        }

        while (pAhead.next != null){
            pAhead = pAhead.next;
            pBehind = pBehind.next;
        }
        return pBehind;
    }

    public static void main(String[] args) {
        ListNode[] head = new ListNode[6];
        for(int i = 0;i < 6;i++){
            head[i] = new ListNode(i+1);
        }
        for(int i = 0;i < 5;i++){
            head[i].next = head[i+1];
        }
        head[5].next = null;
        head[5].val = 6;

//        ListNode pNode = head[0];
//        while (pNode.next != null){
//            System.out.println(pNode.val);
//            pNode = pNode.next;
//        }
        Solution_14 solution14 = new Solution_14();
       System.out.println(solution14.FindKthToTail(head[0],2).val);
    }
}
