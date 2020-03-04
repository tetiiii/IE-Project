package main.java.ir.loghme.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {
    private Restaurant restaurant;
    private HashMap<String, Integer> factor;

    public Cart() {
        this.restaurant = null;
        this.factor = new HashMap<>();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void clear() {
        this.factor.clear();
        this.restaurant = null;
    }

    public void addFood(String foodName) throws Exception {
        if (this.isEmpty())
            throw new Exception("No restaurant selected for cart");

        ArrayList<Food> menu = restaurant.getMenu();
        for (Food f: menu) {
            if (f.getName().equals(foodName)){
                factor.put(foodName, factor.getOrDefault(foodName, 0) + 1);
                return;
            }
        }
        throw new Exception("No food with this name found in this restaurant");
    }

    public HashMap<String, Integer> getFactor() {
        return factor;
    }

    public boolean isEmpty() {
        return restaurant == null;
    }

}
