package test.java.ir.loghme.model.command;

import main.java.ir.loghme.exeption.InsufficientCreditException;
import main.java.ir.loghme.model.*;
import main.java.ir.loghme.controller.command.FinalizeOrder;
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
        user.setCredit(Integer.MAX_VALUE);
       Restaurant r1 = new Restaurant("1",null, "1",
                "1",
                new Location(3,4),
                new ArrayList<Food>());

        // add foods to restaurant menu
        r1.addFood(new Food(null,"Fateme","khoshmaze", 100,20000));
        r1.addFood(new Food(null, "Fateme2","khoshmaze2", 1002,10000));

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
    public void testFinalizeOrderSuccess() throws InsufficientCreditException {
        HashMap<String,Integer> actualResult;
        HashMap<String,Integer>  expectedResult = new HashMap<>();
        actualResult = ((Cart)this.finalizeOrder.execute(null)).getFactor();
        expectedResult.put("Fateme", 2);
        expectedResult.put("Fateme2", 1);

        assertEquals(expectedResult.size(), actualResult.size());

        for(Map.Entry<String, Integer> entry : actualResult.entrySet()) {
            assertNotNull(expectedResult.get(entry.getKey()));
            assertEquals(expectedResult.get(entry.getKey()), entry.getValue());
        }
    }
}

