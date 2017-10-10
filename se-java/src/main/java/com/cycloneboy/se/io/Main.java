package com.cycloneboy.se.io;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        float res;

        int sizea = 0;
        sizea = Integer.parseInt(in.nextLine().trim());
        int[] A = new int[sizea];
        int A_item;
        String[] str1 = in.nextLine().trim().split(" ");

        for (int i = 0; i < sizea; i++) {
            A_item = Integer.parseInt(str1[i]);
            A[i] = A_item;
        }

//        for( int num : A){
//            System.out.print( num + " ");
//        }
//        System.out.println();

        int sizeb = 0;
        sizeb = Integer.parseInt(in.nextLine().trim());
        int[] B = new int[sizeb];
        int B_item;
        String[] str2 = in.nextLine().trim().split(" ");

        for (int i = 0; i < sizeb; i++) {
            B_item = Integer.parseInt(str2[i]);
            B[i] = B_item;
        }
//        for( int num : B){
//            System.out.print( num + " ");
//        }
//        System.out.println();
        res = findNumber(A, B);
        System.out.println(String.valueOf(res));

    }

    static float findNumber(int[] A, int[] B) {
        int[] C = new int[A.length + B.length];
        int aIndex = 0;
        int bIndex = 0;
        int cIndex = 0;
        int min = A.length > B.length ? B.length : A.length;
        System.out.println(" min " + min + " ");
        float result = 0;

        for (int i = 0; aIndex < A.length && bIndex < B.length; i++) {
            if (A[aIndex] > B[bIndex]) {
                C[cIndex++] = B[bIndex++];
            } else if (A[aIndex] < B[bIndex]) {
                C[cIndex++] = A[aIndex++];
            } else {
                C[cIndex++] = B[bIndex++];
                aIndex++;
            }
        }

        for(int i = 0;i < min;i++){
            System.out.print( C[i] + " ");
        }
        System.out.println();
        System.out.println(aIndex + " " + bIndex + " " + cIndex);

        if (aIndex < A.length) {
            for (int i = aIndex; i < A.length; i++) {
                C[cIndex++] = A[aIndex++];
            }
        }

        if (bIndex < B.length) {
            for (int i = bIndex; i < B.length; i++) {
                C[cIndex++] = B[bIndex++];
            }
        }

        for(int i =0 ; i < C.length;i++){
            System.out.print(C[i] + " ");
        }

        if (cIndex % 2 == 1) {
            result = C[cIndex / 2];
        } else {
            result = (float) ((C[cIndex / 2] + C[cIndex / 2 - 1]) / 2.0);
        }
        return result;
    }

}

