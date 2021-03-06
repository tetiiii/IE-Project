package main.java.ir.loghme.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.ir.loghme.exeption.FoodNotFoundException;

import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class Restaurant {
    private String id;
//TODO: assume url as an immutable object
    private URL logo;
    private String name;
    private String description;
    private Location location;
    private ArrayList<Food> menu;

    public Restaurant() {
        this.id = null;
        this.logo = null;
        this.name = "";
        this.description = "";
        this.location = new Location();
        this.menu = new ArrayList<>();
    }

    public Restaurant(String id, URL logo, String name, String description, Location location, ArrayList<Food> menu) {
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.description = description;
        this.location = new Location(location);
        this.menu = new ArrayList<>();
        for (Food f : menu)
            this.menu.add(new Food(f));
    }

    public Restaurant(Restaurant that) {
        this(that.id, that.logo, that.name, that.description, that.location, that.menu);
    }

    public String getId() {
        return id;
    }

    public URL getLogo() {
        return logo;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setLogo(URL logo) {
        this.logo = logo;
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
        for (Food food : menu) {
            this.menu.add(new Food(food));
        }
    }

    public Food getFood(String foodName) throws FoodNotFoundException {
        for (Food f:menu) {
            if(f.getName().toLowerCase().equals(foodName.toLowerCase()))
                return f;
        }
        throw new FoodNotFoundException("No food with this name found in menu");
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

    @JsonIgnore
    public boolean isValid() {
        return this.name != null && !this.name.equals("") &&
                this.location != null &&
                this.menu != null;
    }
}
