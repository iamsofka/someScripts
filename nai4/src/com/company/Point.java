package com.company;

public class Point {
    private double x;
    private double y;
    private double z;
    private double f;
    private String name;
    private int numOfCl = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point(double x, double y, double z, double f, String name){
        this.x = x;
        this.y = y;
        this.z = z;
        this.f = f;
        this.name = name;
    }
    public Point(double x, double y, double z, double f){
        this.x=x;
        this.y=y;
        this.z=z;
        this.f=f;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public static double distance(Point p, Point centroid) {
        return Math.sqrt(Math.pow((centroid.getX() - p.getX()), 2) + Math.pow((centroid.getY() - p.getY()), 2)+ Math.pow((centroid.getZ() - p.getZ()), 2)+ Math.pow((centroid.getF() - p.getF()), 2));
    }

    public String toString(){
        return "{ species: " + this.name + " x: " + this.x + " y: " + this.y + " z: " + this.z + " g: " + this.f + " }";
    }

    public void setCluster(int cluster) {
        this.numOfCl = cluster;
    }

    public int getCluster(){
        return this.numOfCl;
    }
}