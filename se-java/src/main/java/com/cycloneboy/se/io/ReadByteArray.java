package com.cycloneboy.se.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by CycloneBoy on 2017/9/18.
 */
public class ReadByteArray {

    public static void main(String[] args) throws IOException{

        InputStream is = new FileInputStream("c:\\oem8.log");
        int n;
        n = is.available();
        System.out.println("文件长度:"+ n);

        byte[] b = new byte[100];
        while((n = is.read(b)) > -1){
            System.out.print(new String(b,0,n));
        }
        is.close();
    }
}
