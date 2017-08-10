package com.cycloneboy.interview.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by CycloneBoy on 2017/8/10.
 * 说明: 一个字符串中能包含a  - z中的多个字符串，如有重，如string data = "adbadfkjagafdgjadffkja"，
 *        求出出现次数最多的那个字母及次数，如有多个重复的则都求出。
 */
public class Test7_3 {

    public static void main(String[] args) {
        String input = "aadldkdgawigkglafnvglajalkjaklfkfkkfalagaogjaoopiwtuiqb";
        new Test7_3().doString(input);
    }

    public void doString(String input){
        char[] chars = input.toCharArray();
        ArrayList lists = new ArrayList();
        TreeSet set = new TreeSet();
        for(int i =0; i< chars.length ;i++){
            lists.add(String.valueOf(chars[i]));   //引入ArrayList: 为了快速排序，再通过StringBuffer生成排序后的字符串
            set.add(String.valueOf(chars[i]));     //引入TreeSet:通过集合快速找到所有出现的字符串
        }

        System.out.println(set);
        Collections.sort(lists);
        System.out.println(lists);

        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < lists.size();i++){
            sb.append(lists.get(i));
        }

        input = sb.toString();
        System.out.println(input);
        int max = 0;
        String maxString = "";
        ArrayList maxlist = new ArrayList();

        Iterator its = set.iterator();
        while (its.hasNext()){
            String os = (String)its.next();
            int begin = input.indexOf(os);
            int end = input.lastIndexOf(os);  //通过String api 中的基本方法indexOfLastIndexOf来计算TreeSet中每个字符串的最大值
            int value = end - begin + 1;       //如果出现相同的，则把相同的都记录在一个列表中
            if(value > max){
                max = value;               //记录第一个出现次数最多的字符(为了计算多个字符串相同的情况)
                maxString = os;
                maxlist.add(os);
            }else if (value == max){       // 如果出现相同的，则把相同的都记录在一个列表中
                maxlist.add(os);
            }
        }

        int index = 0 ;
        for(int i = 0; i< maxlist.size();i++){       //计算最大字符串列表中哪些才是真正出现次数最多的
            if(maxlist.get(i).equals(maxString)){
                index = i;
                break;
            }
        }
        System.out.println("max data");
        for(int i = index;i<maxlist.size();i++){
            System.out.println(maxlist.get(i) + " ");
        }
        System.out.println();
        System.out.println("max " + max);
    }
}
