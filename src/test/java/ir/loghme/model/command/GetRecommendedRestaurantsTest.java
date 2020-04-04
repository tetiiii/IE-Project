package test.java.ir.loghme.model.command;

import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Location;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.controller.command.GetRecommendedRestaurants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class GetRecommendedRestaurantsTest {
    private GetRecommendedRestaurants getRecommendedRestaurants;

    @BeforeEach
    // run before every test
    public void init() {
        ArrayList<Restaurant> restaurants;
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());

        // add four restaurants
        Restaurant r1 = new Restaurant(null,null,"1",
                "1",
                new Location(3,4),
                new ArrayList<Food>());

        Restaurant r2 = new Restaurant(null,null,"2",
                "2",
                new Location(1,2),
                new ArrayList<Food>());

        Restaurant r3 = new Restaurant(null,null,"3",
                "3",
                new Location(1,2),
                new ArrayList<Food>());

        Restaurant r4 = new Restaurant(null,null,"4",
                "4",
                new Location(6,9),
                new ArrayList<Food>());

        // add foods to restaurant menus
        r1.addFood(new Food(null,"Fateme","khoshmaze", 100,10000));
        r1.addFood(new Food(null,"Fateme2","khoshmaze2", 1002,100002));
        r2.addFood(new Food(null,"Fateme","khoshmaze", 100,10000));
        r2.addFood(new Food(null,"Fateme2","khoshmaze2", 1002,100002));
        r3.addFood(new Food(null,"Fateme","khoshmaze", 100,10000));
        r3.addFood(new Food(null,"Fateme2","khoshmaze2", 1,100));

        restaurants = new ArrayList<Restaurant>();

        // add four dummy restaurants to list of restaurants
        restaurants.add(r1);
        restaurants.add(r4);
        restaurants.add(r3);
        restaurants.add(r2);
        this.getRecommendedRestaurants = new GetRecommendedRestaurants(restaurants, users);
    }

    @Test
    // success test
    public void testGetRecommendedRestaurantsSuccess() {
        ArrayList<String> actualResult;
        ArrayList<String> expectedResult = new ArrayList<>();
        actualResult = this.getRecommendedRestaurants.execute(null);
        expectedResult.add("1");
        expectedResult.add("2");
        expectedResult.add("3");

        assertArrayEquals(expectedResult.toArray(), actualResult.toArray());

    }
}

