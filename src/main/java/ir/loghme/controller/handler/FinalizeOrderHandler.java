package main.java.ir.loghme.controller.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import main.java.ir.loghme.controller.WebServer;
import main.java.ir.loghme.controller.command.FinalizeOrder;
import main.java.ir.loghme.exeption.InsufficientCreditException;
import main.java.ir.loghme.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FinalizeOrderHandler extends FinalizeOrder implements Handler{

    public FinalizeOrderHandler(ArrayList<User> users) {
        super(users);
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        try {
           if(execute((null)) == null) {
               context.status(WebServer.HTTP_BADـREQUEST);
               context.result("No food in the cart");
               return;
           }
        } catch (IllegalArgumentException | InsufficientCreditException e){
            context.status(WebServer.HTTP_BADـREQUEST);
            context.result(e.getMessage());
            return;
        }
        context.result("your food is on the way =)))))))))))))");
    }
}
