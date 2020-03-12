package test.java.ir.loghme.model.command;

import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Location;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.command.AddFood;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AddFoodTest {
    private ArrayList<Restaurant> restaurants;
    private AddFood addFood;

    @BeforeEach
    // run before every test
    public void init() {
        Restaurant r1 = new Restaurant("name test1",
                                               "sib mikhore javad",
                                                new Location(2,1),
                                                new ArrayList<Food>());
        Restaurant r2 = new Restaurant("name test2",
                                                "sigar nemkeshe javad:)",
                                                new Location(6,9),
                                                new ArrayList<Food>());
        this.restaurants = new ArrayList<Restaurant>();

        // add two dummy restaurants to list of restaurants
        this.restaurants.add(r1);
        this.restaurants.add(r2);
        this.addFood = new AddFood(restaurants);
    }

    @Test
    // success test
    public void testAddFoodSuccess() {

        // add a dummy food "gheime" to the menu of "name test1" restaurant
        this.addFood.execute("{\n" +
                "   \"name\":\"قیمه\",\n" +
                "   \"restaurantName\":\"name test1\",\n" +
                "   \"description\":\"Maye Rosvayi\",\n" +
                "   \"popularity\":69,\n" +
                "   \"price\":75\n" +
                "}");

        for (Restaurant r : restaurants) {
            if (r.getName().equals("name test1")) {
                ArrayList<Food> menu = r.getMenu();

                // check if exactly one food added to the menu
                assertEquals(1, menu.size(),
                        "the menu size is incorrect");

                // Assert food fields
                Food f1 = menu.get(0);
                assertEquals("قیمه", f1.getName(),
                        "the food name is incorrect");

                assertEquals("name test1", f1.getRestaurantName(),
                        "the restaurant name is incorrect");

                assertEquals("Maye Rosvayi", f1.getDescription(),
                        "the description is incorrect");

                assertEquals(69, f1.getPopularity(),
                        "the popularity is incorrect");

                assertEquals(75, f1.getPrice(),
                        "the price is incorrect");
            } else {
                // food should not be added in the menu of other restaurants
                assertTrue(r.getMenu().isEmpty());
            }

        }
    }

    @Test
    public void testAddFoodFailure() {
        // Invalid JSON
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.addFood.execute("{\n" +
                            "   \"name\":\"قیمه\",\n" +
                            "   \"restaurantName\":\"name test1\",\n" +
                            "   \"description\"\"Maye Rosvayi\",\n" +
                            "   \"popularity\":69,\n" +
                            "   \"price\":75\n" + "}");
                }
        );
        // Invalid popularity
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.addFood.execute("{\n" +
                            "   \"name\":\"قیمه\",\n" +
                            "   \"restaurantName\":\"name test1\",\n" +
                            "   \"description\":\"Maye Rosvayi\",\n" +
                            "   \"popularity\":-100,\n" +
                            "   \"price\":75\n" + "}");
                }
        );
        // Invalid restaurantName
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.addFood.execute("{\n" +
                            "   \"name\":\"قیمه\",\n" +
                            "   \"restaurantName\":\"name testt\",\n" +
                            "   \"description\":\"Maye Rosvayi\",\n" +
                            "   \"popularity\":100,\n" +
                            "   \"price\":75\n" + "}");
                }
        );
        // add a valid food twice
        assertThrows(IllegalArgumentException.class,
                () -> {
                    this.addFood.execute("{\n" +
                            "   \"name\":\"قیمه\",\n" +
                            "   \"restaurantName\":\"name test1\",\n" +
                            "   \"description\":\"Maye Rosvayi\",\n" +
                            "   \"popularity\":100,\n" +
                            "   \"price\":75\n" + "}");
                    this.addFood.execute("{\n" +
                            "   \"name\":\"قیمه\",\n" +
                            "   \"restaurantName\":\"name test1\",\n" +
                            "   \"description\":\"Maye Rosvayi\",\n" +
                            "   \"popularity\":100,\n" +
                            "   \"price\":75\n" + "}");
                }
        );

    }
}
































