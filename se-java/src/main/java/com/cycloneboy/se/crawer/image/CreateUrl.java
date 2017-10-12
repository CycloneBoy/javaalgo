package com.cycloneboy.se.crawer.image;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by CycloneBoy on 2017/10/10.
 * 构建要爬取的Url
 */
public class CreateUrl {

    //首先构建需要抓取的10页百度图片页面
    public static List<String> CreateMainUrl(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入开始链接:");
        String urlMain = scanner.nextLine();
        String urlTemp = urlMain;
        List<String> list = new ArrayList<>();

        //构建需要爬取的10页Url
        for(int i = 0 ; i< 10;i++){
            urlMain = "http://" + urlMain +"pn=" + i*60;
            list.add(urlMain);
            urlMain = urlTemp;
        }

        return list;
    }

    //创建每个要爬取图片的Url
    public static List<String> CreateImageUrl(List<String> list){
        List<String> imageList = new ArrayList<>();

        imageList = UrlFecter.urlParse(imageList,list);

        return imageList;
    }


    //首先构建需要抓取的10页百度图片页面
    public static List<String> CreateNipicMainUrl(String homeUrl,int beginPage,int endPage){
        String webUrl =null;
        List<String> imageList = new ArrayList<>();
        for(int i = beginPage;i<endPage;i++){
            try {
                webUrl =homeUrl+beginPage;
                Document document = Jsoup.connect(webUrl).get();

                Elements elements = document.select("img[src^=http://img85.nipic.com/file/]");
                System.out.println("第"+i+"页获取了" + elements.size() + "个链接");
                for(Element element : elements){
                    try{
                        String picSrcUrl = element.attr("src");
                       // System.out.println("Pic    : " + picSrcUrl);
                        String picUrl = "http://pic136.nipic.com/file/"+
                                picSrcUrl.substring(28,picSrcUrl.length() -5)+ "2"+
                                picSrcUrl.substring(picSrcUrl.length() -4,picSrcUrl.length());

                        //System.out.println("picUrl :" + picUrl);

                        if(picUrl != null && !picUrl.trim().equals("")){
                            imageList.add(picUrl);
                        }
                    }catch (Exception e){
                        continue;
                    }
                }

            }catch (Exception e){

            }
        }
        return imageList;
    }

    // 在大图的网址获取详细的图片地址
    public static String getPicUrl(String picHreUrl){
        String picUrl = null;
        if(picHreUrl != null){
            try{
                Document document = Jsoup.connect(picHreUrl).get();
                Element element = document.getElementById("J_worksImg");
                picUrl = element.attr("src");
            }catch (Exception e){
               // System.out.println(e.getMessage());
            }
        }
        return picUrl;
    }

}

