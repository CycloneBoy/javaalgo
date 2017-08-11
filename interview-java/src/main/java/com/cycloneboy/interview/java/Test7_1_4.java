package com.cycloneboy.interview.java;

/**
 * Created by CycloneBoy on 2017/8/11.
 * 说明:
 *      利用1、2、2、3、4这五个数,用Java写一个main函数，打印出所有的不同排列，如12234,21234
 *      等，要求打印出来的不能有重复。
 */
public class Test7_1_4 {
    static int count =0;

    public static void main(String[] args) {
        String s = "1223";
        String s2 = "1232";
        int t = 1&0;
        //System.out.println(t);

        int index[] = new int[s.length()];
        for(int i = 0; i< s2.length(); i++){ //该循环将所有的字符第一次出现的位置记录在数组index中
            index[i] = s2.indexOf(s2.charAt(i));
            //System.out.println(s2.indexOf(s2.charAt(i)));
        }

        Pailie(s,"");
        System.out.println("Total:" + count);
    }

    static void Pailie(String s, String p){
        if(s.length() < 1){
            System.out.println(p);                   // 字符串长度小于1，换行
            count++;
        }else {
            int index[] = new int[s.length()];
            for(int i =0;i <s.length();i++){
                index[i] = s.indexOf(s.charAt(i));
               // System.out.println(s.indexOf(s.charAt(i)));
            }

            for ( int i =0 ; i < s.length(); i++){
                if(i == index[i]){                           //只有当循环数与第一次记录数相等时才递归，保证相
                                                            // 同字符中的第一个调用
                   // System.out.println("ppp" + index[i]);
                    Pailie(s.substring(1),p+s.substring(0,1));  //递归，打印其他字符
                }

                s = s.substring(1) + s.substring(0,1);      //循环移位
            }
        }
    }
}
