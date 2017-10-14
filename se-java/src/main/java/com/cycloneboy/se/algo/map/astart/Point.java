package com.cycloneboy.se.algo.map.astart;

/**
 * Created by CycloneBoy on 2017/10/14.
 */
public class Point implements Comparable<Point>{
    private int x;
    private int y;
    private Point parent;
    int F,G,H;

    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        this.F = 0;
        this.G = 0;
        this.H = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point getParent() {
        return parent;
    }

    public void setParent(Point parent) {
        this.parent = parent;
    }

    public int getF() {
        return F;
    }

    public void setF(int f) {
        F = f;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    @Override
    public int compareTo(Point o) {
        return this.F - o.F;
    }

    @Override
    public boolean equals(Object obj) {
        Point point = (Point)obj;
        if(point.x == this.x && point.y == this.y){
            return  true;
        }
        return false;
    }

    public static int getDis(Point p1,Point p2){
        int dis = Math.abs(p1.x - p2.x) * 10 + Math.abs(p1.y - p2.y) * 10;
        return dis;
    }

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
