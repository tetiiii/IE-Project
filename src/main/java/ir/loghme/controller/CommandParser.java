package main.java.ir.loghme.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.command.*;
import main.java.ir.loghme.model.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandParser {

    private HashMap<String,Command> commands;
    private final int MAX_COMMAND_SIZE = 2;

    public CommandParser(ArrayList<Restaurant> restaurants, ArrayList<User> users) {
        this.commands = new HashMap<>();
        commands.put("addFood", new AddFood(restaurants));
        commands.put("addRestaurant", new AddRestaurant(restaurants));
        commands.put("addToCart", new AddToCart(users, restaurants));
        commands.put("finalizeOrder", new FinalizeOrder(users));
        commands.put("getCart", new GetCart(users));
        commands.put("getFood", new GetFood(restaurants));
        commands.put("getRecommendedRestaurants", new GetRecommendedRestaurants(restaurants, users));
        commands.put("getRestaurant", new GetRestaurant(restaurants));
        commands.put("getRestaurants", new GetRestaurants(restaurants));
    }

    public Pair<Command, Object> parse(String s) throws IllegalArgumentException {
        String[] args =  s.split(" ", MAX_COMMAND_SIZE);
        Class<?> inputType;
        switch (args[0]) {
            // JSON String to Java object
            case "addFood":
                inputType = Food.class;
                break;
            case "addRestaurant":
                inputType = Restaurant.class;
                break;
            case "addToCart":
            case "getFood":
            case "getRestaurant":
                inputType = HashMap.class;
                break;
            default:
                inputType = null;
        }

        Object input = null;
        ObjectMapper mapper = new ObjectMapper();
        if(inputType != null)
            try {
                input = mapper.readValue(args[1], inputType);
            } catch (Exception e) {
                throw new IllegalArgumentException(e.getMessage());
            }
 
       Command command = commands.get(args[0]);
       if(command == null)
           throw new IllegalArgumentException("command " + args[0] + " not found" );

       return new Pair<>(command, input);
    }

}
