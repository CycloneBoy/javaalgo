package com.cycloneboy.se.day23.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.TreeSet;

/**
 * Created by CycloneBoy on 2017/8/23.
 */
public class TomcatDemo extends Thread{

    Socket socket ;

    public TomcatDemo(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取socket的输出流对象
            OutputStream outputStream = socket.getOutputStream();
            //把数据写到浏览器上
            outputStream.write("<html><head><title>aaa</title></head><body>你好啊浏览器</body></html>".getBytes());
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //建立tcp的服务端
        ServerSocket serverSocket = new ServerSocket(9090);
        //不断的接受客户端的连接
        while (true){
            Socket socket = serverSocket.accept();
            new TomcatDemo(socket).start();
        }
    }
}
