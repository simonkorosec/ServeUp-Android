package serve.serveup.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import serve.serveup.R;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);

        LoginFragment myLoginFrag = new LoginFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, myLoginFrag).commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // fragment interaction
    }
}
