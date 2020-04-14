package test.java.ir.loghme.model.command;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import main.java.ir.loghme.controller.WebServer;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import org.junit.jupiter.api.*;
import test.java.ir.loghme.util.CommonSetup;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


public class JavalinHandlersTest {
    private WebServer wb;
    private final String restaurantHtml = CommonSetup.restaurantHtml;
    private ArrayList<Restaurant> restaurants;
    private ArrayList<User> users = new ArrayList<>();
    @BeforeEach
    public void setup() {
        users.add(new User());
        restaurants = CommonSetup.restaurants();
        // define a Javalin Web Server and set routs and their handlers
        wb = new WebServer(6969);
        wb.start();
        try {
            // define http routs and their corresponding handlers
            wb.configurePaths(restaurants, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetRestaurantHandler() {
        //success
        HttpResponse response = Unirest.get("http://localhost:6969/restaurants/1").asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(restaurantHtml);

        //failure not found
        response = Unirest.get("http://localhost:6969/restaurants/10").asString();
        assertThat(response.getStatus()).isEqualTo(404);

        //failure unauthorized
        response = Unirest.get("http://localhost:6969/restaurants/3").asString();
        assertThat(response.getStatus()).isEqualTo(403);

    }

    @Test
    public void testOrder() {
        Unirest.config().reset();
        Unirest.config().followRedirects(false);
        //success add to cart
        HttpResponse response = Unirest.post("http://localhost:6969/restaurants/1/addtocart").field("foodName","f1").asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("added to your cart successfully =)))))))))");

        //fail:
        response = Unirest.post("http://localhost:6969/restaurants/1/addtocart").field("foodName","f10").asEmpty();
        assertThat(response.getStatus()).isEqualTo(400);

        //success finalize
        response = Unirest.post("http://localhost:6969/cart/finalize").asString();
        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo("your food is on the way =)))))))))))))");

        //fail finalize due to low credit
        for (int i = 0; i < 15; i++)
            Unirest.post("http://localhost:6969/restaurants/1/addtocart").field("foodName","f1").asString();

        response = Unirest.post("http://localhost:6969/cart/finalize").asString();
        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.getBody()).isEqualTo("your credit doesnt cover this order :'(((( ");

    }

    @AfterEach
    public void tearDown() {
        wb.stop();
    }
}
