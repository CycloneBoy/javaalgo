package com.cycloneboy.interview.jzoffer;

/**
 * 题目描述
 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 * Created by CycloneBoy on 2017/8/24.
 */
import java.util.Stack;

public class Solution_20 {
    private Stack<Integer> mData = new Stack<Integer>();
    private Stack<Integer> mMinData = new Stack<Integer>();

    public void push(int node) {
        mData.push(node);
        if(mMinData.size() == 0 || node < mMinData.peek()){
            mMinData.push(node);
        }else {
            mMinData.push(mMinData.peek());
        }
    }

    public void pop() {
        if(mData.size() <= 0 || mMinData.size() <= 0){
            return;
        }
        mData.pop();
        mMinData.pop();
    }

    public int top() {
        return mData.peek();
    }

    public int min() {
        if(mData.size() <= 0 || mMinData.size() <= 0){
            throw  new RuntimeException("栈已经为空了！");
        }
        return  mMinData.peek();

    }
}
