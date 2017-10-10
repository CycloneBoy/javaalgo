package com.cycloneboy.se.io;


import java.util.Scanner;

public class Main4 {

    public static void main(String[] args)  {

        Scanner in = new Scanner(System.in);
        int res;

        int _A_size = 0;
        _A_size = Integer.parseInt(in.nextLine().trim());
        int[] _A = new int[_A_size];
        int _A_item;
        for(int _A_i = 0; _A_i < _A_size; _A_i++) {
            _A_item = Integer.parseInt(in.nextLine().trim());
            _A[_A_i] = _A_item;
        }

        res = findMinMis(_A);
        System.out.println(String.valueOf(res));

    }

    static int findMinMis(int[] A) {
        int min = A[0];
        int result = 1;
        for(int i = 0;i < A.length - 1;i ++){
            for(int j = i;j < A.length ;j ++){
                if(A[j] < A[i]){
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }

        for(int i =0 ;i < A.length -1;i++){
            if(A[i] > 0 && (A[i + 1] - A[i] >=2)){
                result = A[i] + 1;
                break;
            }else if(A[i] > 0 && (A[i + 1] - A[i] >=1)) {
                result = A[i+1] + 1;
            }else{
                continue;
            }
        }

        return  result;
    }


    //            if(A[i] < min && A[i] > 0){
//                min  = A[i];
//                result = min + 1;
//            }else if(A[i] == result){
//                result += 1;
//            }
}
