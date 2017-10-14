package com.cycloneboy.se.crawer.IPProxy;

import com.cycloneboy.se.crawer.entity.IPMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/10/14.
 */
public class URLFecter {

    //使用代理进行爬取
    public static boolean urlParse(String url ,String ip,String port,
                                           List<IPMessage> ipMessages){

        String html = MyHttpResponse.getHtml(url,ip,port);

        if(html != null){
            Document document = Jsoup.parse(html);

            Elements trs = document.select("table[id=ip_list]").select("tbody").select("tr");

            for(int i = 1;i < trs.size();i++){
                IPMessage ipMessage = new IPMessage();
                String ipAddress = trs.get(i).select("td").get(1).text();
                String ipPort = trs.get(i).select("td").get(2).text();
                String ipType = trs.get(i).select("td").get(5).text();
                String ipSpeed = trs.get(i).select("td").get(6).select("div[class=bar")
                        .attr("title");

                ipMessage.setIPAddress(ipAddress);
                ipMessage.setIPPort(ipPort);
                ipMessage.setIPType(ipType);
                ipMessage.setIPSpeed(ipSpeed);

                ipMessages.add(ipMessage);
            }

            return  true;
        }else {
            System.out.println(ip + ":" + port + " 代理不可用");
            return  false;
        }


    }

    public static List<IPMessage> urlParse(List<IPMessage> ipMessages){
        String url = "http://www.xicidaili.com/nn/1";
        String html = MyHttpResponse.getHtml(url);

        Document document = Jsoup.parse(html);

        Elements trs = document.select("table[id=ip_list]").select("tbody").select("tr");

        for(int i = 1;i < trs.size();i++){
            IPMessage ipMessage = new IPMessage();
            String ipAddress = trs.get(i).select("td").get(1).text();
            String ipPort = trs.get(i).select("td").get(2).text();
            String ipType = trs.get(i).select("td").get(5).text();
            String ipSpeed = trs.get(i).select("td").get(6).select("div[class=bar]")
                    .attr("title");

            ipMessage.setIPAddress(ipAddress);
            ipMessage.setIPPort(ipPort);
            ipMessage.setIPType(ipType);
            ipMessage.setIPSpeed(ipSpeed);

            ipMessages.add(ipMessage);
        }

        return ipMessages;
    }

    public static void main(String[] args) {
        List<IPMessage> ipMessageList = new ArrayList<>();
        ipMessageList = URLFecter.urlParse(ipMessageList);

        System.out.println("爬取了多少代理IP:" + ipMessageList.size());
        for(IPMessage ip : ipMessageList){
            System.out.println(ip);
        }

        System.out.println("对IP进行过滤：");

        IPUtils.IPIsable(ipMessageList);
        System.out.println("过滤完不能连通的IP后剩下:" +ipMessageList.size());

        ipMessageList = IPUtils.Filter(ipMessageList);
        System.out.println("过滤完Https 连接时间小于两秒的IP:" +ipMessageList.size());

        for(IPMessage ip : ipMessageList){
            System.out.println(ip + " " + ip.getIPType() + " " + ip.getIPSpeed() );
        }
    }
}

/**
 * 过滤完Https 连接时间小于两秒的IP:6
 112.248.228.13:8888 HTTPS 1.996秒秒
 27.151.11.211:80 HTTPS 0.186秒秒
 123.131.199.254:8118 HTTPS 0.26秒秒
 222.141.12.21:8118 HTTPS 0.09秒秒
 120.78.15.63:80 HTTPS 0.168秒秒
 114.99.8.132:6890 HTTPS 0.57秒秒

 119.177.75.141:9999 HTTPS 0.084秒秒
 120.76.55.49:8088 HTTPS 0.225秒秒
 27.151.11.211:80 HTTPS 0.186秒秒
 112.248.228.13:8888 HTTPS 1.996秒秒
 27.197.210.36:9999 HTTPS 0.091秒秒
 120.78.15.63:80 HTTPS 0.168秒秒
 123.52.84.20:8118 HTTPS 0.304秒秒
 60.23.44.146:8118 HTTPS 0.14秒秒

 */
