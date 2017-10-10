package com.cycloneboy.se.crawer.image;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by CycloneBoy on 2017/10/10.
 */
public class HttpUrl {

    public static String request(String url){
        // 创建默认的客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get请求实例
        HttpGet httpGet = new HttpGet(url);
        String entity = null;

        httpGet.setHeader("Accept","text/html,application/xhtml+xml," +
                "application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding","gzip, deflate");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "max-age=0");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Cookie","__cfduid=db1610e7d5ff5c51f28482f9aa3b7c" +
                "6f41497698623; indexPageSugList=%5B%22%E6%91%84%E5%83%8F%E5%A4%B4%E5%AE%9" +
                "A%E4%BD%8D%E7%B3%BB%E7%BB%9F%22%5D; BAIDUID=053337A727C36B914830A63322057" +
                "BB6:FG=1; PSTM=1500857291; BIDUPSID=2C704661466FFE5F431A08755D52BDEF; " +
                "BDUSS=lg0STk1fk8zSVVPdDFUOXhMTDB4N2p1c0VRN0VOY08tSFU4Zjg5WUZ6M2JnOVZaS" +
                "VFBQUFBJCQAAAAAAAAAAAEAAAD-jSIiNTM0NjM0Nzk5c2wAAAAAAAAAAAAAAAAAAAAAAAAA" +
                "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANv2rVnb9q1ZY; MCITY=-%3A; " +
                "pgv_pvi=8567317504; BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; pgv_si=s5918708736;" +
                " BDRCVFR[dG2JNJb_ajR]=mk3SLVN4HKm; PSINO=2; H_PS_PSSID=1438_21114_20697" +
                "_20930; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BDRCVFR[X_XKQks0S63]" +
                "=mk3SLVN4HKm; userFrom=null; firstShowTip=1");
        httpGet.setHeader("Host","image.baidu.com");
        httpGet.setHeader("Upgrade-Insecure-Requests","1");
        httpGet.setHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)"+
                "Chrome/61.0.3163.100 Safari/537.36");

        try {
            // 客户端执行get请求 返回响应
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if (response.getStatusLine().getStatusCode() == 200){
               entity = EntityUtils.toString(response.getEntity(),"utf-8");
            }
            response.close();
            httpClient.close();

        }catch (Exception e){
            entity = null;
        }

        return entity;
    }

    public static void main(String[] args) {
        String str = request("http://image.baidu.com/");
        System.out.println(str);
    }
}
