package com.cycloneboy.se.crawer;

import java.net.URL;

/**
 * Created by CycloneBoy on 2017/10/13.
 */
public class BloomFilterTest {

    private final String[] URLS = {
            "http://www.csdn.net/",
            "http://www.baidu.com/",
            "http://www.google.com.hk",
            "http://www.cnblogs.com/",
            "http://www.zhihu.com/",
            "https://www.shiyanlou.com/",
            "http://www.google.com.hk",
            "https://www.shiyanlou.com/",
            "http://www.csdn.net/"
    };

    private void testBloomFilter(){
        SimpleBloomFilter filter = new SimpleBloomFilter();
        for(int i = 0; i < URLS.length;i++){
            if(filter.contains(URLS[i])){
                System.out.println("contain: " + URLS[i]);
                continue;
            }

            filter.add(URLS[i]);
        }
    }

    public static void main(String[] args) {
        BloomFilterTest test = new BloomFilterTest();
        test.testBloomFilter();
    }
}
