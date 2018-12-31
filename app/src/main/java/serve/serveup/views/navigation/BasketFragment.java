package serve.serveup.views.navigation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import serve.serveup.R;
import serve.serveup.utils.adapters.ShoppingBasketItemAdapter;
import serve.serveup.views.order.PaymentOptionActivity;

public class BasketFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView shoppingBasketRecycleView;
    private ShoppingBasketItemAdapter shoppingBasketItemAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView emptyBasketText;
    private LinearLayout makeOrderButton;

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

        emptyBasketText = rootView.findViewById(R.id.emptyBasketText);
        makeOrderButton = rootView.findViewById(R.id.makeOrderButton);

        // Initialize the view components
        shoppingBasketRecycleView = rootView.findViewById(R.id.shoppingBasketRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        shoppingBasketItemAdapter = new ShoppingBasketItemAdapter(getActivity());

        // Set the layout manager and the adapter of the Recycler View
        shoppingBasketRecycleView.setLayoutManager(linearLayoutManager);
        shoppingBasketRecycleView.setAdapter(shoppingBasketItemAdapter);

        emptyBasketText.setVisibility(shoppingBasketItemAdapter.getItemCount() < 1 ? View.VISIBLE : View.GONE);
        
        makeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), PaymentOptionActivity.class);
                startActivity(myIntent);
            }
        });

        return rootView;
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
