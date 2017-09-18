package com.cycloneboy.se.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by CycloneBoy on 2017/9/18.
 */
public class WriterAppend {

    public static void main(String[] args) throws IOException{

        BufferedWriter bw = null;
        String s = "hello";

        bw = new BufferedWriter(new FileWriter("D:\\temp.txt"));
        bw.write(s);
        bw.newLine();
        bw.append(s + " world");
        bw.close();

    }
}
