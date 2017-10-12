package com.cycloneboy.se.algo.map;

/**
 * 地图网格节点类
 * Created by CycloneBoy on 2017/10/12.
 */
public class MapNode {
    private double x;
    private double y;
    private double G;
    private double F;
    private double H;
    private MapNode fatherNode;
    private boolean reachable;
    public MapNode(){
    }

    public MapNode(double x,double y){
        this.x = x;
        this.y = y;
        G = 0;
        F = 0;
        H = 0;
        fatherNode = null;
        reachable = true;
    }

    @Override
    public String toString() {
        return "MapNode [x=" + x + ", y=" + y + ", reachable=" + reachable + "]";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public MapNode getFatherNode() {
        return fatherNode;
    }

    public void setFatherNode(MapNode fatherNode) {
        this.fatherNode = fatherNode;
        this.F = fatherNode.F + this.distanceTo(fatherNode);
        this.G = this.F + this.H;
    }

    /**
     * 计算H值
     */
    public double calH(MapNode fatherNode){
        double distance = 0.0;
        if(fatherNode.x == this.x)
            distance = Math.abs(fatherNode.y - this.y);
        else if(fatherNode.y == this.y)
            distance = Math.abs(fatherNode.x - this.x);
        else
            distance = 1.414*Math.abs(fatherNode.y - this.y);
        return fatherNode.H + distance;
    }

    public double distanceTo(MapNode to){
        return Math.sqrt((x-to.x)*(x-to.x) + (y-to.y)*(y-to.y));
    }


    public void setH(MapNode node){
        H = this.distanceTo(node);
    }

    public double getH() {
        return H;
    }

    public double getG() {
        return G;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MapNode other = (MapNode) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
            return false;
        return true;
    }
}
