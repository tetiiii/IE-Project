package main.java.ir.loghme.controller.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import main.java.ir.loghme.controller.WebServer;
import main.java.ir.loghme.controller.command.IncreaseCredit;
import main.java.ir.loghme.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IncreaseCreditHandler extends IncreaseCredit implements Handler {


    public IncreaseCreditHandler(ArrayList<User> users) {
        super(users);
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        String creditStr = context.formParam("credit");
        if(creditStr == null) {
            context.status(WebServer.HTTP_BADـREQUEST);
            context.result("the credit field is empty");
            return;
        }

        try {
            int credit = Integer.parseInt(creditStr);
            this.execute(credit);
        } catch (NumberFormatException n) {
            context.status(WebServer.HTTP_BADـREQUEST);
            context.result("the credit field must be integer");
            return;
        }  catch (IllegalArgumentException | ClassCastException e) {
            context.status(WebServer.HTTP_BADـREQUEST);
            context.result(e.getMessage());
            return;
        }

        context.redirect("/user");
    }
}
