package main.java.ir.loghme.model.command;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Restaurant;

import java.util.ArrayList;

public class AddFood implements Command {
    private ArrayList<Restaurant> restaurants;

    public AddFood(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public void execute(String input) throws IllegalArgumentException {
        ObjectMapper mapper = new ObjectMapper();

        // JSON String to Java object
        Food food;
        try {
            food = mapper.readValue(input, Food.class);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        if(!food.isValid())
            throw new IllegalArgumentException("the food information is not valid,plz change it bi namoos");

        String restaurantName = food.getRestaurantName();
        for (Restaurant r: restaurants) {
            // check existence of given restaurantName in the list - case insensitive
            if (r.getName().toLowerCase().equals(restaurantName.toLowerCase())) {
                // check existence of foodName in the menu of the restaurant
                if (!r.hasFood(food.getName())){
                    r.addFood(food) ;
                    return;
                }
            }
        }
        throw new IllegalArgumentException("No restaurant found with this name");
    }
}
