package com.cycloneboy.interview.util;


import java.util.Scanner;

/**
 * Created by CycloneBoy on 2017/9/8.
 */
public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        while(in.hasNextLine()){
            int num = Integer.valueOf(in.nextLine().trim());
            System.out.println(test(num));
            //isHappyNumber(num);
           // num = in.nextInt();
        }



//       for(int i = 1; i < 100;i++){
//
//           System.out.println(i + " " + isHappyNumber(i) + " " + test(i));
//
//       }

    }

    public static int test(int num){
        int res = 0;
        if(num > 0){
            for(int i = 1 ; i <= num;i++){
                if(isHappyNumber(i)){
                    res += i;
                }
            }
        }
        return  res;
    }

    public static boolean isHappyNumber(int test){
        if(test <= 0 ){
            return  false;
        }
        if( test == 2 || test ==4){
            return  false;
        }

       int[] number = new int[100000];
       int count = 0;
       for(count  = 0; test != 0;count++){
           number[count] = test % 10;
           test = test/10;
       }

       int result = 0;
        for(int i = count-1;i >= 0 ;i--){
           // System.out.print(number[i] + " ");
            result += number[i] * number[i];

        }
        //System.out.println(result);
        if(result == 1){
            return true;
        }else if(result < 9 ){
            if(result == test * test){
                return  false;
            }else {
                return  isHappyNumber(result);
            }
        }else {
            return  isHappyNumber(result);
        }

    }

}
