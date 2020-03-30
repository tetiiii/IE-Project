package main.java.ir.loghme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.net.URL;
public class Food {
    private String name;
    private URL image;
    private String description;
    private double popularity;
    private double price;

    public Food(URL image, String name, String description, double popularity, double price ) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.popularity = popularity;
        this.price = price;
    }

    public Food() {
        this.name = "";
        this.image = null;
        this.description = "";
        this.popularity = 0;
        this.price = 0;
    }

    public Food(Food f) {
        this.name = f.name;
        this.image= f.image;
        this.description = f.description;
        this.popularity = f.popularity;
        this.price = f.price;
    }

    public String getName() {
        return name;
    }

    public URL getImage() {
        return image;
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

    public void setImage(URL image) {
        this.image = image;
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

    @JsonIgnore
    public boolean isValid() {
        return name != null && !name.equals("") &&
                popularity >= 0 &&
                price > 0;
    }
}
