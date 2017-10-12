package com.cycloneboy.se.crawer.image;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/10/10.
 * 测试数据:image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=bird&
 */
public class major {
    public static void main(String[] args) {
        Date startTime = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd : hh:mm:ss");
        System.out.println("开始时间:" +sf.format(startTime));

        String homeUrl = "http://www.nipic.com/photo/lvyou/guonei/index.html?page=";
        testNiPicImage(homeUrl);

        Date endTime = new Date();
        System.out.println("结束时间:" +sf.format(endTime));

    }

    public static void testNiPicImage(String homeUrl){
        int sum =0;
        List<String> urls = new ArrayList<>();

        urls = CreateUrl.CreateNipicMainUrl(homeUrl, 1,1000);

        System.out.println("带抓取的链接总数:" + urls.size());

        ImageFile.createDir("D:\\upload\\test1");

        int average = urls.size()/10;
        for(int i = 0; i< 10;i++){
            int begin = sum;
            sum += average;
            int last = sum;

            Thread image = null;
            if(i < 9){
                image = new Thread(new ImageFile(begin,last,urls));
            }else {
                image = new Thread(new ImageFile(begin,urls.size(),urls));
            }

            image.start();
        }
    }

    public static void testBaiduImage(){
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
