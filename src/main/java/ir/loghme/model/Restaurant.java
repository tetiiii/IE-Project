package main.java.ir.loghme.model;

import main.java.ir.loghme.exeption.FoodNotFoundExeption;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private ArrayList<Food> menu;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Location getLocation() {
        return location;
    }

    public ArrayList<Food> getMenu() {
        return menu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setMenu(ArrayList<Food> menu) {
        this.menu = new ArrayList<Food>();
        for(int i = 0; i < menu.size(); i++) {
            this.menu.add(new Food(menu.get(i)));
        }
    }

    public Food getFood(String foodName) throws FoodNotFoundExeption {
        for (Food f:menu) {
            if(f.getName().toLowerCase().equals(foodName.toLowerCase()))
                return f;
        }
        throw new FoodNotFoundExeption("No food with this name found in menu");
    }

    public void addFood(Food food) throws IllegalArgumentException {
        if(hasFood(food.getName()))
            throw new IllegalArgumentException("the food already exists in the menu");

        this.menu.add(food);
    }

    public boolean hasFood(String foodName) {
        for (Food f:menu) {
            if(f.getName().toLowerCase().equals(foodName.toLowerCase()))
                return true;
        }
        return false;
    }
}
