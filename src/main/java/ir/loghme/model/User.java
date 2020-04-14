package main.java.ir.loghme.model;

import main.java.ir.loghme.exeption.FoodNotFoundException;
import main.java.ir.loghme.exeption.InsufficientCreditException;
import main.java.ir.loghme.exeption.RestaurantNotFoundException;

import javax.naming.InsufficientResourcesException;
import java.util.HashMap;

public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int credit;
    private Cart cart;
    private Location location;


    public User() {
        this.id = "1";
        this.firstName = "FJ";
        this.lastName = "JF";
        this.phoneNumber = "09118115063";
        this.email = "fateme.k128@gmail.com";
        this.credit = 1000;
        this.cart = new Cart();
        this.location = new Location();
    }

    public void addToCart(String foodName, Restaurant restaurant) throws FoodNotFoundException, RestaurantNotFoundException, IllegalArgumentException {
        if (this.cart.isEmpty())
            this.cart.setRestaurant(restaurant);
        else if (!restaurant.getId().equals(this.cart.getRestaurant().getId())){
            throw new IllegalArgumentException("you have unfinished order from " + this.cart.getRestaurant().getName());
        }



        try {
          this.cart.addFood(foodName);
        } catch (RestaurantNotFoundException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }
//FIXME: change exception to a proper one
    public Cart finalizeOrder() throws IllegalArgumentException, InsufficientCreditException {
        Cart temp = this.getCart();
        if(temp == null)
            return null;

        HashMap<String, Integer> tempFactor = temp.getFactor();
        if(tempFactor.size() == 0)
            throw new IllegalArgumentException("cart is empty");

        Restaurant restaurant =  temp.getRestaurant();
        var lambdaContext = new Object() {
            double priceSum = 0;
        };
        tempFactor.forEach((k, v) -> {
            try {
                lambdaContext.priceSum += (restaurant.getFood(k).getPrice()) * v;
            } catch (FoodNotFoundException e) {
                System.err.println(e.getMessage());
            }
        });
        if (lambdaContext.priceSum > credit){
            throw new InsufficientCreditException("your credit doesnt cover this order :'(((( ");
        }
        credit -= lambdaContext.priceSum;
        this.cart.clear();
        return temp;
    }

    public Cart getCart() {
        if(cart.getRestaurant() != null)
            return new Cart(cart);
        return null;
    }

    public String getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public int getCredit() {
        return credit;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
