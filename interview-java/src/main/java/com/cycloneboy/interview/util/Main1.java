package com.cycloneboy.interview.util;

import java.util.Scanner;

/**
 * 现公司要开发一个业务管理系统，要求注册环节的密码需要提示用户其安全等级，密码按如下规则进行计分，并根据不同的得分为密码进行安全等级划分。
 一、密码长度:
 5 分: 小于等于4 个字符
 10 分: 5 到7 字符
 25 分: 大于等于8 个字符
 二、字母:
 0 分: 没有字母
 10 分: 全都是小（大）写字母
 20 分: 大小写混合字母
 三、数字:
 0 分: 没有数字
 10 分: 1 个数字
 20 分: 大于1 个数字
 四、符号:
 0 分: 没有符号
 10 分: 1 个符号
 25 分: 大于1 个符号
 五、奖励:
 2 分: 字母和数字
 3 分: 字母、数字和符号
 5 分: 大小写字母、数字和符号
 最后的评分标准:
 大于等于 90: 非常安全
 大于等于80: 安全
 大于等于70: 非常强
 大于等于 60: 强
 大于等于 50: 一般
 大于等于 25: 弱
 大于等于0:  非常弱

 对应输出为：
 VERY_WEAK,
 WEAK,
 AVERAGE,
 STRONG,
 VERY_STRONG,
 SECURE,
 VERY_SECURE
 * Created by CycloneBoy on 2017/9/8.
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();



    }

    public static int  judge(String str){
        int result = 0;
        if(str.length() <= 4){
            result += 5;
        }else if (str.length() > 4 && str.length() < 8){
            result += 10;
        }else {
            result += 25;
        }


        return  result;
    }
}
