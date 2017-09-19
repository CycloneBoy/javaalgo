package com.cycloneboy.se.io;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static void main(String[] args)  {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        List<Integer> list = printMain(str);

        for(int i = 0; i< list.size();i++){
            System.out.print(list.get(i));
        }

    }

    public static List<Integer> printMain(String str){
        List<Integer> list = new ArrayList<>();
        List<Integer> out = new ArrayList<>();
        int k = 0;
        for( int i = 0 ; i < str.length();i++){
            if(str.charAt(i) >='0' && str.charAt(i) <= '9'){
                list.add(k++,str.charAt(i) - '0');
            }
        }

        for(int i =0; i < list.size() -1;i++){
            for(int j = i+1;j< list.size();j++){

                if(list.get(i) > list.get(j)){
                    int temp = list.get(i);
                    list.set(i,list.get(j));
                    list.set(j,temp);
                }
            }
        }

        return  list;
    }

}
