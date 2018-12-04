package serve.serveup.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import serve.serveup.R;
import serve.serveup.dataholder.UserInfo;
import serve.serveup.utils.GoogleSignInUtil;
import serve.serveup.utils.Utils;


public class LoginFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private SignInButton googleButton;
    private View signInButton;
    private View signUp;
    private GoogleSignInUtil myGoogleUtil;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.d("myLayout", currentUser + " object");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myLayout = inflater.inflate(R.layout.fragment_login, container, false);
        signUp = myLayout.findViewById(R.id.textSignUp);
        googleButton = myLayout.findViewById(R.id.google_button);
        signInButton = myLayout.findViewById(R.id.button_sign_in);

        myGoogleUtil = new GoogleSignInUtil(getContext(), mAuth);
        myGoogleUtil.setUp();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Google sign out
                if (myGoogleUtil.checkIfAlreadySignedIn())
                    myGoogleUtil.signOut();
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignIn();
            }
        });


        return myLayout;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Utils.showToast(getActivity(), "Google authentication failed :(");
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("myLayout", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Bundle myBundle = new Bundle();
                            myBundle.putSerializable("userInfo", new UserInfo(user));

                            Intent startMainPanel = new Intent(getActivity(), MainPanel.class);
                            startMainPanel.putExtras(myBundle);
                            startActivity(startMainPanel);
                            Utils.showToast(getActivity(), "Signed in!");
                        }
                        else {
                            // If sign in fails, display a message to the user.
                            Log.w("myLayout", "signInWithCredential:failure", task.getException());
                        }

                    }
                });
    }

    private void checkValidation() {
        EditText password = getActivity().findViewById(R.id.passwordField);
        EditText email = getActivity().findViewById(R.id.emailField);
        if (Utils.isEmailValid(email.getText().toString()) && Utils.isPasswordValid(password.getText().toString())) {
            // continue with signup
        }
        else {
            Utils.showToast(getContext(), "Invalid email or password!");
        }
    }

    private void googleSignIn() {
        Intent signInIntent = myGoogleUtil.mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signUp() {
        View loginContainer = getActivity().findViewById(R.id.login_container);
        loginContainer.setVisibility(View.INVISIBLE);
        RegistrationFragment nextFrag = new RegistrationFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, nextFrag)
                .addToBackStack(null)
                .commit();
    }


}
