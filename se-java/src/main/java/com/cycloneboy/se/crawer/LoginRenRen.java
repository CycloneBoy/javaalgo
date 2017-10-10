package com.cycloneboy.se.crawer;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * 网络爬虫中的模拟登陆获取数据（实例教学）
 * http://blog.csdn.net/qy20115549/article/details/52249232
 * Created by CycloneBoy on 2017/10/10.
 */
public class LoginRenRen {
    private static String userName = "534634799@qq.com";
    private static String password = "847563@sl";
    //人人网登陆链接
    private static String renRenLoginURL = "http://www.renren.com/PLogin.do";
    private HttpResponse response;
    private CloseableHttpClient httpClient = HttpClients.createDefault();

    private boolean login(){
        HttpPost httpPost = new HttpPost(renRenLoginURL);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("domain","renren.com"));
        nvps.add(new BasicNameValuePair("isplogin","true"));
        nvps.add(new BasicNameValuePair("submit","登陆"));
        nvps.add(new BasicNameValuePair("email",userName));
        nvps.add(new BasicNameValuePair("password",password));
        try{
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF8"));
            response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == 200){
                // 打印所有响应头
                Header[] headers = response.getAllHeaders();
                for(Header header : headers){
                    System.out.println(header.getName() + ":" + header.getValue());
                }
            }
            System.out.println("\n\n");

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            httpPost.abort();
        }
        return true;
    }

    /**
     * 获取响应头
     * @return
     */
    private String getRedirectLocation(){
        Header locationHeader = response.getFirstHeader("Location");
        if(locationHeader == null){
            return  null;
        }
        return  locationHeader.getValue();
    }

    private String getText(String redirectLocation){
        HttpGet httpGet = new HttpGet(redirectLocation);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = "";
        try{
            responseBody = httpClient.execute(httpGet,responseHandler);
        }catch (Exception e){
            e.printStackTrace();
            responseBody = null;
        }finally {
            httpGet.abort();

        }
        return responseBody;
    }

    public void printText(){
        if(login()){
            String redirectLocation = getRedirectLocation();
            if(redirectLocation != null){
                System.out.println(getText(redirectLocation));
            }
        }
    }

    public static void main(String[] args) {
        LoginRenRen renRen = new LoginRenRen();
        renRen.printText();
    }
}
