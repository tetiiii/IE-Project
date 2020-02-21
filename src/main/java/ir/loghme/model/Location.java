package main.java.ir.loghme.model;

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


