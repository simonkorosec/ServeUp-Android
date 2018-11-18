package serve.serveup.utils;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class GoogleSignInUtil {

    public Context myContext;
    public GoogleSignInClient mGoogleSignInClient;
    public GoogleSignInOptions gso;


    public GoogleSignInUtil(Context myContext) {
        this.myContext = myContext;
    }

    public void setUp() {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
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

}
