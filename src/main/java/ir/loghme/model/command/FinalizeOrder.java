package main.java.ir.loghme.model.command;

import main.java.ir.loghme.model.User;
import java.util.ArrayList;
import java.util.HashMap;

public class FinalizeOrder implements Command {

    private ArrayList<User> users;
    public FinalizeOrder(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public <I, O> O execute(I input) throws ClassCastException, IllegalStateException {
        if (input != null)
            throw new IllegalArgumentException("order finalization takes no arguments");
        for (User u : users) {
            if(u.getName().equals("FJ")) {
                return (O) u.finalizeOrder();
            }
        }
        throw new IllegalArgumentException ("no user found with this name");
    }

}
