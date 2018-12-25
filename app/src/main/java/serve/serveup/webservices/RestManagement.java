package serve.serveup.webservices;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import serve.serveup.dataholder.MealInfo;
import serve.serveup.dataholder.RestaurantInfo;
import serve.serveup.dataholder.UserID;
import serve.serveup.dataholder.UserLoginStatus;

public class RestManagement {

    private static String baseURL = "https://serveup-backend.herokuapp.com/api/";

    private static Retrofit myRetrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build();


    public static Call<UserLoginStatus> getLoginStatusCall(String id_uporabnik) {
        return myRetrofit
                .create(LoginStatus.class)
                .getLoginStatus(id_uporabnik);
    }

    public static Call<List<UserID>> getAllUsers() {
        return myRetrofit
                .create(GetUsers.class)
                .getAllUsers();
    }

    public static Call<List<RestaurantInfo>> getAllRestaurants(String location) {
        return myRetrofit
                .create(GetRestaurants.class)
                .getRestaurantsByCity(location);
    }

    public static Call<Map<String, List<MealInfo>>> getRestraurantMeals(int id) {
        return myRetrofit
                .create(GetRestaurantMeals.class)
                .getRestaurantMeals(id);
    }






}
