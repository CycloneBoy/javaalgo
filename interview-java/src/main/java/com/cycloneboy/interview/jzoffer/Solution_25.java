package com.cycloneboy.interview.jzoffer;


/**
 * 题目描述
 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的head。
 （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
 *
 * Created by CycloneBoy on 2017/8/24.
 *
 *
 方法一：map关联 
    首先遍历一遍原链表，创建新链表（赋值label和next），用map关联对应结点；
    再遍历一遍，更新新链表的random指针。（注意map中应有NULL
    ----> NULL的映射）

 方法二：next指针关联
     创建新链表的时候，用原结点的next指针指向对应新结点，
         新结点的next指针指向下一个原结点，以此类推，形成之字形关联。
         然后，就可以先更新新链表的random指针，再解除next关联，更新next指针。
      这种方法不需要map来辅助，不管查找next还是random指针都是O(1)的，效率很高。
 */
public class Solution_25 {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead == null){
            return  null;
        }
        //复制next 如原来是A->B->C 变成A->A'->B->B'->C->C'
        RandomListNode pCur = pHead;
        while(pCur != null){
            RandomListNode node = new RandomListNode(pCur.label);
            node.next = pCur.next;
            pCur.next = node;
            pCur = node.next;
        }
        pCur = pHead;
        //复制random pCur是原来链表的结点 pCur.next是复制pCur的结点
        while(pCur != null){
            if(pCur.random != null){
                pCur.next.random = pCur.random.next;
            }
            pCur = pCur.next.next;
        }
        RandomListNode head = pHead.next;
        RandomListNode cur = head;
        pCur = pHead;
        //拆分链表
        while (pCur != null){
            pCur.next = pCur.next.next;
            if(cur.next != null){
                cur.next = cur.next.next;
            }
            cur = cur.next;
            pCur = pCur.next;
        }
        return head;
    }

    /**
     * 递归思想：把大问题转化若干子问题
     此题转化为一个头结点和除去头结点剩余部分，剩余部分操作和原问题一致
     */
    public RandomListNode Clone1(RandomListNode pHead){
        if(pHead == null){
            return null;
        }
        //开辟一个新的结点
        RandomListNode node = new RandomListNode(pHead.label);
        node.next = pHead.next;
        node.random = pHead.random;

        //递归其他结点
        node.next = Clone1(pHead.next);
        return node;
    }
}
