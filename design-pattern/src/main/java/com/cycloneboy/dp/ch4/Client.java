package com.cycloneboy.dp.ch4;


/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Client {
    public static void main(String[] args) {
        // 定义一个美女
        //IPettyGirl weiWei = new PettyGirl("薇薇");
        PettyGirl weiWei = new PettyGirl("薇薇");
        AbstractSearcher searcher = new Searcher(weiWei);
        searcher.show();
    }
}
