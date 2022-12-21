package com.zbq.semantic;

import java.util.LinkedList;

/**
 * @author zbq
 * @date 2022/12/20 12:06
 */
public class DrawingData {
    private Point origin;

    private Double rot;

    private Point scale;
    private LinkedList<Point> points;

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Double getRot() {
        return rot;
    }

    public void setRot(Double rot) {
        this.rot = rot;
    }

    public Point getScale() {
        return scale;
    }

    public void setScale(Point scale) {
        this.scale = scale;
    }

    public LinkedList<Point> getPoints() {
        return points;
    }

    public void setPoints(LinkedList<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "DrawingData{" +
                "origin=" + origin +
                ", rot=" + rot +
                ", scale=" + scale +
                ", points=" + points +
                '}';
    }
}
