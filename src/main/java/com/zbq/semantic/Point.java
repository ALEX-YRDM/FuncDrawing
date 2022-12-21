package com.zbq.semantic;

/**
 * @author zbq
 * @date 2022/12/20 12:04
 */
public class Point {
    private Double x;
    private Double y;

    public Point(){}
    public Point(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
