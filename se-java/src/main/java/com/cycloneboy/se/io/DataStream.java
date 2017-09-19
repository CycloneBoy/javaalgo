package com.cycloneboy.se.io;

import java.io.*;

/**
 * Created by CycloneBoy on 2017/9/19.
 */
public class DataStream {

    public static void main(String[] args) throws IOException{

        boolean b = true;
        short s = 2;
        int i = 20;
        char c = '中';
        float f = 2.1f;
        String str = "这个一个字符串";
        String fileName = "temp.txt";

        DataInputStream dis = null;
        DataOutputStream dos = null;

        //output
        dos = new DataOutputStream(new FileOutputStream(fileName));
        dos.writeBoolean(b);
        dos.writeShort(s);
        dos.writeInt(i);
        dos.writeChar(c);
        dos.writeFloat(f);
        dos.writeUTF(str);
        dos.flush();
        dos.close();

        //input
        dis = new DataInputStream(new FileInputStream(fileName));
        System.out.println(dis.readBoolean());
        System.out.println(dis.readShort());
        System.out.println(dis.readInt());
        System.out.println(dis.readChar());
        System.out.println(dis.readFloat());
        System.out.println(dis.readUTF());
        dis.close();
    }
}
