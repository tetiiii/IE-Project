package main.java.ir.loghme.controller.command;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;

import java.util.ArrayList;

public class GetRestaurants implements Command {
    private ArrayList<Restaurant> restaurants;
    private ArrayList<User> users;
    private final double MAX_DISTANCE = 170;

    public GetRestaurants(ArrayList<Restaurant> restaurants, ArrayList<User> users) {
        this.restaurants = restaurants;
        this.users = users;
    }

    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException {
        if (input != null)
            throw new IllegalArgumentException("GetRestaurant takes no arguments");
        User user = null;
        for (User u : users) {
            if (u.getName().equals("FJ")) {
                user = u;
                break;
            }
        }
        if (user == null)
            throw new IllegalArgumentException("user not found for command getrestaurants");

        ArrayList<Restaurant> result = new ArrayList<>();
        for (Restaurant r: restaurants) {
            if (r.getLocation().distanceFrom(user.getLocation()) <= MAX_DISTANCE) {
                result.add(new Restaurant(r));
            }
        }

        return (O) result;
    }
}
