package com.cycloneboy.se.algo.map;

import java.util.Deque;

/**
 * Created by CycloneBoy on 2017/10/12.
 */
public class Test {
    public static void main(String[] args) {
        //初始化地图，横向x个节点，纵向y个节点，节点尺寸为1，起点为（0,0）
        AstarPathPlan a = new AstarPathPlan(10, 10, 1, 0, 0);
        //编辑障碍，中心（5,5）,半径3个节点
        a.map.editObstacle(5, 5, 3);
        a.map.editObstacle(8, 3, 2);
        //打印地图
        a.map.print();
        System.out.println();
        //执行A*算法，起点（0,0）,终点（8,7）,结果保存至堆栈
        Deque<MapNode> result = a.pathPlanning(new MapNode(0, 0), new MapNode(8,7));
        //打印结果
        a.printResult(result);
    }

}
