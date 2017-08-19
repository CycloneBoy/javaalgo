package com.cycloneboy.se.day13;


/**
 * Created by CycloneBoy on 2017/8/18.
 */
class TalkThread extends Thread{

    @Override
    public void run() {
        System.out.println("hi,你好！开视频呗....");
    }
}
class VideoThread extends Thread{
    @Override
    public void run() {
        while (true){
            System.out.println("视频视频。。。。。");
        }
    }
}
public class Demo2 {
    public static void main(String[] args) {
        TalkThread talkThread = new TalkThread();
        talkThread.start();
        VideoThread videoThread = new VideoThread();
        videoThread.start();
    }
}
