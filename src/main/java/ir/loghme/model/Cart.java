package main.java.ir.loghme.model;
import main.java.ir.loghme.exeption.FoodNotFoundExeption;
import main.java.ir.loghme.exeption.RestaurantNotFoundExeption;
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

    public void addFood(String foodName) throws FoodNotFoundExeption, RestaurantNotFoundExeption {
        if (this.isEmpty())
            throw new RestaurantNotFoundExeption("No restaurant selected for cart");

        if (!restaurant.hasFood(foodName))
            throw new FoodNotFoundExeption("No food with this name found in this restaurant");

        factor.put(foodName, factor.getOrDefault(foodName, 0) + 1);

    }

    public HashMap<String, Integer> getFactor() {
        return factor;
    }

    public boolean isEmpty() {
        return restaurant == null;
    }

}
