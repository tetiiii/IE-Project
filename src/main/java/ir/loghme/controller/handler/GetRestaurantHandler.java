package main.java.ir.loghme.controller.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import main.java.ir.loghme.controller.WebServer;
import main.java.ir.loghme.controller.command.GetRestaurant;
import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.util.FileManipulator;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import static main.java.ir.loghme.controller.handler.GetRestaurantsHandler.MAX_DISTANCE;

public class GetRestaurantHandler extends GetRestaurant implements Handler {
    private String prefix ;
    private String postfix;
    private ArrayList<User> users;
    public GetRestaurantHandler(ArrayList<Restaurant> restaurants, ArrayList<User> users) throws IOException {
        super(restaurants);
        this.users = users;
        FileManipulator fm = new FileManipulator();
        prefix = fm.readFile(fm.openFileFromResources("restaurant_prefix.html"));
        postfix = fm.readFile(fm.openFileFromResources("restaurant_postfix.html"));
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {

        User user = null;
        for (User u : users) {
            if (u.getName().equals("FJ")) {
                user = u;
                break;
            }
        }
        if (user == null)
            throw new IllegalArgumentException("user not found for command getrestaurants");

        Restaurant restaurant = execute(context.pathParam("id"));
        if( restaurant == null) {
            context.status(WebServer.HTTP_NOT_FOUND);
            context.result("No Restaurant with specified id found");
            return;
        }
        if (restaurant.getLocation().distanceFrom(user.getLocation()) > MAX_DISTANCE) {
            context.status(WebServer.HTTP_UNAUTHORIZED);
            context.result("you dont have permission to access Restaurants that are not in your neighbourhood");
            return;
        }
        String listElements = "";


            listElements += "<ul>\n" +
                    "    <li>id: " + restaurant.getId() + "</li>\n" +
                    "    <li>name: " + restaurant.getName() + "</li>\n" +
                    "    <li>location: " + restaurant.getLocation() + "</li>\n" +
                    "    <li>logo: <img src=\"" + restaurant.getLogo() + "\" alt=\"logo\"></li>\n" +
                    "    <li>menu:\n" +
                    "        <ul>";

       String menuElements = "";
        for (Food f: restaurant.getMenu()) {
            menuElements += "<li>\n" +
                    "                <img src=\"" + f.getImage() + "\" alt=\"logo\">\n" +
                    "                <div>" + f.getName() + "</div>\n" +
                    "                <div>" + f.getPrice() + " Toman</div>\n" +
                    "                <form action=\"\" method=\"POST\">\n" +
                    "                    <button type=\"submit\">addToCart</button>\n" +
                    "                </form>\n" +
                    "            </li>";
        }


        context.html(prefix + listElements + menuElements + postfix);






    }
}
