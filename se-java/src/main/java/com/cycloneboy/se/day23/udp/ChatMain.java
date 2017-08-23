package com.cycloneboy.se.day23.udp;

/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class ChatMain {
    public static void main(String[] args) {
        ChatReceive chatReceive = new ChatReceive();
        chatReceive.start();

        ChatSender chatSender = new ChatSender();
        chatSender.start();

    }
}
