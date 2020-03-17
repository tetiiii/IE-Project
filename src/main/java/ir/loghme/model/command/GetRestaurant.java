package main.java.ir.loghme.model.command;

import main.java.ir.loghme.model.Restaurant;

import java.util.ArrayList;

public class GetRestaurant implements Command {
    private ArrayList<Restaurant> restaurants;

    public GetRestaurant(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public <I, O> O execute(I input) throws ClassCastException {
        if(!(input instanceof String))
            throw new ClassCastException("Invalid type for GetRestaurant input");
        for (Restaurant r : restaurants) {
            if (r.getName().toLowerCase().equals(((String) input).toLowerCase()))
                return (O) new Restaurant(r);
        }
        throw new  IllegalArgumentException("No Restaurant found with this name");
    }
}
