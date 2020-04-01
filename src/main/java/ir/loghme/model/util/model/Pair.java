package main.java.ir.loghme.model.util.model;

public class Pair<X,Y> {
    private final X x;
    private final Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getKey() {
        return x;
    }

    public Y getValue() {
        return y;
    }
}
