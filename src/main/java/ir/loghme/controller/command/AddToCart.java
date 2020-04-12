package main.java.ir.loghme.controller.command;

import main.java.ir.loghme.exeption.FoodNotFoundException;
import main.java.ir.loghme.exeption.RestaurantNotFoundException;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;

import java.util.ArrayList;
import java.util.Map;

public class AddToCart implements Command {
    private ArrayList<User> users;
    private ArrayList<Restaurant> restaurants;
    private final int ELEMENTS_NUM = 2;
    public final String FOOD_NAME_KEY = "foodName";
    public final String RESTAURANT_ID_KEY = "restaurantId";

    public AddToCart(ArrayList<User> users, ArrayList<Restaurant> restaurants){
        this.users = users;
        this.restaurants = restaurants;
    }

    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException, ClassCastException, IllegalStateException {
        if (!(input instanceof Map))
            throw new ClassCastException("cannot convert input to type Map");

        Map order = ((Map) input);

        if (order.size() != ELEMENTS_NUM)
            throw new IllegalArgumentException("Invalid number of keys in JSON");

        if(!(order.containsKey(FOOD_NAME_KEY) && order.containsKey(RESTAURANT_ID_KEY)))
            throw new IllegalArgumentException("JSON keys are not valid ");

        if(!(order.get(FOOD_NAME_KEY) instanceof String && order.get(RESTAURANT_ID_KEY) instanceof String))
            throw new IllegalArgumentException("JSON values are not valid ");

        String foodName = (String) order.get(FOOD_NAME_KEY);
        String restaurantId = (String) order.get(RESTAURANT_ID_KEY);

        Restaurant restaurant = null;
        for (Restaurant r : restaurants) {
            if (r.getId().equals(restaurantId)) {
                restaurant = r;
                break;
            }
        }

        if(restaurant == null)
            throw new IllegalArgumentException("Restaurant id is not valid");

        for (User u : users) {
            if (u.getId().equals("1")) {
                try {
                    u.addToCart(foodName, restaurant);
                    return null;
                } catch (RestaurantNotFoundException e) {
                    throw new IllegalStateException("serious problem in list of restaurants", e);
                } catch (FoodNotFoundException e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        }
        throw new IllegalArgumentException("user not found");
    }
}
