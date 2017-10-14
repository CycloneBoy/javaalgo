package com.cycloneboy.se.crawer.utils;

import com.cycloneboy.se.crawer.entity.IPMessage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/10/14.
 */
public class IPProxy {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    //设置System系统属性
    public static void test1(){
        System.getProperties().setProperty("proxySet","true");
        System.getProperties().setProperty("http.proxyHost","183.61.236.55");
        System.getProperties().setProperty("http.proxyPort","3128");

        Connection connection = Jsoup.connect("http://city.ip138.com/ip2city.asp");
        connection.timeout(2000);
        try{
            Document document = connection.get();
            System.out.println(document);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void test2()  {
        Proxy proxy = new Proxy(Proxy.Type.HTTP,
                new InetSocketAddress("211.138.60.25",80));
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection)new URL("http://city.ip138.com/ip2city.asp")
                        .openConnection(proxy);
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.setConnectTimeout(6000);
        connection.setReadTimeout(6000);
        connection.setUseCaches(false);

        try {
            if(connection.getResponseCode() == 200){
                System.out.println("使用代理IP连接网络成功");
            }
            System.out.println(connection.getHeaderField(0));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 首先我使用本机IP在xici（西刺）代理网站上的高匿IP代理区抓取了第一页的代理IP放入了一个数组之中;
     然后我使用数组中的IP对要访问的页面进行轮番调用，每访问一个页面就换一个IP;
     我将得到的IP按链接速度的快慢进行排序，选需速度最快的前100个;
     我对得到的IP进行测试，如果不能使用就在容器中删除;
     将最终的IP写入数据库中。
     */

    public void getIP(){
        List<String> Urls = new ArrayList<>();
        List<IPMessage> list = new ArrayList<>();
        List<IPMessage> ipMessages = new ArrayList<>();

        String url = "http://www.xicidaili.com/nn/1";
        String IPAddress;
        String IPPort;

        int k , j;


    }
}
