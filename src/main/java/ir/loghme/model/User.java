package main.java.ir.loghme.model;

import main.java.ir.loghme.exeption.FoodNotFoundException;
import main.java.ir.loghme.exeption.RestaurantNotFoundException;

import java.util.HashMap;

public class User {
    private String name;
    private Cart cart;
    private Location location;


    public User() {
        this.name = "FJ";
        this.cart = new Cart();
        this.location = new Location();
    }

    public void addToCart(String foodName, Restaurant restaurant) throws FoodNotFoundException, RestaurantNotFoundException {
        if (this.cart.isEmpty())
            this.cart.setRestaurant(restaurant);

        try {
          this.cart.addFood(foodName);
        } catch (RestaurantNotFoundException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
//FIXME: change exception to a proper one
    public HashMap<String, Integer> finalizeOrder() throws IllegalArgumentException {
        HashMap<String,Integer> result = this.getCart();
        if(result.size() == 0)
            throw new IllegalArgumentException("cart is empty");
        this.cart.clear();
        return result;
    }

    public HashMap<String, Integer> getCart() {
        HashMap<String, Integer> temp = this.cart.getFactor();
        HashMap<String,Integer> result = new HashMap<>();

        // lambda expression
        // lambda body copies each element of temp into the result
        // k is of type String and v of type Integer
        temp.forEach((k, v) -> {
            result.put(k, v);
        });
        return result;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

}
