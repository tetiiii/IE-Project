package main.java.ir.loghme.model;

import main.java.ir.loghme.exeption.FoodNotFoundExeption;

import java.util.ArrayList;

public class Restaurant {
    private String name;
    private String description;
    private Location location;
    private ArrayList<Food> menu;

    public Restaurant() {
        this.name = "";
        this.description = "";
        this.location = new Location();
        this.menu = new ArrayList<>();
    }

    public Restaurant(String name, String description, Location location, ArrayList<Food> menu) {
        this.name = name;
        this.description = description;
        this.location = new Location(location);
        this.menu = new ArrayList<>();
        for (Food f : menu)
            this.menu.add(new Food(f));
    }

    public Restaurant(Restaurant that) {
        this(that.name, that.description, that.location, that.menu);
    }

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

    public boolean isValid() {
        return this.name != null && !this.name.equals("") &&
                this.location != null &&
                this.menu != null;
    }
}
