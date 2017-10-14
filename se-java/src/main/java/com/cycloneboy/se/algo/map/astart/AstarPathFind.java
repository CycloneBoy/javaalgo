package com.cycloneboy.se.algo.map.astart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by CycloneBoy on 2017/10/14.
 */
public class AstarPathFind {
    //前四个是上下左右，后四个是斜角
    public final static int[] dx = {0,-1,0,1,-1,-1,1,1};
    public final static int[] dy = {-1,0,1,0,1,-1,-1,1};

    //最外圈都是1表示不可通过
    public final static int[][] map =  {
                   { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                   { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                   { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                   { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };

    public static void main(String[] args) {
        Point start = new Point(1,1);
        Point end = new Point(10,13);

        Stack<Point> stack = printPath(start,end);
        if(stack == null){
            System.out.println("不可达");
        }else {
            while(!stack.isEmpty()){
                System.out.print(stack.pop() + "-> ");
            }
            System.out.println();
        }

    }
    public static Stack<Point> printPath(Point start,Point end){

        ArrayList<Point> openTable = new ArrayList<>();
        ArrayList<Point> closeTable = new ArrayList<>();
        openTable.clear();
        closeTable.clear();

        Stack<Point> pathStack = new Stack<Point>();
        start.setParent(null);
        //该点起到转换作用，就是当前的扩展点
        Point currentPoint = new Point(start.getX(),start.getY());
        boolean flag = true;

        while(flag){
            for( int i = 0 ; i < 8;i++){
                int fx = currentPoint.getX() + dx[i];
                int fy = currentPoint.getY() + dy[i];
                Point tempPoint = new Point(fx,fy);
                if(map[fx][fy] == 1){  //遇到了墙或者障碍物
                    //由于边界都是1中间障碍物也是1，这样不必考虑越界和障碍物点扩展问题
                    //如果不设置边界那么fx >= map.length && fy >= map[0].length判断越界问题
                    continue;
                }else {
                    if(end.equals(tempPoint)){
                        flag = false;
                        // 不是tempPoint,他俩都一样了此时
                        end.setParent(currentPoint);
                        break;
                    }

                    if(i < 4){
                        tempPoint.setG(currentPoint.getG() + 10);//G = 从起点 A 移动到指定方格的移动代价，沿着到达该方格而生成的路径。
                    }else {
                        tempPoint.setG(currentPoint.getG() + 14);
                    }
                    tempPoint.setH(Point.getDis(tempPoint,end));
                    tempPoint.setF(tempPoint.getG() + tempPoint.getH());

                    //因为重新写了equals 方法，所以这里包含只是按equals相等包含
                    if(openTable.contains(tempPoint)){
                        int pos = openTable.indexOf(tempPoint);
                        Point temp = openTable.get(pos);
                        if(temp.getF() > tempPoint.getF()){
                            openTable.remove(pos);
                            openTable.add(tempPoint);
                            tempPoint.setParent(currentPoint);
                        }
                    }else if(closeTable.contains(tempPoint)){
                        int pos = closeTable.indexOf(tempPoint);
                        Point temp = closeTable.get(pos);
                        if(temp.getF() > tempPoint.getF()){
                            closeTable.remove(pos);
                            openTable.add(tempPoint);
                            tempPoint.setParent(currentPoint);
                        }
                    }else {
                        openTable.add(tempPoint);
                        tempPoint.setParent(currentPoint);
                    }
                }

            }// end for

            if(openTable.isEmpty()){
                return  null;
            }

            if(flag == false){
                break;
            }

            openTable.remove(currentPoint);
            closeTable.add(currentPoint);
            Collections.sort(openTable);
            currentPoint = openTable.get(0);
        }

        Point node = end;
        while(node.getParent() != null){
            pathStack.push(node);
            node = node.getParent();
        }
        return pathStack;
    }
}
