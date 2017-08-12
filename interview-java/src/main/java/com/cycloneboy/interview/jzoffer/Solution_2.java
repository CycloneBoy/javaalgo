package com.cycloneboy.interview.jzoffer;

/**
 * Created by CycloneBoy on 2017/8/12.
 * 说明：题目描述
    请实现一个函数，将一个字符串中的空格替换成“%20”。
    例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Solution_2 {
    /**
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {

        StringBuffer nstr = new StringBuffer();
        for (int i =0 ;i <str.toString().length(); i++){
            if(str.charAt(i) ==' '){
                nstr.append("%20");
            }else {
                nstr.append(str.charAt(i));
            }
        }

        return nstr.toString();
    }


    /**
     *
     问题1：替换字符串，是在原来的字符串上做替换，还是新开辟一个字符串做替换！
     问题2：在当前字符串替换，怎么替换才更有效率（不考虑java里现有的replace方法）。
         从前往后替换，后面的字符要不断往后移动，要多次移动，所以效率低下
          从后往前，先计算需要多少空间，然后从后往前移动，
            则每个字符只为移动一次，这样效率更高一点。
     * @param str
     * @return
     */
    public String replaceSpace1(StringBuffer str) {
        int spacenum = 0; //空格数量
        for(int i =0;i <str.length();i++){
            if(str.charAt(i) == ' '){
                spacenum++;
            }
        }

        int indexold = str.length()-1;  //indexold为替换前的str下标
        int newlength = str.length() + spacenum*2; //计算空格转换成%20之后的str长度
        int indexnew = newlength -1; //indexnew为替换后的str下标
        str.setLength(newlength); // 使str的长度扩大成%20之后的长度，防止下标越界
        for(; indexold >= 0 && indexold < newlength;--indexold){
            if(str.charAt(indexold) == ' '){
                str.setCharAt(indexnew--,'0');
                str.setCharAt(indexnew--,'2');
                str.setCharAt(indexnew--,'%');
            }else {
                str.setCharAt(indexnew--,str.charAt(indexold));
            }
        }

        return str.toString();
    }
    public static void main(String[] args) {
        Solution_2 solution2 = new Solution_2();
        System.out.println(solution2.replaceSpace(new StringBuffer("We Are Happy")));

        System.out.println(solution2.replaceSpace1(new StringBuffer("We Are Happy")));

    }
}
