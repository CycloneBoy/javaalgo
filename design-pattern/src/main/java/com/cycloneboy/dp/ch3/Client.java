package com.cycloneboy.dp.ch3;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Client {
    public static void main(String[] args) {
        IDriver zhangsan = new Driver();
        //ICar benz = new Benz();
        ICar bmw = new BMW();
        // 张三开奔驰车
        //zhangsan.driver(benz);
        zhangsan.driver(bmw);
    }
}
