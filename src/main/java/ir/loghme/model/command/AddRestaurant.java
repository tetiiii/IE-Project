package main.java.ir.loghme.model.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Restaurant;

import java.util.ArrayList;

public class AddRestaurant implements Command {
    private ArrayList<Restaurant> restaurants;

    public AddRestaurant(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }


    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException, ClassCastException {
        if (!(input instanceof Restaurant))
            throw new ClassCastException("cannot cast input to type Restaurant");

        Restaurant restaurant = ((Restaurant) input);

        if (!restaurant.isValid())
            throw new IllegalArgumentException("the restaurant information is not valid,plz change it ");

        String restaurantName = restaurant.getName();
        for (Restaurant r: restaurants) {
            // check existence of given restaurantName in the list - case insensitive
            if (r.getName().toLowerCase().equals(restaurantName.toLowerCase()))
                throw new IllegalArgumentException("the restaurant already exists in the list");
        }
        // Add the restaurant to the list
        restaurants.add(restaurant);
        return null;
    }
}