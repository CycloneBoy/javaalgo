package com.cycloneboy.se.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by CycloneBoy on 2017/9/18.
 */
public class ReadByteReset {

    public static void main(String[] args) throws IOException{

        BufferedInputStream is = new BufferedInputStream(new FileInputStream("c:\\oem8.log"));

        System.out.println("文件长度:"+is.available());
        is.mark(10);
        int b;
        while((b = is.read()) != -1){
            System.out.print( " " + b);
        }
        System.out.println("\nRead again:");

        is.reset();
        while((b = is.read()) != -1){
            System.out.print((char)b);
        }

        is.reset();
        is.skip(200);

        System.out.println("\nreset:");

        while ((b = is.read()) != -1){
            System.out.print((char)b);
        }

        is.close();
    }
}
