package com.cycloneboy.se.algo.map.astart;

import java.awt.Point;
import java.util.LinkedList;

/**
 * Created by CycloneBoy on 2017/10/13.
 */
public class Node implements Comparable{
    public  Point _Pos;   //position of the node
    public int sourcePoint;
    public int destiPoint;
    //public Node _parentnode;    //the parent node
    public Node _parentnode;
    private Node()
    {

    }
    //initialize the NOde
    public Node(Point _Pos)
    {
        this._Pos=_Pos;
    }
    //get the cost of the Path
    public int GetCost(Node node)
    {
        int m=node._Pos.x-_Pos.x;
        int n=node._Pos.y-_Pos.y;
        return (int)Math.sqrt(m*m+n*n);
    }
    //check if the node is the destination point
    public boolean equals(Object node)
    {
        if (_Pos.x == ((Node) node)._Pos.x && _Pos.y == ((Node) node)._Pos.y)
        {
            return true;
        }
        return false;
    }
    //get the minist cost
    public int compareTo(Object node)
    {
        int a1=sourcePoint+destiPoint;
        int a2=((Node)node).sourcePoint+((Node)node).destiPoint;
        if(a1<a2){
            return -1;
        }else if(a1==a2)
        {return 0;}
        else return 1;

    }
    public LinkedList getLimit()
    {
        LinkedList limit=new LinkedList();
        int x=_Pos.x;
        int y=_Pos.y;
        limit.add(new Node(new Point(x,y-1)));   //up
        limit.add(new Node(new Point(x,y+1)));   //down
        limit.add(new Node(new Point(x-1,y)));   //left
        limit.add(new Node(new Point(x+1,y)));   //right
        return limit;
    }
}
