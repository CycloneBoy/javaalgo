package com.cycloneboy.interview.java;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by CycloneBoy on 2017/8/10.
 * 说明:   写一个Java应用程序，从键盘输入两个整数，然后输出它们的平方值和立方值
 */
class InputData{                        //定义从键盘输入数据的类
    static private  String s ="";

    static public void input(){
        BufferedReader bu = new BufferedReader(new InputStreamReader(System.in));
        try {
            s = bu.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    static public int getInt(){         //静态方法可直接用类名调用
        input();
        return  Integer.parseInt(s);    //将字符组成的字符串s转换为整形数据后返回
    }
}

class  Result{
    void print(int d){
        System.out.println(d + "的平方：" + d* d);
        System.out.println(d+ "的立方：" + d*d*d);
    }
}

public class Test6_3_4 {
    public static void main(String[] args) {
        Result result = new Result();
        System.out.println("请输入一个整数:");
        int a = InputData.getInt();
        result.print(a);
    }
}
