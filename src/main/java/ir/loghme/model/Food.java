package main.java.ir.loghme.model;

public class Food {
    private String name;
    private String description;
    private double popularity;
    private double price;

    public Food() {
        this.name = "";
        this.description = "";
        this.popularity = 0;
        this.price = 0;
    }

    public Food(Food f) {
        this.name = f.name;
        this.description = f.description;
        this.popularity = f.popularity;
        this.price = f.price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
