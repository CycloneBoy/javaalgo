package com.cycloneboy.se.day23.tcp;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * Created by CycloneBoy on 2017/8/23.
 */
public class ImageServer extends Thread{

    Socket socket;

    static HashSet<String> ips = new HashSet<String>();

    public ImageServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            //获取到socket输出流对象
            OutputStream outputStream = socket.getOutputStream();
            //获取图片的输入流对象
            FileInputStream fileInputStream = new FileInputStream( "C:\\Users\\53463\\Pictures\\Camera " +
                    "Roll\\sunshine-wallpaper-1.jpg");
            //读取图片数据，把数据写出
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = fileInputStream.read(buf)) != -1){
                outputStream.write(buf,0,length);
            }

            String ip = socket.getInetAddress().getHostAddress();// socket.getInetAddress() 获取对方的IP地址
            if(ips.add(ip)){
                System.out.println("恭喜" + ip + "同学成功下载，当前下载的人数是："+ ips.size());
            }

            //关闭资源
            fileInputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //建立tcp的服务 ,并且要监听一个端口
        ServerSocket serverSocket = new ServerSocket(9090);
        while (true){
            //接受用户的链接。
            Socket socket = serverSocket.accept();
            new ImageServer(socket).start();
        }
    }
}
