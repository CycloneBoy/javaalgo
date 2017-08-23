package com.cycloneboy.se.day23.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 需求： 客户端与服务端一问一答聊天。


 1.如果使用BuffrerdReader的readline方法一定要加上\r\n才把数据写出。
 2.使用字符流一定要调用flush方法数据才会写出。

 * Created by CycloneBoy on 2017/8/22.
 */
public class ChatClient {
    public static void main(String[] args) throws IOException {
        //建立tcp的客户端服务
        Socket socket = new Socket(InetAddress.getLocalHost(),9090);
        //获取socket的输出流对象。
        OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
        //获取socket的输入流对象
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //获取键盘的输入流对象，读取数据
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        //不断的读取键盘录入的数据，然后把数据写出
        System.out.println("输入内容：");
        while ((line = keyReader.readLine()) != null){

            socketOut.write(line + "\r\n");
            //刷新
            socketOut.flush();

            //读取服务端回送的数据
            line = socketReader.readLine();
            System.out.println("客户端回送的数据是: " + line);
        }
        socket.close();
    }

}
