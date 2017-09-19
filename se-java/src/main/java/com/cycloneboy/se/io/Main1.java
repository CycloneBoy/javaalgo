package com.cycloneboy.se.io;

public class Main1 {

    public static void main(String[] args)  {
        for(int i=0;i<100;i++){
            int num=(int)(Math.random()*(1000));
            Main1 t=new Main1();
            if(t.isPrime(num)){
                System.out.println(num+"是素数");
            }else {
                System.out.println(num+"不是素数");
            }
        }
    }

    private boolean isPrime(int num) {

        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                // System.out.println(num+"第一个被"+i+"整除");
                return false;
            }
        }
        return true;
    }
}
