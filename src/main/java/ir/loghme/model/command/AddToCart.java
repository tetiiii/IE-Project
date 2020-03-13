package main.java.ir.loghme.model.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.ir.loghme.exeption.FoodNotFoundExeption;
import main.java.ir.loghme.exeption.RestaurantNotFoundExeption;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddToCart implements Command {
    private ArrayList<User> users;
    private ArrayList<Restaurant> restaurants;
    private final int ELEMENTS_NUM = 2;
    private final String FOOD_NAME_KEY = "foodName";
    private final String RESTAURANT_NAME_KEY = "restaurant_name";

    public AddToCart(ArrayList<User> users, ArrayList<Restaurant> restaurants){
        this.users = users;
        this.restaurants = restaurants;
    }

    @Override
    public void execute(String input) throws IllegalArgumentException, IllegalStateException {
        ObjectMapper mapper = new ObjectMapper();

        // JSON String to Java object
        Map order;
        try {
            order = mapper.readValue(input, HashMap.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        if (order.size() != ELEMENTS_NUM)
            throw new IllegalArgumentException("Invalid number of keys in JSON");

        if(!(order.containsKey(FOOD_NAME_KEY) && order.containsKey(RESTAURANT_NAME_KEY)))
            throw new IllegalArgumentException("JSON keys are not valid ");

        if(!(order.get(FOOD_NAME_KEY) instanceof String && order.get(RESTAURANT_NAME_KEY) instanceof String))
            throw new IllegalArgumentException("JSON values are not valid ");

        String foodName = (String) order.get(FOOD_NAME_KEY);
        String restaurantName = (String) order.get(RESTAURANT_NAME_KEY);

        Restaurant restaurant = null;
        for (Restaurant r : restaurants) {
            if (r.getName().toLowerCase().equals(restaurantName.toLowerCase())) {
                restaurant = r;
                break;
            }
        }

        if(restaurant == null)
            throw new IllegalArgumentException("Restaurant name is not valid");

        boolean success = false;
        for (User u : users) {
            if (u.getName().equals("FJ")) {
                try {
                    u.addToCart(foodName, restaurant);
                    success =true;
                } catch (RestaurantNotFoundExeption e) {
                    throw new IllegalStateException("serious problem in list of restaurants", e);
                } catch (FoodNotFoundExeption e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        }

        if (success != true)
            throw new IllegalArgumentException("user not found");

    }
}
