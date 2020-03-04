package main.java.ir.loghme.model;

import java.util.ArrayList;

public class Cart {
    private Restaurant restaurant;
    private ArrayList<Food> foods;

    public Cart() {
        this.restaurant = null;
        this.foods = new ArrayList<Food>();
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void clear() {
        this.foods.clear();
        this.restaurant = null;
    }

    public void addFood(String foodName) throws Exception {
        if (this.isEmpty())
            throw new Exception("No restaurant selected for cart");

        ArrayList<Food> menu = restaurant.getMenu();
        for (Food f: menu) {
            if (f.getName().equals(foodName)){
                foods.add(new Food(f));
                return;
             }
        }
        throw new Exception("No food with this name found in this restaurant");
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public boolean isEmpty() {
        return restaurant == null;
    }

}
