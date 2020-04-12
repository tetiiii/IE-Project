package main.java.ir.loghme.model;
import main.java.ir.loghme.exeption.FoodNotFoundException;
import main.java.ir.loghme.exeption.RestaurantNotFoundException;
import java.util.HashMap;

public class Cart {
    private Restaurant restaurant;
    private HashMap<String, Integer> factor;

    public Cart() {
        this.restaurant = null;
        this.factor = new HashMap<>();
    }

    public Cart(Cart cart) {
        this.restaurant = new Restaurant(cart.getRestaurant());
        this.factor = new HashMap<>();

        // lambda expression
        // lambda body copies each element
        // k is of type String and v of type Integer
        cart.getFactor().forEach((k, v) -> {
            this.factor.put(k, v);
        });
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void clear() {
        this.factor.clear();
        this.restaurant = null;
    }

    public void addFood(String foodName) throws FoodNotFoundException, RestaurantNotFoundException {
        if (this.isEmpty())
            throw new RestaurantNotFoundException("No restaurant selected for cart");

        if (!restaurant.hasFood(foodName))
            throw new FoodNotFoundException("No food with this name found in this restaurant");

        factor.put(foodName, factor.getOrDefault(foodName, 0) + 1);

    }

    public HashMap<String, Integer> getFactor() {
        return factor;
    }

    public boolean isEmpty() {
        return restaurant == null;
    }

}
