package com.cycloneboy.se.io;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        int total = in.nextInt();
        int t = in.nextInt();
        int [] array = new int[total];
        int j = 0;
        while(in.hasNext()  && (j < total)){
            array[j++] = in.nextInt();
        }





    }



}
