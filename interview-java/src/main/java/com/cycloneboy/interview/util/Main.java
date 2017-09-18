package com.cycloneboy.interview.util;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int total = Integer.parseInt(in.nextLine());
        int i = 0;
        int[][] course = new int[5][10];

        Map<String,List<String>> same = new HashMap<>();
        while(i < total){
            String temp = in.nextLine();
            String[] str = temp.split(" ");
            int x = str[0].charAt(0) - '0';
            int y = str[0].charAt(1) - '0';
            if(course[x][y] != 1){
                course[x][y] = 1;
            }else{
                List<String> list = new LinkedList<>();
                list.add(str[1]);
                same.put(str[0],list);
            }
        }

    }



}
