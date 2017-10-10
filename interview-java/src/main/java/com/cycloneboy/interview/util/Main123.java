package com.cycloneboy.interview.util;

import java.util.Scanner;

/**
 * Created by CycloneBoy on 2017/10/10.
 */
public class Main123 {

    public static void main(String args[])
    {
        Scanner cin = new Scanner(System.in);
        int n =0,r,avg;

        n = cin.nextInt();
        r = cin.nextInt();
        avg = cin.nextInt();
        System.out.println(n +" " +  r + " " + avg);

        int[] a = new int[n];
        int[] b = new int[n];
        int sum = 0;
        int need = n * avg; //总分数
        for(int i =0; i< n;i++){
            a[i] = cin.nextInt();
            sum += a[i];
            b[i] = cin.nextInt();
           //System.out.println("a[i] " + a[i] + " " + b[i]);
        }
        System.out.println("need sum " + need + " " + sum);


        for(int i = 0 ; i < n-1;i++){
            for(int j= i+1;j < n;j++){
                if(b[i] > b[j] ){
                    int temp = b[i];
                    b[i] = b[j];
                    b[j] = temp;

                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

        for(int i = 0;i < n;i++){
            System.out.println(b[i] + " " + a[i]);
        }

        int result = 0 ;
        if(need > sum){
            int k = 0;
            for(; k<n;k++){
                sum +=(r - a[k]);
                if(sum < need){
                    result += b[k] *(r - a[k]);
                }else {
                    sum -=(r - a[k]);
                    System.out.println( "sum need " + sum + " " + need);
                    break;
                }
            }
            result +=b[k] * (need -sum);
        }

        System.out.println(result);
    }

}
