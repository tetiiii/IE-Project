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
    public GetRestaurantsHandler(ArrayList<Restaurant> restaurants, ArrayList<User> users) throws IOException {
        super(restaurants, users);
        FileManipulator fm = new FileManipulator();
        prefix = fm.readFile(fm.openFileFromResources("restaurants_prefix.html"));
        postfix = fm.readFile(fm.openFileFromResources("restaurants_postfix.html"));
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        ArrayList<Restaurant> result = this.execute(null);
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
