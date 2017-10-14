package com.cycloneboy.se.crawer.IPProxy;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by CycloneBoy on 2017/10/14.
 *  * 进行代理访问
 *
 * setConnectTimeout：设置连接超时时间，单位毫秒.
 * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒.
 * 这个属性是新加的属性，因为目前版本是可以共享连接池的.
 * setSocketTimeout：请求获取数据的超时时间，单位毫秒.如果访问一个接口，多少时间内无法返回数据，
 * 就直接放弃此次调用。
 */
public class MyHttpResponse {

    public static String getHtml(String url,String ip,String port){
        String entity = null;
        // 创建默认的客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        System.out.println("此时线程："+ Thread.currentThread().getName() + " 爬取所使用的代理为: "
            + ip + ":" + port);

        HttpHost proxy = new HttpHost(ip,Integer.parseInt(port));
        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(3000).
                setSocketTimeout(3000).build();

        // 创建get请求实例
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(config);

        httpGet.setHeader("Accept","text/html,application/xhtml+xml," +
                "application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Host", "www.xicidaili.com");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)"+
                "Chrome/61.0.3163.100 Safari/537.36");

        try {
            // 客户端执行get请求 返回响应
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if(response.getStatusLine().getStatusCode() == 200){
                entity = EntityUtils.toString(response.getEntity(),"utf-8");
            }

            response.close();
            httpClient.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    public static String getHtml(String url){
        String entity = null;
        // 创建默认的客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //设置超时处理
        RequestConfig config = RequestConfig.custom().setConnectTimeout(3000).
                setSocketTimeout(3000).build();

        // 创建get请求实例
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept","text/html,application/xhtml+xml," +
                "application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
        httpGet.setHeader("Cache-Control", "no-cache");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Host", "www.xicidaili.com");
        httpGet.setHeader("Pragma", "no-cache");
        httpGet.setHeader("Upgrade-Insecure-Requests", "1");
        httpGet.setHeader("Cookie","__cfduid=db1610e7d5ff5c51f28482f9aa3b7c6f41497698623; " +
                "BAIDUID=053337A727C36B914830A63322057BB6:FG=1; PSTM=1500857291;" +
                " BIDUPSID=2C704661466FFE5F431A08755D52BDEF; " +
                "BDUSS=lg0STk1fk8zSVVPdDFUOXhMTDB4N2p1c0VRN0VOY08tSFU4Zjg5WUZ6M2JnOVZaSVFBQUFBJCQAAAAAAAAAAAEAAAD" +
                "-jSIiNTM0NjM0Nzk5c2wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA" +
                "AAAAAAAAANv2rVnb9q1ZY; MCITY=-%3A; pgv_pvi=8567317504; " +
                "BDRCVFR[feWj1Vr5u3D]=I67x6TjHwwYf0; rsv_jmp_slow=1507614659563; " +
                "H_PS_645EC=8f09QXcXGPm7RbVNTLcc%2Bwr3uueH530ekV1AjV0wwASDiFenLDd6eQrXjsXXfFa916Eq;" +
                " BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; BD_CK_SAM=1; PSINO=2; BD_HOME=1; " +
                "H_PS_PSSID=1438_21114_20697_20930; BD_UPN=12314753; sug=0; sugstore=0; ORIGIN=2;" +
                "bdime=20100");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko)"+
                "Chrome/61.0.3163.100 Safari/537.36");

        try {
            // 客户端执行get请求 返回响应
            CloseableHttpResponse response = httpClient.execute(httpGet);

            if(response.getStatusLine().getStatusCode() == 200){
                entity = EntityUtils.toString(response.getEntity(),"utf-8");
            }

            response.close();
            httpClient.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }
}
