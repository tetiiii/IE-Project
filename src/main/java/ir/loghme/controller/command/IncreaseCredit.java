package main.java.ir.loghme.controller.command;

import main.java.ir.loghme.model.User;

import java.util.ArrayList;

public class IncreaseCredit implements Command {
    private ArrayList<User> users;

    public IncreaseCredit(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public <I, O> O execute(I input) throws Exception {
        if (!(input instanceof Integer))
            throw new ClassCastException("can not convert input to Integer");
            
        int plusCredit = (int) input;
        if(plusCredit <= 0)
            throw new IllegalArgumentException("the increase value must be more than zero");
     
        User user = null;
        for (User u : users) {
            if (u.getId().equals("1")) {
                user = u;
                break;
            }
        }
        if (user == null)
            throw new IllegalArgumentException("user not found for command IncreaseCredit");
        
        user.setCredit(plusCredit + user.getCredit());
        return null;
    }
}
