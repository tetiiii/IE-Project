package main.java.ir.loghme.controller.command;

import main.java.ir.loghme.model.User;
import java.util.ArrayList;

public class GetCart implements Command {

    private ArrayList<User> users;
    public GetCart(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException {
        if (input != null)
            throw new IllegalArgumentException("get cart takes no arguments");
        for (User u : users) {
            if(u.getId().equals("1")) {
                return (O) u.getCart();
            }
        }
        throw new IllegalArgumentException ("no user found with this name");
    }

}
