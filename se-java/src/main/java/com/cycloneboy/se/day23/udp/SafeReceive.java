package com.cycloneboy.se.day23.udp;

import java.io.IOException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class SafeReceive {
    public static void main(String[] args) throws IOException, Exception  {
        //建立udp的服务
        DatagramSocket socket = new DatagramSocket(9090);

        //建立空的数据包存储数据
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);

        //不断接受数据包
        while (true){
            socket.receive(packet);
            System.out.println(new String(buf,0,packet.getLength()));
            Thread.sleep(10);
        }
    }
}
