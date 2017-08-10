package com.cycloneboy.interview.java;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by CycloneBoy on 2017/8/10.
 */
public class Test6_3 {
    public static void main(String[] args) {
        try {
            FileOutputStream out = new FileOutputStream("FileName.txt");
            out.write("字符串写入文件".getBytes());
            out.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }
}
