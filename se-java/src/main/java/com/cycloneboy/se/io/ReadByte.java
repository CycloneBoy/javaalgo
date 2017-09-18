package com.cycloneboy.se.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by CycloneBoy on 2017/9/18.
 */
public class ReadByte {

    public static void main(String[] args) throws IOException {

        InputStream is = new FileInputStream("c:\\oem8.log");
        System.out.println("文件长度："+(is.available()));
        int b = is.read();
        System.out.println(b);

        while(b != -1){
            b = is.read();
            System.out.print(" " + b);
        }
        is.close();

    }
}
