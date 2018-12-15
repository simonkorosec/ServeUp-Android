package serve.serveup.views.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import serve.serveup.R;
import serve.serveup.dataholder.RestaurantHome;
import serve.serveup.utils.DiscoveryRecyclerAdapter;

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RecyclerView discoveryRecyclerView;
    DiscoveryRecyclerAdapter discoveryRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    ArrayList<RestaurantHome> restaurantHomes; // Contains data of every restaurant home page info

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // TODO Proper initialization of the restaurantHomes object
        restaurantHomes = new ArrayList<>();
        // TODO get the data from the API instead of hardcoding
        Bitmap image = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.common_google_signin_btn_icon_dark);
        for (int i = 0; i < 20; i++) {
            restaurantHomes.add(new RestaurantHome(i,"Foculus", "Picerija", 4.5f, image));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restaurantHomes = new ArrayList<>();
        // TODO get the data from the API instead of hardcoding
        Bitmap image = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.common_google_signin_btn_icon_dark);
        for (int i = 0; i < 20; i++) {
            restaurantHomes.add(new RestaurantHome(i,"Foculus", "Picerija", 4.5f, image));
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the view components
        discoveryRecyclerView = rootView.findViewById(R.id.discoveryRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        discoveryRecyclerAdapter = new DiscoveryRecyclerAdapter(restaurantHomes);

        // Set the layout manager and the adapter of the Recycler View
        discoveryRecyclerView.setLayoutManager(linearLayoutManager);
        discoveryRecyclerView.setAdapter(discoveryRecyclerAdapter);

        return rootView;
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
}
