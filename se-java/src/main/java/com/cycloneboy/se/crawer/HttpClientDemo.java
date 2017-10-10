package com.cycloneboy.se.crawer;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Java网络爬虫（一）--HttpClient的使用
 * http://blog.csdn.net/championhengyi/article/details/64618454?locationNum=3&fps=1
 * Created by CycloneBoy on 2017/10/8.
 */
public class HttpClientDemo {
    // HttpClient 代表Http客户端
    // HttpEntity 消息载体,发送或者接收消息的载体,可以通过客户端请求或者服务器响应获取实例

    public static void main(String[] args) throws IOException {

        // 创建默认的客户端实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建get请求实例
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        System.out.println("executing request " + httpGet.getURI());

        try {
            // 客户端执行get请求 返回响应
            CloseableHttpResponse response = httpClient.execute(httpGet);
            // 服务器响应状态行
            System.out.println(response.getStatusLine().toString());

            Header[] headers = response.getAllHeaders();
            System.out.println(response.getHeaders("Content-Type").toString());

            // 打印所有响应头
            for(Header header : headers){
                System.out.println(header.getName() + ":" + header.getValue());
            }
        }finally {
            httpClient.close();
        }
    }
}
