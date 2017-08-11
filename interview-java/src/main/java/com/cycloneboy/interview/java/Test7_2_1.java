package com.cycloneboy.interview.java;

/**
 * Created by CycloneBoy on 2017/8/11.
 * 说明:
 *      用筛选法选出100以内的素数
 */
public class Test7_2_1 {
    public static void main(String[] args) {
       //sushu1();

       sushu2();
    }

    static void sushu1(){
        int a[] = new int[101];
        int i,j;
        for( i = 1; i< 101;i++){
            a[i] = 1;
        }

        for( i =2;i<101;i++){
            if(a[i]!= 0){
                for(j =i+i;j<101;){
                    if(j%i == 0){
                        a[j] = 0;
                        j = j+ i;
                    }
                }
            }
        }

        for (i = 2; i < 101; i++ ){
            if(a[i] != 0){
                System.out.println(i);
            }
        }
    }

    static void sushu2(){
        int a[] = new int[101];
        int i,j,k;
        for(i = 1; i < 100;i ++){
            k = (int)Math.sqrt(i);

            for(j = 2; j <= k ; j++){
                if(i%j == 0){
                    break;
                }
            }

            if (j > k){
                System.out.println(" " + i);
            }
        }
    }
}
