package com.cycloneboy.se.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by CycloneBoy on 2017/9/19.
 */
public class FileStreamIO {

    public static void main(String[] args) throws IOException{
        FileInputStream fis = new FileInputStream("D:\\java\\idea\\javaalgo\\se-java\\src\\main\\java\\com\\cycloneboy\\se\\io\\FileStreamIO.java");
        FileOutputStream fos = new FileOutputStream("FileStreamIO(1).java");
        System.out.println("文件长度:" + fis.available());

        byte[] b =   new byte[100];
        int count = 0;
        while((count = fis.read(b,0,100)) != -1){
            fos.write(b,0,count);
        }

        fis.close();
        fos.flush();
        fos.close();
    }
}
