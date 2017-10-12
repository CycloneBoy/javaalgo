package com.cycloneboy.se.algo.map;

import java.util.Deque;
import java.util.HashSet;

/**
 * Map类存储地图信息
 * Created by CycloneBoy on 2017/10/12.
 */
public class Map {
    //节点尺寸
    private double nodeSize;
    //横向节点数
    private int xScale;
    //纵向节点数
    private int yScale;
    //
    private double startX;
    private double startY;
    //存储节点的二维数组
    private MapNode[][] mapNodes;

    public Map(){

    }

    public Map(int xScale, int yScale, double nodeSize, double startX, double startY){
        mapNodes = new MapNode[yScale][xScale];
        this.nodeSize = nodeSize;
        this.xScale = xScale;
        this.yScale = yScale;
        this.startX = startX;
        this.startY = startY;
        double y = startY;
        for(int i = 0;i < yScale;i ++){
            double x = startX;
            for(int j = 0;j < xScale;j ++){
                mapNodes[i][j] = new MapNode(x, y);
                x += nodeSize;
            }
            y += nodeSize;
        }
    }

    public void print(){
        for(int i = 0;i < yScale;i ++){
            for(int j = 0;j < xScale;j ++){
                if(mapNodes[i][j].isReachable())
                    System.out.print("□");
                else
                    System.out.print("■");
            }
            System.out.println();
        }
    }

    public void print(Deque<MapNode> result){
        for(int i = 0;i < yScale;i ++){
            for(int j = 0;j < xScale;j ++){
                if(result.contains(mapNodes[i][j]))
                    System.out.print("*");
                else if(mapNodes[i][j].isReachable())
                    System.out.print("□");
                else
                    System.out.print("■");
            }
            System.out.println();
        }
    }

    /**
     * 编辑地图中的障碍
     * @param x 障碍中心x位置
     * @param y 障碍中心y位置
     * @param size size半径
     */
    public void editObstacle(double x, double y, double size){
        int unitSize = (int)(size/nodeSize);
        MapNode obs = XY2MapNode(x, y);
        if(obs == null)
            return;
        HashSet<MapNode> obstacles = new HashSet<MapNode>();
        obstacles.add(obs);
        for(int i = 0;i < unitSize - 1;i++){
            HashSet<MapNode> temp = new HashSet<MapNode>(obstacles);
            for(MapNode node: temp){
                obstacles.addAll(getNeighbors(node));
            }
        }
        for(MapNode node:obstacles){
            node.setReachable(false);
        }
    }
    /**
     * 获得x y位置的节点
     * @param x
     * @param y
     * @return
     */
    public MapNode XY2MapNode(double x,double y){
        int j = (int)((x-startX)/nodeSize);
        int i = (int)((y-startY)/nodeSize);
        if(j < xScale && i <yScale && j >= 0 && i >= 0)
            return mapNodes[i][j];
        else
            return null;
    }

    /**
     * 返回给定节点在map中对应的节点
     * @param node
     * @return
     */
    public MapNode node2MapNode(MapNode node){
        return XY2MapNode(node.getX(), node.getY());
    }
    /**
     * 获得数组中的索引
     * @param node
     * @return
     */
    public int getXIndex(MapNode node){
        return (int)((node.getX()-startX)/nodeSize);
    }

    public int getYIndex(MapNode node){
        return (int)((node.getY()-startY)/nodeSize);
    }

    /**
     * 返回邻居节点
     * @param node
     * @return
     */
    public HashSet<MapNode> getNeighbors(MapNode node){
        HashSet<MapNode> neighbors = new HashSet<MapNode>();
        int x = getXIndex(node);
        int y = getYIndex(node);
        for(int i = -1;i <= 1;i ++){
            if(y + i >= yScale || y + i < 0)
                continue;
            for(int j = -1;j <= 1;j ++){
                if(i == 0 && j == 0)
                    continue;
                if(x + j >= xScale || x + j < 0)
                    continue;
                if(!mapNodes[y + i][x + j].isReachable())
                    continue;
                if((i*j==-1 || i*j == 1)&&
                        ((!mapNodes[y + i][x].isReachable())||(!mapNodes[y][x + j].isReachable())))
                    continue;
                neighbors.add(mapNodes[y + i][x + j]);
            }
        }
        return neighbors;
    }
}
