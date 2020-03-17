package main.java.ir.loghme.model.command;

import main.java.ir.loghme.model.Restaurant;

import java.util.ArrayList;

public class GetRestaurants implements Command {
    private ArrayList<Restaurant> restaurants;

    public GetRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException {
        if (input != null)
            throw new IllegalArgumentException("GetRestaurant takes no arguments");
        return (O) restaurants;
    }
}
