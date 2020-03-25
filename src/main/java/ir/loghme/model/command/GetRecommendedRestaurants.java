package main.java.ir.loghme.model.command;

import main.java.ir.loghme.model.Food;
import main.java.ir.loghme.model.Location;
import main.java.ir.loghme.model.Restaurant;
import main.java.ir.loghme.model.User;
import main.java.ir.loghme.model.util.Pair;

import java.util.ArrayList;

public class GetRecommendedRestaurants implements Command {
    private ArrayList<Restaurant> restaurants;
    private ArrayList<User> users;

    public GetRecommendedRestaurants(ArrayList<Restaurant> restaurants, ArrayList<User> users) {
        this.restaurants = restaurants;
        this.users = users;
    }

    @Override
    public <I, O> O execute(I input) throws IllegalArgumentException {
       ArrayList<Pair<Restaurant, Double>> topThree = new ArrayList<>();
        Location userLocation = null;
        for (User u:users)
            if(u.getName().toLowerCase().equals("FJ".toLowerCase()))
                userLocation  = u.getLocation();

            if(userLocation == null)
                throw new IllegalArgumentException("User does not exist");

        for (Restaurant r : restaurants) {
            ArrayList<Food> menu = r.getMenu();
            double sum = 0;
            double avg = 0;
            double popularity;
            double distance;

            for (Food f : menu)
                sum += f.getPopularity();
            avg = sum / menu.size();

            distance = userLocation.distanceFrom(r.getLocation());
            popularity = distance * avg;

            if (topThree.size() < 3){
                topThree.add(new Pair<>(new Restaurant(r), popularity));
                continue;
            }

            int minIdx = 0;
            for (int i = 1; i < topThree.size(); i++) {
                if(topThree.get(minIdx).getValue() > topThree.get(i).getValue())
                    minIdx = i;
            }
            if (popularity > topThree.get(minIdx).getValue()) {
                topThree.remove(minIdx);
                topThree.add(new Pair<>(new Restaurant(r),popularity));
            }
        }

        sort(topThree);
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i < topThree.size(); i++)
            result.add(topThree.get(i).getKey().getName());


        return (O) result;
    }

    private void sort(ArrayList<Pair<Restaurant, Double>> topThree) {
        // bubble sort for topThree
        for(int i = 0; i < topThree.size(); i++) {
            int maxIdx = i;
            for(int j = i; j < topThree.size(); j++) {
                if( topThree.get(maxIdx).getValue() < topThree.get(j).getValue())
                    maxIdx = j;
            }
            Pair<Restaurant, Double> temp = topThree.get(i);
            topThree.set(i, topThree.get(maxIdx));
            topThree.set(maxIdx, temp);
        }
    }
}
