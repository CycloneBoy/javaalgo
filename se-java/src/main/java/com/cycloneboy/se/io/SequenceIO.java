package com.cycloneboy.se.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by CycloneBoy on 2017/9/19.
 */
public class SequenceIO {
    public static void main(String[] args) throws IOException {

        SequenceInputStream sis;

        FileInputStream f1 = new FileInputStream("d:\\temp1.txt");
        FileInputStream f2 = new FileInputStream("d:\\temp2.txt");

        sis = new SequenceInputStream(f1,f2);

        int bytecount = 0;
        int c;
        while((c =sis.read()) != -1){
            System.out.print((char)c);
        }
        System.out.println();
        f1.close();
        f2.close();
        sis.close();

        Vector<FileInputStream> list = new Vector<>();
        list.add(new FileInputStream("d:\\temp1.txt"));
        list.add(new FileInputStream("d:\\temp2.txt"));
        Enumeration<FileInputStream> e= Collections.enumeration(list);
        sis = new SequenceInputStream(e);
        while((c = sis.read()) != -1){
            System.out.print((char)c);
        }
        sis.close();
    }
}
