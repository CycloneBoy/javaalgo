package com.cycloneboy.interview.java;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/8/10.
 * 说明: 把一个数组里的数组合全部列出，比如1和2列出来为1,2,12,21
 *
 */
public class Test7_1 {
    public static void main(String[] args) {
        String[] array = new String[]{
                "1","2","3","4"
        };

        listall(Arrays.asList(array),"");
    }

    public static void listall(List candidate,String prefix){
        //if (candidate.isEmpty()){
            System.out.println(prefix);
       // }

        for(int i = 0; i < candidate.size(); i++){
            List temp = new LinkedList(candidate);
            listall(temp,prefix + temp.remove(i));
        }
    }
}
