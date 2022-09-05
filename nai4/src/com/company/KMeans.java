package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KMeans{
    private static int numOfClusters;
    private List<Point> points;
    private List<Cluster> clusters;

    public KMeans() {
        this.points = CSVLoader.load("iris_test.txt");
        this.clusters = new ArrayList();
    }

    public static void main(String[] args) {
        System.out.println("Write number of clusters: ");
        Scanner scanner = new Scanner(System.in);
        numOfClusters = scanner.nextInt();
        KMeans kMeans = new KMeans();
        kMeans.init();
        kMeans.calculate();
    }

    public void init() {
            for (int i = 1; i <= numOfClusters; i++) {
                Cluster cluster = new Cluster(i);
                Point centroid = new Point((CSVLoader.getA() + Math.random() * (CSVLoader.getE() - CSVLoader.getA())),
                                           (CSVLoader.getB() + Math.random() * (CSVLoader.getI() - CSVLoader.getB())),
                                           (CSVLoader.getC() + Math.random() * (CSVLoader.getG() - CSVLoader.getC())),
                                           (CSVLoader.getD() + Math.random() * (CSVLoader.getH() - CSVLoader.getD())));
                cluster.setCentroid(centroid);
                clusters.add(cluster);
            }
            plotClusters();
    }

    private void plotClusters() {
        for (int i = 0; i < clusters.size(); i++) {
            double setosaCount = 0;
            double virginicaCount = 0;
            double versicolorCount = 0;
            double[] elements = {setosaCount,virginicaCount,versicolorCount};
            double actualGroup = 0;
            double entropy = 0;
            Cluster cluster = clusters.get(i);
            for (Point p : clusters.get(i).getPoints()) {
                actualGroup++;
                if (p.getName().equals("Iris-setosa")) {
                    elements[0]++;
                } else if (p.getName().equals("Iris-virginica")) {
                    elements[1]++;
                } else if (p.getName().equals("Iris-versicolor")) {
                    elements[2]++;
                }
            }

            double proportion1 = elements[0] / actualGroup;
            if (proportion1 != 0 && proportion1 != 1) {
                entropy += Math.log(proportion1)/Math.log(2) * proportion1;
            }
            double proportion2 = elements[1] / actualGroup;
            if (proportion2 != 0 && proportion2 != 1) {
                entropy += Math.log(proportion2)/Math.log(2) * proportion2;
            }
            double proportion3 = elements[2] / actualGroup;
            if (proportion3 != 0 && proportion3 != 1) {
                entropy += Math.log(proportion3)/Math.log(2) * proportion3;
            }

            cluster.plotCluster();
            entropy = entropy * -1;
            System.out.println("Entropy " + entropy);
//            List listCentroids = getCentroids();
//            List currentCentroids = getCentroids();
//            double distance = 0;
//            for (int k = 0; k < listCentroids.size(); k++) {
//                distance += Point.distance((Point) listCentroids.get(i), (Point) currentCentroids.get(i));
//            }
//            System.out.println("Centroid distances: " + distance);
        }
    }

    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        while (!finish) {
            List currentCentroids = getCentroids();
            List listCentroids = getCentroids();
            clearClusters();
            assignPoint();
            updateCentroids();
            iteration++;
            double distance = 0;
            for (int i = 0; i < listCentroids.size(); i++)
                distance += Point.distance((Point) listCentroids.get(i), (Point) currentCentroids.get(i));

            System.out.println("--------------------------------------------");
            System.out.println("Iteration: " + iteration);
            plotClusters();

            if (distance == 0) {
                finish = true;
            }
        }
    }

    private void updateCentroids() {
        for (Cluster cluster : clusters) {
            double sumX = 0;
            double sumY = 0;
            double sumZ = 0;
            double sumF = 0;
            List<Point> points = cluster.getPoints();
            int numOfPoints = points.size();
            for (Point point : points) {
                sumX += point.getX();
                sumY += point.getY();
                sumZ += point.getZ();
                sumF += point.getF();
            }

            Point centroid = cluster.getCentroid();
            if (numOfPoints > 0) {
                double newX = sumX / numOfPoints;
                double newY = sumY / numOfPoints;
                double newZ = sumZ / numOfPoints;
                double newF = sumF / numOfPoints;
                centroid.setX(newX);
                centroid.setY(newY);
                centroid.setZ(newZ);
                centroid.setF(newF);
            }
        }

    }

    private List getCentroids() {
        List centroids = new ArrayList();
        for (Cluster cluster : clusters) {
            Point centroid = cluster.getCentroid();
            Point point = new Point(centroid.getX(), centroid.getY(), centroid.getZ(), centroid.getF());
            centroids.add(point);
        }
        return centroids;

    }

    private void assignPoint() {
        double max = Double.MAX_VALUE;
        double min;
        int cluster = 0;
        double distance;

        for (Point point : points) {
            min = max;
            for (int i = 0; i < numOfClusters; i++) {
                Cluster cluster1 = clusters.get(i);
                distance = Point.distance(point, cluster1.getCentroid());
                if (distance < min) {
                    min = distance;
                    cluster = i;
                }
            }

            point.setCluster(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }
}
