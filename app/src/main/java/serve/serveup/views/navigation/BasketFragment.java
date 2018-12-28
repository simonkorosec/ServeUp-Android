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
import android.widget.TextView;

import serve.serveup.R;
import serve.serveup.utils.adapters.ShoppingBasketItemAdapter;

public class BasketFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView shoppingBasketRecycleView;
    private ShoppingBasketItemAdapter shoppingBasketItemAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView emptyBasketText;

    private Context myContext;

    public BasketFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_basket, container, false);

        myContext = getActivity().getApplicationContext();
        emptyBasketText = rootView.findViewById(R.id.emptyBasketText);

        // Initialize the view components
        shoppingBasketRecycleView = rootView.findViewById(R.id.shoppingBasketRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        shoppingBasketItemAdapter = new ShoppingBasketItemAdapter(myContext);

        // Set the layout manager and the adapter of the Recycler View
        shoppingBasketRecycleView.setLayoutManager(linearLayoutManager);
        shoppingBasketRecycleView.setAdapter(shoppingBasketItemAdapter);

        emptyBasketText.setVisibility(shoppingBasketItemAdapter.getItemCount() < 1 ? View.VISIBLE : View.GONE);

        return rootView;
    }

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
