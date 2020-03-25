package main.java.ir.loghme.model.command;

import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Restaurant;

import java.util.ArrayList;
import java.util.Map;

public class GetRestaurant implements Command {
    private ArrayList<Restaurant> restaurants;
    private final int ELEMENTS_NUM = 1;
    private final String RESTAURANT_NAME_KEY = "name";

    public GetRestaurant(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public <I, O> O execute(I input) throws ClassCastException {
        if (!(input instanceof Map))
            throw new ClassCastException("cannot convert input to type Map");

        Map restaurantInfo = ((Map) input);

        if (restaurantInfo.size() != ELEMENTS_NUM)
            throw new IllegalArgumentException("Invalid number of keys in JSON");

        if (!(restaurantInfo.containsKey(RESTAURANT_NAME_KEY)))
            throw new IllegalArgumentException("JSON keys are not valid ");

        if (!(restaurantInfo.get(RESTAURANT_NAME_KEY) instanceof String))
            throw new IllegalArgumentException("JSON values are not valid ");

        String restaurantName = (String) restaurantInfo.get(RESTAURANT_NAME_KEY);
        Restaurant restaurant = null;
        for (Restaurant r : restaurants) {
            if (r.getName().toLowerCase().equals(restaurantName.toLowerCase())) {
                restaurant = r;
                break;
            }
        }

        if (restaurant == null)
            throw new IllegalArgumentException("No Restaurant found with this name");

        return (O) restaurant;
    }
}
