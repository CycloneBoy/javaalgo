package com.cycloneboy.se.crawer;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Java网络爬虫（二）--HttpClient设置头部信息（模拟登录）
 * http://blog.csdn.net/championhengyi/article/details/66583276
 * Created by CycloneBoy on 2017/10/10.
 */
public class LoginDemo {
    // HttpClient 代表Http客户端
    // HttpEntity 消息载体,发送或者接收消息的载体,可以通过客户端请求或者服务器响应获取实例

    public static void main(String[] args) throws IOException {

        // 创建默认的客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get请求实例
        HttpGet httpGet = new HttpGet("http://www.baidu.com");

        httpGet.setHeader("Accept","text/html,application/xhtml+xml," +
                "application/xml;q=0.9,image/webp,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
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
            // 服务器响应状态行
            System.out.println(response.getStatusLine().toString());
            if (response.getStatusLine().getStatusCode() == 200){
                // 打印所有响应头
                Header[] headers = response.getAllHeaders();
                for(Header header : headers){
                    System.out.println(header.getName() + ":" + header.getValue());
                }
            }else{
                System.out.println("访问出错！");
            }

        }finally {
            httpClient.close();
        }
    }
}
