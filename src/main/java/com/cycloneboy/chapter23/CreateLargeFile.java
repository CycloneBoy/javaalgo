package com.cycloneboy.chapter23;

import java.io.*;
import java.util.Random;

/**
 * Created by CycloneBoy on 2017/7/3.
 */
public class CreateLargeFile {
    public static void main(String[] args) throws  Exception{
        DataOutputStream output = new DataOutputStream(
                new BufferedOutputStream(
                new FileOutputStream("largedata.dat")));

        for(int i =0 ;i < 800004;i++){
            output.write((int)(Math.random() * 1000000));
        }
        output.close();

        // Display first 100 numbers
        DataInputStream input = new DataInputStream(
                new BufferedInputStream(
                new FileInputStream("largedata.dat")));

        for(int i = 0 ;i < 100 ;i++){
            if (i%10 == 0) System.out.println();
            System.out.print(input.readInt() + " ");

        }
    }
}
