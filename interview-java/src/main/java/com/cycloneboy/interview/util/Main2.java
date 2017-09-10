package com.cycloneboy.interview.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by CycloneBoy on 2017/9/8.
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = Integer.valueOf(in.nextLine().trim());

       // System.out.println("行数：" +length);
        List<String> list = new ArrayList<>(length);
        int[] count = new int[length];
        for(int i= 0 ; i < length; i++){
            int tmp =  Integer.valueOf(in.nextLine().trim());
            count[i] = tmp;

            String str = in.nextLine();
            //System.out.println("第"+i + "行 " +count + " 数据为:" + str);
            list.add(str);
        }

        List<String> result = judge(count,list);

        for(int i =0;i< result.size();i++){
            System.out.println(result.get(i));
        }
    }

    public static List<String> judge(int [] count ,List<String> strList){
        List<String> result = new ArrayList<String>();

        for(int i= 0 ; i < strList.size(); i++) {
            long[] test = new long[count[i]];
            String[] line = strList.get(i).split(" ");
            int m = 0;
            int n =0;
            int k = 0;
            for (int j = 0; j < line.length; j++) {
                test[j] = Long.valueOf(line[j]);

                if((test[j]%4) ==0){
                    m++;
                }else if((test[j]%2) == 0){
                    n++;
                }else{
                    k++;
                }
            }

            if(m >= (line.length /2) ){
                result.add(i,"Yes");
            }else if(2 * (line.length /2 - m) <= k){
                result.add(i,"Yes");
            }else {
                result.add(i,"No");
            }


        }
        return  result;
    }
}
