package main.java.ir.loghme.controller.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import main.java.ir.loghme.controller.WebServer;
import main.java.ir.loghme.controller.command.AddToCart;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class AddToCartHandler extends AddToCart implements Handler {
    public AddToCartHandler(ArrayList<User> users, ArrayList<Restaurant> restaurants) {
        super(users, restaurants);
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        String id = context.pathParam("id");
        String foodName = context.formParam("foodName");
        if(foodName == null || id == null ) {
            context.status(WebServer.HTTP_BADـREQUEST);
            context.result("Invalid input");
            return;
        }

        HashMap<String, String> order = new HashMap<>();
        order.put(FOOD_NAME_KEY, foodName);
        order.put(RESTAURANT_ID_KEY, id);

        try {
            execute(order);
        } catch (IllegalArgumentException | ClassCastException e) {
            context.status(WebServer.HTTP_BADـREQUEST);
            context.html(e.getMessage()).res.setCharacterEncoding("utf-8");
            return;
        }
        context.redirect("/restaurants/" + id);
    }
}
