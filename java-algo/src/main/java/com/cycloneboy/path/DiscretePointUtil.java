/**
 * 文件名    :DiscretePointUtil.java
 * 项目名称:GraphAglo
 * 描述信息: 离散点计算工具
 * 版本信息: V1.0
 * 创建日期:2017年4月12日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.path;

import java.util.List;

/**
 * * <p>
 * <b>离散点计算工具</b>
 * <p>
 * <pre>
 * 离散点计算工具
 * 
 *  y
 *  ↑   ·  ·
 *  │  · ·   ·
 *  │ ·  · ·   ·
 *  │  ·  ·  
 * —│————————————→ x
 * </pre>
 *
 * @author CycloneBoy
 *
 */
public class DiscretePointUtil {
	/**
     * <p>
     * <b>查找离散点集中的(min_x, min_Y) (max_x, max_Y)</b>
     * <p>
     * <pre>
     * 查找离散点集中的(min_x, min_Y) (max_x, max_Y)
     * </pre>
     *
     * @author CycloneBoy
     * @param   points    离散点集
     * @return  [(min_x, min_Y), (max_x, max_Y)]
     */
    public static Point[] calMinMaxDots(final List<Point> points) {
        if (null == points || points.isEmpty()) {
            return null;
        }

        double min_x = points.get(0).getX(), max_x = points.get(0).getX();
        double min_y = points.get(0).getY(), max_y = points.get(0).getY();

        /* 这里存在优化空间，可以使用并行计算 */
        for (Point point : points) {
            if (min_x > point.getX()) {
                min_x = point.getX();
            }

            if (max_x < point.getX()) {
                max_x = point.getX();
            }

            if (min_y > point.getY()) {
                min_y = point.getY();
            }

            if (max_y < point.getY()) {
                max_y = point.getY();
            }
        }

        Point ws = new Point(min_x, min_y);
        Point en = new Point(max_x, max_y);

        return new Point[] { ws, en };
    }

    /**
     * <p>
     * <b>求矩形面积平方根</b>
     * <p>
     * <pre>
     * 以两个点作为矩形的对角线上的两点，计算其面积的平方根
     * </pre>
     *
     * @author CycloneBoy
     * @param   ws  西南点
     * @param   en  东北点
     * @return  矩形面积平方根
     */
    public static double calRectAreaSquare(Point ws, Point en) {
        if (null == ws || null == en) {
            return .0;
        }

        /* 为防止计算面积时float溢出，先计算各边平方根，再相乘 */
        return Math.sqrt(Math.abs(ws.getX() - en.getX()))
                * Math.sqrt(Math.abs(ws.getY() - en.getY()));
    }

    /**
     * <p>
     * <b>求两点之间的长度</b>
     * <p>
     * <pre>
     * 求两点之间的长度
     * </pre>
     *
     * @author  ManerFan 2015年4月10日
     * @param   ws  西南点
     * @param   en  东北点
     * @return  两点之间的长度
     */
    public static double calLineLen(Point ws, Point en) {
        if (null == ws || null == en) {
            return .0;
        }

        if (ws.equals(en)) {
            return .0;
        }

        double a = Math.abs(ws.getX() - en.getX()); // 直角三角形的直边a
        double b = Math.abs(ws.getY() - en.getY()); // 直角三角形的直边b

        double min = Math.min(a, b); // 短直边
        double max = Math.max(a, b); // 长直边

        /**
         * 为防止计算平方时float溢出，做如下转换
         * √(min²+max²) = √((min/max)²+1) * abs(max)
         */
        double inner = min / max;
        return Math.sqrt(inner * inner + 1.0) * max;
    }

    /**
     * <p>
     * <b>求两点间的中心点</b>
     * <p>
     * <pre>
     * 求两点间的中心点
     * </pre>
     *
     * @author  ManerFan 2015年4月10日
     * @param   ws  西南点
     * @param   en  东北点
     * @return  两点间的中心点
     */
    public static Point calCerter(Point ws, Point en) {
        if (null == ws || null == en) {
            return null;
        }

        return new Point(ws.getX() + (en.getX() - ws.getX()) / 2.0, ws.getY()
                + (en.getY() - ws.getY()) / 2.0);
    }

    /**
     * <p>
     * <b>计算向量角</b>
     * <p>
     * <pre>
     * 计算两点组成的向量与x轴正方向的向量角
     * </pre>
     *
     * @author  ManerFan 2015年4月17日
     * @param   s   向量起点
     * @param   d   向量终点
     * @return  向量角
     */
    public static double angleOf(Point s, Point d) {
        double dist = calLineLen(s, d);

        if (dist <= 0) {
            return .0;
        }

        double x = d.getX() - s.getX(); // 直角三角形的直边a
        double y = d.getY() - s.getY(); // 直角三角形的直边b

        if (y >= 0.) { /* 1 2 象限 */
            return Math.acos(x / dist);
        } else { /* 3 4 象限 */
            return Math.acos(-x / dist) + Math.PI;
        }
    }

    /**
     * <p>
     * <b>修正角度</b>
     * <p>
     * <pre>
     * 修正角度到 [0, 2PI]
     * </pre>
     *
     * @author  ManerFan 2015年4月17日
     * @param   angle   原始角度
     * @return  修正后的角度
     */
    public static double reviseAngle(double angle) {
        while (angle < 0.) {
            angle += 2 * Math.PI;
        }
        while (angle >= 2 * Math.PI) {
            angle -= 2 * Math.PI;
        }

        return angle;
    }

}
