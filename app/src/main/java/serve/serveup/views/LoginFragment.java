package serve.serveup.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import serve.serveup.R;
import serve.serveup.utils.Utils;


public class LoginFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        View googleButton = getActivity().findViewById(R.id.sign_in_button);
        View signInButton = getActivity().findViewById(R.id.cardView_signin);
        View signUp = getActivity().findViewById(R.id.textSignUp);
        googleButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardView_signin:
                checkValidation();
                break;
            case R.id.sign_in_button:
                googleSignIn();
                break;
            case R.id.textSignUp:
                signUp();
                break;
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
        Utils.showToast(getContext(), "Not yet implemented");
        /*GoogleSignInUtil myGoogleUtil = new GoogleSignInUtil(getContext());
        myGoogleUtil.setUp();
        GoogleSignInClient mGoogleSignInClient = myGoogleUtil.mGoogleSignInClient;

        int RC_SIGN_IN = 100;

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        */
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
