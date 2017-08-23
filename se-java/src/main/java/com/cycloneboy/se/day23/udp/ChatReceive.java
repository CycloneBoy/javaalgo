package com.cycloneboy.se.day23.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class ChatReceive extends Thread {

    @Override
    public void run() {
        try {
            //建立udp的服务
            DatagramSocket socket = new DatagramSocket(9090);

            //建立空的数据包存储数据
            byte[] buf = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buf,buf.length);

            boolean flag = true;
            //不断接受数据包
            while (flag){
                socket.receive(packet);
                System.out.println(packet.getAddress().getHostAddress() + "说："+ new String(buf,0,packet.getLength()));
            }
            //关闭资源
            socket.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
