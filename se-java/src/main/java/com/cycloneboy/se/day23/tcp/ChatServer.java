package com.cycloneboy.se.day23.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 	聊天的服务端
 * Created by CycloneBoy on 2017/8/23.
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {

        //建立tcp的客户端服务
        ServerSocket serverSocket = new ServerSocket(9090);
        //接受客户端的连接，产生一个Socket
        Socket socket = serverSocket.accept();
        //获取socket的输出流对象。
        OutputStreamWriter socketOut = new OutputStreamWriter(socket.getOutputStream());
        //获取socket的输入流对象
        BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //获取键盘的输入流对象，读取数据
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));

        String line = null;
        //不断的读取键盘录入的数据，然后把数据写出
        while ((line = socketReader.readLine()) != null){
            System.out.println("服务端接收到的数据: " + line);

            System.out.println("请输入回送给客户端的数据:");
            line = keyReader.readLine();

            socketOut.write(line + "\r\n");
            //刷新
            socketOut.flush();

        }
        //关闭资源
        socket.close();
    }
}
