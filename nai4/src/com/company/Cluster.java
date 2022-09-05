package com.company;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

    public List<Point> points;
    public Point centroid;
    public int id;

    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList();
        this.centroid = null;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void setPoints(List points) {
        this.points = points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

    public void plotCluster() {
        System.out.println("[Cluster: " + id + "]");
        System.out.println("[Centroid: " + centroid + "]");
        double distanceSum = 0;
        for (Point point : points)
            distanceSum += Point.distance(point, getCentroid());
        System.out.println("Sum of distances between centroids and points: " + distanceSum);
        System.out.println("[Points: ");
        System.out.println();
        for(Point p : points) {
            System.out.println(p);
        }
        System.out.println("]");

    }

}