package com.cycloneboy.dp.ch5;

import java.util.List;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class GroupLeader {
    private List<Girl> listGirls;

    public GroupLeader(List<Girl> _listGirls){
        this.listGirls = _listGirls;
    }

    // 清点女生数量
    public void countGirls(){
        System.out.println("女生的数量是: " + this.listGirls.size());
    }
}
