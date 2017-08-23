package com.cycloneboy.se.day23.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 在java中网络通讯业称作为Socket(插座)通讯，要求通讯 的两台器都必须要安装Socket。

 不同的协议就有不同 的插座（Socket）

 UDP通讯协议的特点：
 1. 将数据极封装为数据包，面向无连接。
 2. 每个数据包大小限制在64K中
 3.因为无连接，所以不可靠
 4. 因为不需要建立连接，所以速度快
 5.udp 通讯是不分服务端与客户端的，只分发送端与接收端。


 比如： 物管的对讲机, 飞Q聊天、 游戏...

 udp协议下的Socket:

 DatagramSocket(udp插座服务)
 DatagramPacket(数据包类)
 DatagramPacket(buf, length, address, port)
 buf: 发送的数据内容
 length : 发送数据内容的大小。
 address : 发送的目的IP地址对象
 port : 端口号。

 发送端的使用步骤：
 1. 建立udp的服务。
 2. 准备数据，把数据封装到数据包中发送。 发送端的数据包要带上ip地址与端口号。
 3. 调用udp的服务，发送数据。
 4. 关闭资源。

 * Created by CycloneBoy on 2017/8/22.
 */
public class Demo2Sender {
    public static void main(String[] args) throws IOException {
        //建立udp的服务
        DatagramSocket datagramSocket = new DatagramSocket();
        //准备数据，把数据封装到数据包中。
        String data = "这是我的第一个udp的例子...";

        //创建了一个数据包
        DatagramPacket packet = new DatagramPacket(data.getBytes(),data.getBytes().length,
                InetAddress.getLocalHost(),9090);
        //调用udp的服务发送数据包
        datagramSocket.send(packet);
        //关闭资源 ---实际上就是释放占用的端口号
        datagramSocket.close();
    }
}
