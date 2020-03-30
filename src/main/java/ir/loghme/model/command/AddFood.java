package main.java.ir.loghme.model.command;
import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.util.FoodInput;

import java.util.ArrayList;

public class AddFood implements Command {
    private ArrayList<Restaurant> restaurants;

    public AddFood(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException, ClassCastException {

        if (!(input instanceof FoodInput)) {
            throw new ClassCastException("cannot cast input to Food type");
        }

        FoodInput foodInput = (FoodInput) input;
        Food food = new Food(foodInput.getImage(),foodInput.getName(),
                foodInput.getDescription(), foodInput.getPopularity(), foodInput.getPrice());
        String restaurantName = foodInput.getRestaurantName();

        if (!food.isValid())
            throw new IllegalArgumentException("the food information is not valid,plz change it");

        for (Restaurant r: restaurants) {
            // check existence of given restaurantName in the list - case insensitive
            if (r.getName().toLowerCase().equals(restaurantName.toLowerCase())) {
                // check existence of foodName in the menu of the restaurant
                if (!r.hasFood(food.getName())) {
                    r.addFood(food);
                    return null;
                } else {
                    throw new IllegalArgumentException("the food name already exists");
                }
            }
        }
        throw new IllegalArgumentException("No restaurant found with this name");
    }
}
