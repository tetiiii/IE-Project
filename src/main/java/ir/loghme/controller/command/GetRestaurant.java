package main.java.ir.loghme.controller.command;

import main.java.ir.loghme.model.Restaurant;

import java.util.ArrayList;

public class GetRestaurant implements Command {
    private ArrayList<Restaurant> restaurants;

    public GetRestaurant(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public <I, O> O execute(I input) throws ClassCastException {
        if (!(input instanceof String))
            throw new ClassCastException("cannot convert input to type String");

        String id = ((String) input);

        Restaurant restaurant = null;
        for (Restaurant r : restaurants) {
            if (r.getId().equals(id)) {
                restaurant = r;
                break;
            }
        }
        return (O) restaurant;
    }
}
