package serve.serveup.utils;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

import serve.serveup.R;

public class GoogleSignInUtil {

    public Context myContext;
    public GoogleSignInClient mGoogleSignInClient;
    public GoogleSignInOptions gso;
    public FirebaseAuth mAuth;


    public GoogleSignInUtil(Context myContext, FirebaseAuth mAuth) {
        this.myContext = myContext;
        this.mAuth = mAuth; //FirebaseAuth.getInstance();
    }

    public void setUp() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(myContext.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(myContext, gso);
    }

    public boolean checkIfAlreadySignedIn() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(myContext);
        if(account != null)
            return true;
        else
            return false;
    }

    public Map<String, String> getUserInfo(FirebaseUser user) {
        Map<String, String> info  = new HashMap<>();
        info.put("email", user.getEmail());
        info.put("display_name", user.getDisplayName());
        //info.put("id_token", user.getIdToken(true));
        info.put("uid", user.getUid());
        return info;
    }

}
