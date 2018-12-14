package serve.serveup.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import serve.serveup.R;
import serve.serveup.dataholder.UserID;
import serve.serveup.webservices.RestManagement;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,
        RegistrationFragment.OnFragmentInteractionListener, DiscoveryFragment.OnFragmentInteractionListener {

    DiscoveryFragment discoveryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        setContentView(R.layout.fragment_login);

        LoginFragment myLoginFrag = new LoginFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, myLoginFrag).commit();


        RestManagement.getAllUsers().enqueue(new Callback<List<UserID>>() {
            @Override
            public void onResponse(Call<List<UserID>> call, Response<List<UserID>> response) {
               // whatever you want to call
            }

            @Override
            public void onFailure(Call<List<UserID>> call, Throwable t) {

            }
        });*/

        // TODO Proper fragment switching, for now just uncomment the fragment you want to appear

        setContentView(R.layout.fragment_discovery);

        DiscoveryFragment discoveryFragment = new DiscoveryFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLayout, discoveryFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // fragment interaction
    }

    @Override
    public void onBackPressed() {
        int isH = findViewById(R.id.login_container).getVisibility();
        if(isH == View.INVISIBLE)
            findViewById(R.id.login_container).setVisibility(View.VISIBLE);
        super.onBackPressed();
    }
}
