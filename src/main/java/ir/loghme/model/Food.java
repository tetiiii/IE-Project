package main.java.ir.loghme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Food {
    private String name;
    private String restaurantName;
    private String description;
    private double popularity;
    private double price;

    public Food() {
        this.name = "";
        this.restaurantName = "";
        this.description = "";
        this.popularity = 0;
        this.price = 0;
    }

    public Food(Food f) {
        this.name = f.name;
        this.restaurantName= f.restaurantName;
        this.description = f.description;
        this.popularity = f.popularity;
        this.price = f.price;
    }

    public String getName() {
        return name;
    }
//FIXME: dont print restaurantName in JSON food
    public String getRestaurantName() {
        return restaurantName;
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

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
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
                restaurantName != null && !restaurantName.equals("") &&
                popularity >= 0 &&
                price > 0;
    }
}
