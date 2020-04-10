package main.java.ir.loghme.controller.command;

import main.java.ir.loghme.model.User;

import java.util.ArrayList;

public class GetUser implements Command {
    private ArrayList<User> users;

    public GetUser (ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public <I, O> O execute(I input) throws Exception {
        if (!(input instanceof String))
            throw new ClassCastException("cannot convert input to type String");

        String phoneNumber = ((String) input);

        User user = null;
        for (User u : users) {
            if (u.getPhoneNumber().equals(phoneNumber)) {
                user = u;
                break;
            }
        }
        return (O) user;
    }
}
