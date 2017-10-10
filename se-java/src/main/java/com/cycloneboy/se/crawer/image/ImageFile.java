package com.cycloneboy.se.crawer.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by CycloneBoy on 2017/10/10.
 */
public class ImageFile implements Runnable{
    InputStream inputStream = null;
    FileOutputStream outputStream = null;
    static String dir = null;
    int begin =0;
    int last = 0;
    List<String> imageUrls = new ArrayList<>();

    //设置线程需要的参数
    public ImageFile(int begin, int last, List<String> imageUrls) {
        this.begin = begin;
        this.last = last;
        this.imageUrls = imageUrls;
    }

    //创建文件夹
    public static void createDir(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入文件夹路径：");
        dir = scanner.nextLine();

        File file = new File(dir);
        if(file.exists()){
            System.out.println("dir is exists");
        }else {
            file.mkdir();
        }
    }

    @Override
    public void run() {
        for(int i = begin;i< last;i++){
            System.out.println(" 爬取的链接：" + imageUrls.get(i));

            try{
                URL url = new URL(imageUrls.get(i));
                URLConnection conn = url.openConnection();
                conn.setConnectTimeout(1000);
                conn.setReadTimeout(5000);
                conn.connect();
                inputStream = conn.getInputStream();
            }catch (Exception e){
                System.out.println("爬取链接出错一个："+imageUrls.get(i));
                continue;
            }

            System.out.println("正确爬取链接:"+ imageUrls.get(i));

            String filename = dir +'/' + imageUrls.get(i).substring(
                    imageUrls.get(i).lastIndexOf('/') +1);
            File file1 = new File(filename);
            try{
                if(!file1.exists()){
                    file1.createNewFile();

                    outputStream = new FileOutputStream(new File(filename));
                    byte[] buf = new byte[10240];
                    int length = 0;
                    while((length = inputStream.read(buf,0,buf.length)) != -1){
                        outputStream.write(buf,0,length);
                    }
                }
            }catch (Exception e){
                System.out.println("保存文件出错，继续下一个："+e.getMessage());
                continue;
            }

            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
