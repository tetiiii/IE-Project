package main.java.ir.loghme.model.util;

import main.java.ir.loghme.model.Food;

import java.net.URL;

public class FoodInput extends Food {
    private String restaurantName;

    public FoodInput(String restaurantName, URL image, String name, String description, double popularity, double price) {
        super(image, name, description, popularity, price);
        this.restaurantName = restaurantName;
    }

    public FoodInput() {
        super();
        this.restaurantName = "";
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
