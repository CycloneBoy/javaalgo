package com.cycloneboy.se.algo;

import com.sun.org.apache.bcel.internal.generic.RET;

/**
 * 顺序表
 * Created by CycloneBoy on 2017/9/12.
 */
class DATA{
    String key;
    String name;
    int age;
}

class SLType{
    static final int MAXLEN = 100;
    DATA[] ListData = new DATA[MAXLEN+1];
    int ListLen;

    //初始化线性表
    void SLInit(SLType SL){
        SL.ListLen = 0;
    }

    //返回线性表的元素数量
    int SLLength(SLType SL){
        return (SL.ListLen);
    }

    //插入元素
    int SLInsert(SLType SL,int n,DATA data){
        int i = 0;
        if(SL.ListLen >=MAXLEN){
            System.out.println("顺序表已满，不能插入节点！");
            return 0;
        }
        if(n <1 || n > SL.ListLen){
            System.out.println("插入元素序号错误，不能插入元素！");
            return 0;
        }

        for(i = SL.ListLen;i >=n;i++){
            SL.ListData[i+1] = SL.ListData[i];
        }
        SL.ListData[n] = data;
        SL.ListLen++;
        return 1;
    }

    //向顺序表的尾部添加元素
    int SLAdd(SLType SL,DATA data){
        if(SL.ListLen >= MAXLEN){
            System.out.println("顺序表已满，不能再添加节点了！");
            return 0;
        }
        SL.ListData[++SL.ListLen] =data;
        return 1;
    }

    int SLDelete(SLType SL,int n){

        return  0;
    }
}
public class P2_1 {
}
