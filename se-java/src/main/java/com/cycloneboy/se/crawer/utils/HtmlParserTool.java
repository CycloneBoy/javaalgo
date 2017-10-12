package com.cycloneboy.se.crawer.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 获取一个网站上的链接
 * Created by CycloneBoy on 2017/10/11.
 */
public class HtmlParserTool {


    // http://www.nipic.com/photo/jingguan/shanshui/index.html?page= 后面的数字逐个加1
    // 直到2010
    public static String getAllUrl(String url,int start,int end){
        StringBuffer sb = null;
        try{
            sb = new StringBuffer();
            for(int i = start; i < end;i++){
                sb.append(url + i).append("\r\n");
            }
        }catch (Exception e){

        }
        return sb.toString();
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
                e.printStackTrace();
            }
        }
        return picUrl;
    }

    // 下载图片到本地磁盘
    public static void downloadPicToLocal(String picSourceUrl,String picDestPath) {
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try{
            URL url = new URL(picSourceUrl);
            bis = new BufferedInputStream(url.openStream());
            bos = new BufferedOutputStream(new FileOutputStream(picDestPath));
            byte[] buf = new byte[1024*1024];
            int len=0;
            while((len = bis.read(buf,0,buf.length)) != -1){
                bos.write(buf,0,len);
                bos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bos.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获取文件后缀名
    public static String getNameExtension(String picUrl){
        String extension = null;
        try{
            int lastIndexOf = 0;
            if(picUrl != null && !picUrl.trim().equals("")){
                if(picUrl.endsWith(".jpg")){
                    lastIndexOf = picUrl.lastIndexOf(".jpg");
                }
                if(picUrl.endsWith(".png")){
                    lastIndexOf = picUrl.lastIndexOf(".png");
                }
                extension = picUrl.substring(lastIndexOf);
            }
        }catch (Exception e){
        }
        return extension;
    }

    // 通过Jsoup获取网页的单个图片地址_通过传入网页地址和生成图片目录
    // 目标网址为http://www.nipic.com/photo/jingguan/shanshui/index.html?page=1
    public static boolean getOnePic(String webUrl,String picDestUrl){
        boolean flag = true;
        try{
            File file = new File(picDestUrl);
            if(!file.exists()){
                file.mkdir();
            }
            int count = 2220;
            for(int i = 1;i < 2010;i++){
                try {
                    String mainUrl = webUrl + i;
                    Document document = Jsoup.connect(mainUrl).get();
                    Elements elements = document.getElementsByClass("relative block works-detail hover-none " +
                            "works-img-box");
                    for(Element element1 : elements){
                        try{
                            String picHrefUrl = element1.attr("href");
                            String picUrl = getPicUrl(picHrefUrl);
                            downloadPicToLocal(picUrl,picDestUrl + "\\" + (++count) +
                                getNameExtension(picUrl));
                            System.out.println("已经抓取了" + count + "张图片 :" + picUrl);
                        }catch (Exception e){
                            continue;
                        }
                    }

                }catch (Exception e){

                }
            }
            count = 0;
        }catch (Exception e){
            flag = false;
            new RuntimeException("在服务器找不到对应的图片。");
        }
        return  flag;
    }

    public static void main(String[] args) {
        boolean result = getOnePic("http://www.nipic.com/photo/jingguan/shanshui/index.html?page=",
                "D:\\upload\\image");
        System.out.println(result);
    }
}
