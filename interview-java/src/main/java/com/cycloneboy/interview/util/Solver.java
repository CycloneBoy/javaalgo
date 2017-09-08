package com.cycloneboy.interview.util;

/**
 * Created by CycloneBoy on 2017/9/8.
 */
public class Solver {

    public static void main(String[] args) {
        String s1 = "()()()()";
        String s2 ="(((())))";

        System.out.println(s1.charAt(0) + " " + s1.charAt(s1.length() -1) + " " + s1.length());
        String str = new String("(" + s1.substring(1,s1.length() -1) + ")");
        System.out.println(str);
    }

    public static int count(String s){


        return  0;
    }

    public static boolean test(String s){
        boolean flag = true;
        if((s == null) || (s.length() < 2) ||(s.length() > 20) ){
            return  false;
        }
        if(s.equals("")||"".equals(s)){ //空串
            return  true;
        }
        if(s.equals("()") ||"()".equals(s)){
            return  true;
        }

        if(s.equals("()()") ||"()()".equals(s)){
            return  true;
        }

        if(s.length() > 3){
            String str = s;
            for(;  str.length() >4; ){
                if((str.charAt(0) == '(') && (str.charAt(str.length() -1) == ')')){
                    str = str.substring(1,str.length() -1);
                }else {
                    break;
                }
            }
            return  test( str.substring(1,str.length() -1)+"()" ) ||
                    test( "()"+str.substring(1,str.length() -1) );
        }else {
            return  false;
        }


    }
}
