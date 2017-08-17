package com.cycloneboy.dp.ch5;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Teacher {
    //老师对学生发布命令，清点一下女生
    public void command(GroupLeader groupLeader){
        // 告诉体育委员开始执行清点任务
        groupLeader.countGirls();
    }
}
