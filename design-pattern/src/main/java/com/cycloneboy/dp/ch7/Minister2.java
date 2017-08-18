package com.cycloneboy.dp.ch7;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public class Minister2 {
    public static void main(String[] args) {
        // 定义5个大臣
        int ministerNum = 5;
        for(int i=0;i < ministerNum;i++){
            Emperor2 emperor2 = Emperor2.getInstance();
            System.out.print("第" + (i +1) + "个大臣参拜的是：");
            emperor2.say();
        }
    }
}
