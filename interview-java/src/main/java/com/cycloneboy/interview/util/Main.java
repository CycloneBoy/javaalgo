package com.cycloneboy.interview.util;


import java.util.*;

/**
 * Created by CycloneBoy on 2017/9/8.
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


//        while(in.hasNextLine()){
//            int num = Integer.valueOf(in.nextLine().trim());
//           // System.out.println(test(num));
//            //isHappyNumber(num);
//           // num = in.nextInt();
//        }

        String str = "Hello, nice to see you. ";

        List<Integer> res = test(str);

        System.out.println(res);

    }

    public static  List<Integer> test(String str){
        List<Integer> res = new ArrayList<Integer>();

        if(str == null){
            return null;
        }

        int k = 0;
       // int j = 0;
        System.out.println("length: " + str.length());
        for(int i =0;i < str.length() && str.charAt(i) != '.';i++){
            //System.out.print(str.charAt(i) + " ");

                if(str.charAt(i) != ','|| str.charAt(i) != ' ' ){
                    k++;
                }else{
                    System.out.println(k + " ");
                    if(k!=0){
                        res.add(k );
                        System.out.print(k + " ");
                        k = 0;
                    }
                    //j = i;
                }

        }



        return  res;
    }


}
