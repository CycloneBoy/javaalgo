package com.cycloneboy.interview.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main7 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] input = str.split(",");

        if(input.length  != 3 ){
            System.out.println("incorrect data" + input.length);
        }
        int total = Integer.valueOf(input[0]);
        if(total <=2 ){
            System.out.println("incorrect data " + total);
        }
        Date start = null;
        Date end = null;
        SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");

        try{
            start = sf.parse(input[1]);
            end =  sf.parse(input[2]);
            if(end.getTime() <= start.getTime()){
                System.out.println("incorrect data");
            }
        }catch (Exception e){
            System.out.println("incorrect data");
        }

        System.out.println(total + " " + start.toString() + " " + end.toString());
        long temp = (end.getTime() - start.getTime())/1000;
        System.out.println(temp );


    }

    public String judge(int total ,Date start,Date end){
        int quan = (int)((end.getTime() - start.getTime())/1000)/(total * 15 * 60);
        long temp = ((end.getTime() - start.getTime())/1000)%(total * 15 * 60);
        int st = (int)start.getTime()/1000;
        int en = (int)end.getTime()/1000 - quan * total * 15 * 60;
        int park1=0;
        int park2=0;
        int flag = 0;
        String result = null;
        for(int i = 1; i <= total;i++){
            if( en >= st + (i-1)*900  && en <= st + (i-1)*900+ 300 ){
                park1 = i;
                flag = 1;
                break;
            }else if( en > st + (i-1)*900 + 300 && en < st + (i)*900){
                park1 = i;
                flag = 2; //在路上
                break;
            }
        }
        if(flag == 1){
            //System.out.println(quan + "," + park1 + "-"+park1);
            result = new String(quan + "," + park1 + "-"+park1);
        }else if (flag == 2){
            if(park1 == total ){
                park2 = 1;
            }else {
                park2 = park1+1;
            }
            //System.out.println(quan + "," + park1 + "-"+park2);
            result = new String(quan + "," + park1 + "-"+park2);
        }else {
            result = new String( "incorrect data");
        }

        return result;
    }
}
