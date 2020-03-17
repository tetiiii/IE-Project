package main.java.ir.loghme.model;

import java.lang.Math;

public class Location {
    private double x;
    private double y;

    public Location() {
        this.x = 0;
        this.y = 0;
    }

    public Location(Location l) {
        this.x = l.x;
        this.y = l.y;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceFrom(Location location) {
        return (Math.sqrt(Math.pow((this.x - location.x), 2) + Math.pow((this.y - location.y), 2)));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}


