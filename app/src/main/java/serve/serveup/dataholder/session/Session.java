package serve.serveup.dataholder.session;

import java.util.ArrayList;
import serve.serveup.dataholder.MealInfo;
import serve.serveup.dataholder.RestaurantInfo;

/*
    @author: urban.jagodic
*/

public class Session {

    private String currentUser;
    private RestaurantInfo currentRestaurant;
    private ArrayList<MealInfo> orderedMeals;
    private float overAllPrice;

    public Session() {
        orderedMeals = new ArrayList<>();
        currentUser = "default_user";
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
    public String getCurrentUser() {
        return currentUser;
    }
    public void clearCurrentUser() {
        this.currentUser = "default_user";
    }

    public void setCurrentRestaurant(RestaurantInfo currentRestaurant) {
        this.currentRestaurant = currentRestaurant;
    }
    public void clearCurrentRestaurant() {
        this.currentRestaurant = null;
    }
    public RestaurantInfo getCurrentRestaurant() {
        return currentRestaurant;
    }

    public void addCurrentMeal(MealInfo meal) {
        this.orderedMeals.add(meal);
    }
    public void deleteMeal(MealInfo meal) {
        this.orderedMeals.remove(meal);
    }
    public ArrayList<MealInfo> getAllMeals() {
        return this.orderedMeals;
    }

    public float getOverAllPrice() {
        for (MealInfo meal : this.getAllMeals())
            overAllPrice += meal.getCena() * meal.getKolicina();
        return overAllPrice;
    }
}
