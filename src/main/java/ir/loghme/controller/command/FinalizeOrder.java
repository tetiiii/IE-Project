package main.java.ir.loghme.controller.command;

import main.java.ir.loghme.exeption.InsufficientCreditException;
import main.java.ir.loghme.model.User;
import java.util.ArrayList;

public class FinalizeOrder implements Command {

    private ArrayList<User> users;
    public FinalizeOrder(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException, InsufficientCreditException {
        if (input != null)
            throw new IllegalArgumentException("order finalization takes no arguments");
        for (User u : users) {
            if(u.getId().equals("1")) {
                return (O) u.finalizeOrder();
            }
        }
        throw new IllegalArgumentException ("no user found with this name!!!!!");
    }

}
