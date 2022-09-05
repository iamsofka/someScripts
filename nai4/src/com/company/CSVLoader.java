package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CSVLoader {
    private static List<Double> data1 = new ArrayList<>();
    private static List<Double> data2 = new ArrayList<>();
    private static List<Double> data3 = new ArrayList<>();
    private static List<Double> data4 = new ArrayList<>();
    private static double a;
    private static double b;
    private static double c;
    private static double d;
    private static double e;
    private static double i;
    private static double g;
    private static double h;


    public static List<Point> load(String fileName) {
        String line;
        List<Point> points = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while ((line = bufferedReader.readLine()) != null) {
                String [] data = line.split(",");
                points.add(new Point(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), data[4]));
                data1.add(Double.parseDouble(data[0]));
                data2.add(Double.parseDouble(data[1]));
                data3.add(Double.parseDouble(data[2]));
                data4.add(Double.parseDouble(data[3]));
            }

            Collections.sort(data1);
            Collections.sort(data2);
            Collections.sort(data3);
            Collections.sort(data4);

            a = data1.get(0);
            b = data2.get(0);
            c = data3.get(0);
            d = data4.get(0);
            e = data1.get(data1.size()-1);
            i = data2.get(data2.size()-1);
            g = data3.get(data3.size()-1);
            h = data4.get(data4.size()-1);
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return points;
    }


    public static double getA() {
        return a;
    }

    public static void setA(double a) {
        CSVLoader.a = a;
    }

    public static double getB() {
        return b;
    }

    public static void setB(double b) {
        CSVLoader.b = b;
    }

    public static double getC() {
        return c;
    }

    public static void setC(double c) {
        CSVLoader.c = c;
    }

    public static double getD() {
        return d;
    }

    public static void setD(double d) {
        CSVLoader.d = d;
    }

    public static double getE() {
        return e;
    }

    public static void setE(double e) {
        CSVLoader.e = e;
    }

    public static double getI() {
        return i;
    }

    public static void setI(double i) {
        CSVLoader.i = i;
    }

    public static double getG() {
        return g;
    }

    public static void setG(double g) {
        CSVLoader.g = g;
    }

    public static double getH() {
        return h;
    }

    public static void setH(double h) {
        CSVLoader.h = h;
    }
}