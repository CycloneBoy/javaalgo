package com.cycloneboy.se.day23.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 2.  实现登陆与注册 功能。
 客户端与服务端连接的时候，就要提示客户端请选择功能。

 客户端注册的时候，用户名与密码都是发送给服务端 的，服务端需要把数据保存到服务端的文件上。

 登陆： 登陆的时候客户端输入用户名与密码发送给服务端，服务端需要校验，返回结果给客户端。
 * Created by CycloneBoy on 2017/8/23.
 */
public class LoginClient {

    public static void main(String[] args) throws IOException {
        //建立tcp的客户端服务
        Socket socket = new Socket(InetAddress.getLocalHost(),9090);
        //获取socket的输出流对象。
        OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
        //获取socket的输入流对象
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //获取键盘的输入流对象，读取数据
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));

        //不断的读取键盘录入的数据，然后把数据写出
        System.out.println("输入内容：");
        while (true){
            System.out.println("请选择功能: A(登录) B(注册)");
            String option = keyReader.readLine();
            if("a".equalsIgnoreCase(option)){
                getInfo(socketOut,keyReader,option);
                //读取服务器反馈的信息
                String line = socketReader.readLine();
                System.out.println(line);
            }else if("b".equalsIgnoreCase(option)){
                getInfo(socketOut,keyReader,option);
                //读取服务器反馈的信息
                String line = socketReader.readLine();
                System.out.println(line);
            }
        }

    }

    public static void getInfo(OutputStreamWriter socketOut,
                               BufferedReader keyReader,String option) throws IOException {
        System.out.println("请输入用户名：");
        String userName = keyReader.readLine();
        System.out.println("请输入密码:");
        String password = keyReader.readLine();
        String info = option + " " + userName + " " + password + "\r\n";
        socketOut.write(info);
        socketOut.flush();
    }
}
