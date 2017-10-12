package com.cycloneboy.se.algo.map;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by CycloneBoy on 2017/10/12.
 */
public class AstarPathPlan {
    public Map map;
    private HashSet<MapNode> closeList;
    private HashSet<MapNode> openList;
    /**
     * 新建地图
     * @param xScale 横向节点数
     * @param yScale 纵向节点数
     * @param nodeSize 节点尺寸（正方形网格边长）
     * @param startX 地图开始的x坐标
     * @param startY 地图开始的y坐标
     */
    public AstarPathPlan(int xScale, int yScale, double nodeSize, double startX, double startY){
        map = new Map(xScale, yScale, nodeSize, startX, startY);
        closeList = new HashSet<MapNode>();
        openList = new HashSet<MapNode>();
    }

    /**
     * A*算法
     * @param from 开始节点
     * @param to 终止节点
     */
    public LinkedList<MapNode> pathPlanning(MapNode from, MapNode to){
        openList.clear();
        closeList.clear();
        to = map.node2MapNode(to);
        from = map.node2MapNode(from);
        if(!from.isReachable() || !to.isReachable())
            return null;
        if(map.node2MapNode(from) == null || map.node2MapNode(to) == null)
            return null;
        //将起点压入openList
        openList.add(from);
        //如果openList不空，则循环
        while(!openList.isEmpty()){
//          System.out.println(openList);
            //取openList中最佳节点
            MapNode currentNode = getBestNode(openList);
            //获取该节点邻居
            HashSet<MapNode> neighbors = map.getNeighbors(currentNode);
            //若邻居中包含终点，则终止
            if(neighbors.contains(to)){
                to.setFatherNode(currentNode);
                break;
            }
            for(MapNode neighbor:neighbors){
                //如果邻居在close表中，则
                if(closeList.contains(neighbor))
                    continue;
                neighbor.setH(to);
                //若该邻居节点存在于open表中
                if(openList.contains(neighbor)){
                    MapNode n = getNodeFromList(neighbor, openList);
                    double H = neighbor.calH(currentNode);
                    //若该邻居节点的G值更小，则替换
                    if(H < n.getH()){
                        neighbor.setFatherNode(currentNode);
                        openList.add(neighbor);
                    }
                }
                else{
                    neighbor.setFatherNode(currentNode);
                    openList.add(neighbor);
                }
            }
            closeList.add(currentNode);
            openList.remove(currentNode);
        }
        return getResult(from, to);
    }

    /**
     * 从计算好的Map中输出结果
     * @param from
     * @param to
     * @return
     */
    public LinkedList<MapNode> getResult(MapNode from,MapNode to){
        LinkedList<MapNode> result = new LinkedList<MapNode>();
        MapNode node = to;
        while(!node.equals(from)){
            if(node.getFatherNode() == null)
                return null;
            result.push(node);
            node = node.getFatherNode();
        }
        return result;
    }

    /**
     * 从集合中选取G值最小的节点
     * @param set
     * @return
     */
    public MapNode getBestNode(HashSet<MapNode> set){
        MapNode bestNode = null;
        double bestG = Double.MAX_VALUE;
        for(MapNode node: set){
            if(node.getG() < bestG){
                bestG = node.getG();
                bestNode = node;
            }
        }
        return bestNode;
    }

    public MapNode getNodeFromList(MapNode node, HashSet<MapNode> openList){
        for(MapNode n:openList){
            if(n.equals(node))
                return n;
        }
        return null;
    }

    public void printResult(Deque<MapNode> result){
        if(result == null)
            return;
        map.print(result);
    }

}
