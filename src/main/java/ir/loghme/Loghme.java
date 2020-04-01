package main.java.ir.loghme;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.ir.loghme.controller.CommandParser;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.command.Command;
import main.java.ir.loghme.model.util.model.Pair;

import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Loghme {
    public static void main(String[] args) {
        // Initialization
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        CommandParser commandParser = new CommandParser(restaurants, users);
        users.add(new User());

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        Pair<Command,Object> command;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.length() == 0) continue;

            try {
                command = commandParser.parse(input);

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                continue;
            }
            try {
                // Pair: (commandObj, JSONInputObj)
                // Key: commandObj, Value: JSONInputObj
                Object output = command.getKey().execute(command.getValue());
                if(output != null)
                      System.out.println((new ObjectMapper()).writeValueAsString(output));

            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                continue;

            } catch (ClassCastException e) {
                System.err.println("command input mismatch");

            } catch (IllegalStateException e) {
                System.err.println("program entered invalid state");
                System.err.println(e.getMessage());
                e.printStackTrace();
                System.exit(1);

            } catch (Exception e) {
                System.err.println("Unknown error");
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}