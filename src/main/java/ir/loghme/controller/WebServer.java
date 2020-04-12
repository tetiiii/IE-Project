package main.java.ir.loghme.controller;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import main.java.ir.loghme.controller.handler.*;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;

import java.io.IOException;
import java.util.ArrayList;

public class WebServer {
    private int port;
    private Javalin app;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_UNAUTHORIZED = 403;
    public static final int HTTP_BADـREQUEST = 400;

    public WebServer(int port) {
        this.port = port;
    }

    public void start() {
        this.app = Javalin.create().start(port);
    }

    public void get(String path, Handler handler) {
        this.app.get(path, handler);
    }

    public void post(String path, Handler handler) {
        this.app.post(path, handler);
    }

    public void configurePaths(ArrayList<Restaurant> restaurants, ArrayList<User> users) throws IOException {
        this.get("/restaurants", new GetRestaurantsHandler(restaurants, users));
        this.get("/restaurants/:id", new GetRestaurantHandler(restaurants, users));
        this.get("/user", new GetUserHandler(users));
        this.post("/user/credit", new IncreaseCreditHandler(users));
        this.post("/restaurants/:id/addtocart", new AddToCartHandler(users, restaurants) );
    }

}
