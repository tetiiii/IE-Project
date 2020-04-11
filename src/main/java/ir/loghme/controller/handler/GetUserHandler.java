package main.java.ir.loghme.controller.handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import main.java.ir.loghme.controller.WebServer;
import main.java.ir.loghme.controller.command.GetUser;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.util.FileManipulator;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

public class GetUserHandler extends GetUser implements Handler {
    private String prefix ;
    private String postfix;
    public GetUserHandler(ArrayList<User> users) throws IOException {
        super(users);
        FileManipulator fm = new FileManipulator();
        prefix = fm.readFile(fm.openFileFromResources("user_prefix.html"));
        postfix = fm.readFile(fm.openFileFromResources("user_postfix.html"));
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        User user = null;
        try {
            user = execute("09118115063");
        } catch (ClassCastException e) {
            context.status(WebServer.HTTP_BADـREQUEST);
            context.result(e.getMessage());
            return;
        }
        String userInfo = " <ul>\n" +
                "        <li>id: " + user.getId() + "</li>\n" +
                "        <li>full name: " + user.getFirstName() + " " + user.getLastName() + "</li>\n" +
                "        <li>phone number: " + user.getPhoneNumber() + "</li>\n" +
                "        <li>email: " + user.getEmail() + "</li>\n" +
                "        <li>credit: " + user.getCredit() + " Toman </li>\n" +
                "        <form action=\"\" method=\"POST\">\n" +
                "            <button type=\"submit\">increase</button>\n" +
                "            <input type=\"text\" name=\"credit\" value=\"\" />\n" +
                "        </form>\n" +
                "    </ul>";
        context.html(prefix + userInfo + postfix);
    }
}
