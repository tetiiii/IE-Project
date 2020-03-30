package test.java.ir.loghme.model.command;

import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Location;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.command.FinalizeOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FinalizeOrderTest {
    private FinalizeOrder finalizeOrder;

    @BeforeEach
    // run before every test
    public void init() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        users.add(user);

        Restaurant r1 = new Restaurant("1",
                "1",
                new Location(3,4),
                new ArrayList<Food>());

        // add foods to restaurant menu
        r1.addFood(new Food("Fateme","1","khoshmaze", 100,10000));
        r1.addFood(new Food("Fateme2","1","khoshmaze2", 1002,100002));

        try {
            user.addToCart("Fateme", r1);
            user.addToCart("Fateme", r1);
            user.addToCart("Fateme2", r1);
        } catch (Exception e){
            fail(e.getMessage());
        }

        finalizeOrder = new FinalizeOrder(users);
    }

    @Test
    // success test
    public void testFinalizeOrderSuccess() {
        HashMap<String,Integer> actualResult;
        HashMap<String,Integer>  expectedResult = new HashMap<>();
        actualResult = this.finalizeOrder.execute(null);
        expectedResult.put("Fateme", 2);
        expectedResult.put("Fateme2", 1);

        assertEquals(expectedResult.size(), actualResult.size());

        for(Map.Entry<String, Integer> entry : actualResult.entrySet()) {
            assertNotNull(expectedResult.get(entry.getKey()));
            assertEquals(expectedResult.get(entry.getKey()), entry.getValue());
        }
    }
}
