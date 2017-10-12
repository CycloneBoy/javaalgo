package com.cycloneboy.se.crawer.utils;

import org.apache.http.Header;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * Created by CycloneBoy on 2017/10/11.
 */
public class DownLoadFile {

    public String getFileNameByUrl(String url,String contentType){
        url  = url.substring(7);//移除http:
        //text/html类型
        if(contentType.indexOf("html") != -1){
            url = url.replaceAll("[\\?/:*|<>\"]","_") + ".html";
            return url;
        }else {
            return url.replaceAll("[\\?/:*|<>\"]","_") + "." +
                    contentType.substring(contentType.lastIndexOf("/") + 1);
        }
    }

    private void saveToLocal(InputStream in,String filepath){
        try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(filepath)));
            byte[] buf = new byte[1024];
            int length = 0;
            while((length = in.read(buf,0,buf.length)) != -1){
                out.write(buf,0,length);
            }
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //下载URL指向的网页
    public String downloadFile(String url){
        String filepath = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        try {
            // 客户端执行get请求 返回响应
            CloseableHttpResponse response = httpClient.execute(httpGet);

            filepath = "D:\\upload" + getFileNameByUrl(url,
                    response.getHeaders("Content-Type").toString());
            saveToLocal(response.getEntity().getContent(),filepath);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return filepath;
    }
}
