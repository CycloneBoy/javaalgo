package com.cycloneboy.se.day23.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class ChatSender extends Thread {

    @Override
    public void run() {
        try {
            //建立UDP的服务
            DatagramSocket  socket = new DatagramSocket();
            //准备数据,把数据封装到数据包中发送
            BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            DatagramPacket packet = null;
            while ((line = keyReader.readLine()) != null){
                //发送数据
                packet = new DatagramPacket(line.getBytes(),line.getBytes().length, InetAddress.getLocalHost(),9090);
                socket.send(packet);
            }
            //关闭资源
            socket.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
