package main.java.ir.loghme.controller.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import main.java.ir.loghme.controller.command.GetRestaurants;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.util.FileManipulator;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class GetRestaurantsHandler extends GetRestaurants implements Handler {
    private String prefix ;
    private String postfix;
    private ArrayList<User> users;
    public static final double MAX_DISTANCE = 170;
    public GetRestaurantsHandler(ArrayList<Restaurant> restaurants, ArrayList<User> users) throws IOException {
        super(restaurants);
        this.users = users;
        FileManipulator fm = new FileManipulator();
        prefix = fm.readFile(fm.openFileFromResources("restaurants_prefix.html"));
        postfix = fm.readFile(fm.openFileFromResources("restaurants_postfix.html"));
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        ArrayList<Restaurant> restaurants = this.execute(null);
        User user = null;
        for (User u : users) {
            if (u.getName().equals("FJ")) {
                user = u;
                break;
            }
        }
        if (user == null)
            throw new IllegalArgumentException("user not found for command getrestaurants");

        ArrayList<Restaurant> result = new ArrayList<>();
        for (Restaurant r: restaurants) {
            if (r.getLocation().distanceFrom(user.getLocation()) <= MAX_DISTANCE) {
                result.add(new Restaurant(r));
            }
        }
        String tableRows = "";
        for (Restaurant r: result) {
            tableRows += "    <tr>\n" +
                    "        <td>" + r.getId() + "</td>\n" +
                    "        <td><img class=\"logo\" src=\"" + r.getLogo().toString() +  "\" alt=\"logo\"></td>\n" +
                    "        <td>" + r.getName() + "</td>\n" +
                    "        <td>" + r.getDescription() + "</td>\n" +
                    "    </tr>\n" ;
        }
        context.html(prefix + tableRows + postfix);
    }
}
