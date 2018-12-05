package serve.serveup.webservices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
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






}
