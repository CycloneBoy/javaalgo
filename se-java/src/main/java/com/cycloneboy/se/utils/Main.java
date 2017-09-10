package com.cycloneboy.se.utils;

import java.util.Scanner;

/**
 * Created by CycloneBoy on 2017/9/10.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int num = in.nextInt();

        if(num < 0 || num > 9){
            System.out.println("error");
        }
        if(num == 6 || num == 7){
            System.out.println("none");
        }else{
            String[] test = input.split(",");

            for(int i = 0; i < test.length;i++){

                if( testStr(test[i]) == num){
                    System.out.print( test[i] +" ");
                }else if( testStr(test[i]) == 0){
                    System.out.println("none");
                }
            }
        }


    }

    public static int testStr(String str){

        int result = 0;
        for(int j =0;j< 5;j++){
            char c = str.charAt(str.length() -1 -j);
            if( c<'0' || c > '9'){
                continue;
            }else{
                switch (c){
                    case '1':
                    case '9':{
                        result = 1;break;
                    }
                    case '2':
                    case '8':{
                        result = 2;break;
                    }
                    case '3':
                    case '7':{
                        result = 3;break;
                    }
                    case '4':
                    case '6':{
                        result = 4;break;
                    }
                    case '5':
                    case '0':{
                        result = 5;break;
                    }

                }
            }
        }

        return  result;
    }

}
