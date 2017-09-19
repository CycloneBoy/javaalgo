package com.cycloneboy.se.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * Created by CycloneBoy on 2017/9/19.
 */
public class PipeIO {
    public static void main(String[] args) throws IOException {

        final PipedOutputStream pos = new PipedOutputStream();
        final PipedInputStream pis = new PipedInputStream(pos);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pos.write("hello!".getBytes());
                }catch (IOException ie){
                    System.out.println(ie.toString());
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                  int c= 0;
                  while((c = pis.read()) != -1){
                      System.out.print((char)c);
                  }
                }catch (IOException ie){
                    System.out.println(ie.toString());
                }
            }
        });

        t1.start();
        t2.start();

    }
}
