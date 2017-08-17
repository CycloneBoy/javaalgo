package com.cycloneboy.dp.ch5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Client {
    public static void main(String[] args) {
        //产生一个女生群体
        List<Girl> listGirls = new ArrayList<Girl>();
        // 初始化女生
        for(int i= 0; i< 20; i++){
            listGirls.add(new Girl());
        }
        Teacher teacher = new Teacher();
        teacher.command(new GroupLeader(listGirls));
    }
}
