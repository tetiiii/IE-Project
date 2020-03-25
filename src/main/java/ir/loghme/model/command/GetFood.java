package main.java.ir.loghme.model.command;

import main.java.ir.loghme.exeption.FoodNotFoundExeption;
import main.java.ir.loghme.exeption.RestaurantNotFoundExeption;
import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;

import java.util.ArrayList;
import java.util.Map;

public class GetFood implements Command {
    private ArrayList<Restaurant> restaurants;
    private final int ELEMENTS_NUM = 2;
    private final String FOOD_NAME_KEY = "foodName";
    private final String RESTAURANT_NAME_KEY = "restaurantName";

    public GetFood(ArrayList<Restaurant> restaurants){
        this.restaurants = restaurants;
    }

    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException, ClassCastException, IllegalStateException {
        if (!(input instanceof Map))
            throw new ClassCastException("cannot convert input to type Map");

        Map foodInfo = ((Map) input);

        if (foodInfo.size() != ELEMENTS_NUM)
            throw new IllegalArgumentException("Invalid number of keys in JSON");

        if(!(foodInfo.containsKey(FOOD_NAME_KEY) && foodInfo.containsKey(RESTAURANT_NAME_KEY)))
            throw new IllegalArgumentException("JSON keys are not valid ");

        if(!(foodInfo.get(FOOD_NAME_KEY) instanceof String && foodInfo.get(RESTAURANT_NAME_KEY) instanceof String))
            throw new IllegalArgumentException("JSON values are not valid ");

        String foodName = (String) foodInfo.get(FOOD_NAME_KEY);
        String restaurantName = (String) foodInfo.get(RESTAURANT_NAME_KEY);

        Restaurant restaurant = null;
        for (Restaurant r : restaurants) {
            if (r.getName().toLowerCase().equals(restaurantName.toLowerCase())) {
                restaurant = r;
                break;
            }
        }

        if(restaurant == null)
            throw new IllegalArgumentException("Restaurant name is not valid");

        ArrayList<Food> menu = restaurant.getMenu();
         for (Food f : menu) {
            if (f.getName().toLowerCase().equals(foodName.toLowerCase())) {
                return (O) f;
            }
        }
        throw new IllegalArgumentException("food not found");
    }
}
