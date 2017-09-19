package com.cycloneboy.se.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by CycloneBoy on 2017/9/19.
 */
public class ByteArrayIO {
    public static void main(String[] args) throws IOException{
        //写入字符串
        String s = "这是一个String";
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        bao.write(s.getBytes());
        System.out.println(bao.toString());
        bao.close();

        //写文件
        FileInputStream fin = new FileInputStream("d:\\temp.txt");
        bao = new ByteArrayOutputStream();
        int c = 0;

        while((c = fin.read()) != -1){
            bao.write(c);
        }
        System.out.println("长度:" + bao.size());
        System.out.println(bao.toString());
        bao.close();

        //读入
        byte[] buf = s.getBytes("utf-8");
        ByteArrayInputStream bai = new ByteArrayInputStream(buf);
        byte[] t = new byte[100];
        while((c = bai.read(t)) > -1){
            System.out.println(new String(t,0,c));
        }

        bai.close();
    }
}
