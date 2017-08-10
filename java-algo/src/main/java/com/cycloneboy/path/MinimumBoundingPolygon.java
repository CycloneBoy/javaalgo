/**
 * 文件名    :MinimumBoundingPolygon.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月12日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.path;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author CycloneBoy
 * * <p>
 * <b>最小（凸）包围边界查找</b>
 * <p>
 * <pre>
 * 最小（凸）包围边界查找
 * 
 * Minimum Bounding Polygon (Convex Hull; Smallest Enclosing A Set of Points)
 * <b><a href="http://alienryderflex.com/smallest_enclosing_polygon/">©2009 Darel Rex Finley.</a></b>
 *
 *  y
 *  ↑   ·  ·
 *  │  · ·   ·
 *  │ ·  · ·   ·
 *  │  ·  ·  
 * —│————————————→ x
 *
 * </pre>
 */
public class MinimumBoundingPolygon {
	final static int NUM_POINT = 20;
	
	public static void main(String[] args){
		List<Point> obstacles = new LinkedList<>();
		Point[] points = new Point[10];
		points[0] = new Point( 29.554568,123.228183);
		points[1] = new Point(  29.616925,123.690873);
		points[2] = new Point(  29.341085,123.884080);
		points[3] = new Point(  29.292178,123.802687);
		points[4] = new Point( 29.194010,123.394008);
		points[5] = new Point( 29.141960,123.774565);
		points[6] = new Point( 29.422745,123.501018);
		points[7] = new Point(  29.275875,123.570133);
		points[8] = new Point(  29.342516,123.149144);
		points[9] = new Point(  29.446803,123.674111);
			
		System.out.println("初始化坐标点:" );
		for (int i = 0; i < points.length; i++) {
			obstacles.add(points[i]);//添加多个点
			System.out.println("Point[" + i + "] = " + points[i].getX() + 
					"," + points[i].getY() );
			
		}
		
		LinkedList<Point> resultPoint = new LinkedList<>();
		
		resultPoint = findSmallestPolygon(obstacles);
		System.out.println("\nfind the polygon:" + resultPoint.size());
		
		for (int i = 0; i < resultPoint.size(); i++) {
			System.out.println("Point[" + i +"] = " + resultPoint.get(i).getX() + 
					" ," +resultPoint.get(i).getY());
		}
	}
	
	
	public static LinkedList<Point> findSmallestPolygon(List<Point> ps) {
        if (null == ps || ps.isEmpty()) {
            return null;
        }

        Point corner = findStartPoint(ps);
        if (null == corner) {
            return null;
        }

        double minAngleDif, oldAngle = 2 * Math.PI;
        LinkedList<Point> bound = new LinkedList<>();
        do {
            minAngleDif = 2 * Math.PI;

            bound.add(corner);

            Point nextPoint = corner;
            double nextAngle = oldAngle;
            for (Point p : ps) {
                if (p.founded) { // 已被加入边界链表的点
                    continue;
                }

                if (p.equals(corner)) { // 重合点
                    /*if (!p.equals(bound.getFirst())) {
                        p.founded = true;
                    }*/
                    continue;
                }

                double currAngle = DiscretePointUtil.angleOf(corner, p); /* 当前向量与x轴正方向的夹角 */
                double angleDif = DiscretePointUtil.reviseAngle(oldAngle - currAngle); /* 两条向量之间的夹角（顺时针旋转的夹角） */

                if (angleDif < minAngleDif) {
                    minAngleDif = angleDif;
                    nextPoint = p;
                    nextAngle = currAngle;
                }
            }

            oldAngle = nextAngle;
            corner = nextPoint;
            corner.founded = true;
        } while (!corner.equals(bound.getFirst())); /* 判断边界是否闭合 */

        return bound;
    }

    /** 查找起始点（保证y最大的情况下、尽量使x最小的点） */
    private static Point findStartPoint(List<Point> ps) {
        if (null == ps || ps.isEmpty()) {
            return null;
        }

        Point p = ps.get(0);
        ListIterator<Point> iter = ps.listIterator();

        while (iter.hasNext()) {
            Point point = iter.next();
            if (point.getY() > p.getY() || (point.getY() == p.getY() && point.getX() < p.getX())) { /* 找到最靠上靠左的点 */
                p = point;
            }
        }

        return p;
    }
}
