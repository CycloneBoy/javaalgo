package com.cycloneboy.se.crawer.IPProxy;

import com.cycloneboy.se.crawer.entity.IPMessage;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/10/14.
 */
public class IPUtils {

    public static void IPIsable(List<IPMessage> ipMessages){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        for(int i = 0; i < ipMessages.size();i++){
            String ip = ipMessages.get(i).getIPAddress();
            String port = ipMessages.get(i).getIPPort();

            HttpHost proxy = new HttpHost(ip,Integer.parseInt(port));
            RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(5000)
                    .setSocketTimeout(5000).build();
            HttpGet httpGet = new HttpGet("https://www.baidu.com");
            httpGet.setConfig(config);

            httpGet.setHeader("Accept","text/html,application/xhtml+xml," +
                    "application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
            httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
            httpGet.setHeader("Connection", "keep-alive");
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)"+
                    "Chrome/61.0.3163.100 Safari/537.36");

            try {
                response = httpClient.execute(httpGet);
            }catch (Exception e){
                System.out.println("不可用代理已经被删除：" + ipMessages.get(i).getIPAddress()
                    + ":" + ipMessages.get(i).getIPPort());
                ipMessages.remove(ipMessages.get(i));
                i--;
            }
        }

        try {
            if(httpClient != null){
                httpClient.close();
            }
            if(response != null) {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //对得到的IP进行筛选，将IP速度在两秒以内的并且类型是https的留下，其余删除
    public static List<IPMessage> Filter(List<IPMessage> ipMessages){
        List<IPMessage> newIpMessages = new ArrayList<>();

        for(int i = 0; i < ipMessages.size();i++){
           String ipType = ipMessages.get(i).getIPType();
           String ipSpeed = ipMessages.get(i).getIPSpeed();
           ipSpeed = ipSpeed.substring(0,ipSpeed.indexOf("秒"));

           double Speed = Double.parseDouble(ipSpeed);
           if(ipType.equals("HTTPS") && Speed <= 2.0){
               newIpMessages.add(ipMessages.get(i));
           }
        }

        return newIpMessages;
    }
}
