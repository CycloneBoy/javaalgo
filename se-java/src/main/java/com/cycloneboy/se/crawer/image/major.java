package com.cycloneboy.se.crawer.image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/10/10.
 * 测试数据:image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=bird&
 */
public class major {
    public static void main(String[] args) {
        int sum = 0;
        List<String> urlMains = new ArrayList<>();
        List<String> imageUrls = new ArrayList<>();

        urlMains = CreateUrl.CreateMainUrl();
        System.out.println("图片的链接总数urlMains："+ urlMains.size());
        for(String urlMain: urlMains){
            System.out.println(urlMain);
        }

        imageUrls = CreateUrl.CreateImageUrl(urlMains);
        System.out.println("图片的链接总数imageUrls："+ imageUrls.size());
        for(String imageUrl: imageUrls){
            System.out.println(imageUrl);
        }

        ImageFile.createDir();

        int average = imageUrls.size()/10;
        for(int i = 0; i< 10;i++){
            int begin = sum;
            sum += average;
            int last = sum;

            Thread image = null;
            if(i < 9){
                image = new Thread(new ImageFile(begin,last,imageUrls));
            }else {
                image = new Thread(new ImageFile(begin,imageUrls.size(),imageUrls));
            }

            image.start();
        }
    }
}
