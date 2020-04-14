package test.java.ir.loghme.util;

import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Location;
import main.java.ir.loghme.model.Restaurant;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class CommonSetup {

    public static final String restaurantHtml = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>Restaurant</title>\n" +
            "    <style>\n" +
            "        img {\n" +
            "            width: 50px;\n" +
            "            height: 50px;\n" +
            "        }\n" +
            "        li {\n" +
            "            display: flex;\n" +
            "            flex-direction: row;\n" +
            "            padding: 0 0 5px;\n" +
            "        }\n" +
            "        div, form {\n" +
            "            padding: 0 5px\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body><ul>\n" +
            "    <li>id: 1</li>\n" +
            "    <li>name: 1</li>\n" +
            "    <li>location: (3.0, 4.0)</li>\n" +
            "    <li>logo: <img src=\"https://alipanah.me\" alt=\"logo\"></li>\n" +
            "    <li>menu:\n" +
            "        <ul><li>\n" +
            "                <img src=\"https://alipanah.me\" alt=\"logo\">\n" +
            "                <div>f1</div>\n" +
            "                <div>100.0 Toman</div>\n" +
            "                <form action=\"1/addtocart\" method=\"POST\">\n" +
            "                    <button type=\"submit\">addToCart</button>\n" +
            "                    <input type=\"hidden\"  name=\"foodName\" value=\"f1\">                </form>\n" +
            "            </li><li>\n" +
            "                <img src=\"https://alipanah.me\" alt=\"logo\">\n" +
            "                <div>f2</div>\n" +
            "                <div>200.0 Toman</div>\n" +
            "                <form action=\"1/addtocart\" method=\"POST\">\n" +
            "                    <button type=\"submit\">addToCart</button>\n" +
            "                    <input type=\"hidden\"  name=\"foodName\" value=\"f2\">                </form>\n" +
            "            </li></ul>\n" +
            "</li>\n" +
            "</ul>\n" +
            "</body>\n" +
            "</html>";
    public static ArrayList<Restaurant> restaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        URL logo = null;
        try {
            logo = new URL("https://alipanah.me");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // add four restaurants
        Restaurant r1 = new Restaurant("1", logo, "1",
                "1",
                new Location(3, 4),
                new ArrayList<Food>());

        Restaurant r2 = new Restaurant("2", logo, "2",
                "2",
                new Location(1, 2),
                new ArrayList<Food>());

        Restaurant r3 = new Restaurant("3", logo, "3",
                "3",
                new Location(1000, 2000),
                new ArrayList<Food>());

        Restaurant r4 = new Restaurant("4", logo, "4",
                "4",
                new Location(6, 9),
                new ArrayList<Food>());

        // add foods to restaurant menus
        r1.addFood(new Food(logo, "f1", "d1", 10, 100));
        r1.addFood(new Food(logo, "f2", "d2", 20, 200));
        r2.addFood(new Food(null, "Fateme", "khoshmaze", 100, 10000));
        r2.addFood(new Food(null, "Fateme2", "khoshmaze2", 1002, 100002));
        r3.addFood(new Food(null, "Fateme", "khoshmaze", 100, 10000));
        r3.addFood(new Food(null, "Fateme2", "khoshmaze2", 1, 100));

        // add four dummy restaurants to list of restaurants
        restaurants.add(r1);
        restaurants.add(r4);
        restaurants.add(r3);
        restaurants.add(r2);

        return restaurants;
    }
}
