package com.cycloneboy.dp.ch11.two;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class Client2 {
    public static void main(String[] args) {
        Director director = new Director();
        //3辆A类型的宝马
        for(int i=0;i < 3;i++){
            director.getABenzModel().run();
        }
        System.out.println();

        //4辆B类型的宝马
        for(int i=0;i < 10;i++){
            director.getBBenzModel().run();
        }
        System.out.println();
        //2辆C类型的奔驰
        for(int i=0;i < 2;i++){
            director.getCBMWModel().run();
        }
        System.out.println();
        //6辆D类型的奔驰
        for(int i=0;i < 10;i++){
            director.getDBMWModel().run();
        }
    }
}
