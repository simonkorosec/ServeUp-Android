package serve.serveup.views.navigation;

import android.content.Context;
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
import serve.serveup.utils.Utils;

public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    RecyclerView discoveryRecyclerView;
    DiscoveryRecyclerAdapter discoveryRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;

    ArrayList<RestaurantHome> restaurantHomes; // Contains data of every restaurant home page info

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        restaurantHomes = new ArrayList<>();


        /* TODO important! here adde the api call that gets all the restaurants and their info
           TODO then implement function that saves base64 image strings into file "base64String.txt" in /assets root folder
           TODO once saved into file, split and parse the base64 strings back into array list
           TODO and pass it into parseBitmapFromBase64 function.

           TODO implement further ordering worklow, when user clicks on a restaurant.
        * */

        ArrayList<String> base64Strings = Utils.readFromFile("base64Strings.txt", getContext());


        for (int i = 0; i < 5; i++) {
            restaurantHomes.add(new RestaurantHome(i,"Foculus", "Picerija", 4.5f,
                    Utils.parseBitmapFromBase64(getContext(), base64Strings.get(i))));
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
        void onFragmentInteraction(Uri uri);
    }

}
