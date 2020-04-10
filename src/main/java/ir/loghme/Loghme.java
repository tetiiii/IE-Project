package main.java.ir.loghme;
import main.java.ir.loghme.controller.WebServer;
import main.java.ir.loghme.exeption.HttpException;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.util.adapter.HttpAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Loghme {
    public static void main(String[] args) {
        // Initialization
        int RETRY_NUM = 5;
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());

        // Get restaurants info from specific url (external API)
        HttpAdapter adapter = new HttpAdapter();
        for(int i = 0; i <= RETRY_NUM; i++) {
            if(i == RETRY_NUM) {
                System.err.println("couldn't retrieve restaurant information from the API");
                System.exit(1);
            }
            try{
                String restaurantInfoJson = adapter.sendGet(new URL("http://138.197.181.131:8080/restaurants"));
                // deserialize Json String to list of restaurant
                // We used TypeFactory to construct a complex type (list of restaurants)
                ObjectMapper mapper = new ObjectMapper();
                restaurants =  mapper.readValue(restaurantInfoJson,
                        mapper.getTypeFactory().constructCollectionType(List.class, Restaurant.class));
                break;
            } catch (HttpException  | IOException e) {
                System.err.println(e.getMessage());
            }
        }

        // define a Javalin Web Server and set routs and their handlers
        WebServer wb = new WebServer(6969);
        wb.start();
        try {
            // define http routs and their corresponding handlers
            wb.configurePaths(restaurants, users);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}