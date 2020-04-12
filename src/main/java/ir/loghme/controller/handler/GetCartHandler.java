package main.java.ir.loghme.controller.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import main.java.ir.loghme.controller.WebServer;
import main.java.ir.loghme.controller.command.GetCart;
import main.java.ir.loghme.model.Cart;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.util.FileManipulator;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GetCartHandler extends GetCart implements Handler {
    private String prefix ;
    private String postfix;
    public GetCartHandler(ArrayList<User> users) throws IOException {
        super(users);
        FileManipulator fm = new FileManipulator();
        prefix = fm.readFile(fm.openFileFromResources("cart_prefix.html"));
        postfix = fm.readFile(fm.openFileFromResources("cart_postfix.html"));
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        Cart cart;
        try {
           cart = execute(null);
        } catch (IllegalArgumentException e) {
           context.status(WebServer.HTTP_BADـREQUEST);
           context.result(e.getMessage());
           return;
        }
        if(cart == null) {
            context.result("your cart is empty");
            return;
        }

        String order = "<div>" + cart.getRestaurant().getName() + "</div>\n" + "<ul>\n";
        for(Map.Entry<String, Integer> entry : cart.getFactor().entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            order += "<li>" + key + ": " + value + "</li>\n";
        }
        order += "</ul>";
        context.html(prefix + order + postfix);
    }
}
