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
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import serve.serveup.R;
import serve.serveup.dataholder.apistatus.ApiStatus;
import serve.serveup.dataholder.apistatus.ApiStatusType;
import serve.serveup.dataholder.order.Order;
import serve.serveup.dataholder.session.Session;
import serve.serveup.utils.ContentStore;
import serve.serveup.utils.Utils;
import serve.serveup.utils.adapters.ShoppingBasketItemAdapter;
import serve.serveup.webservices.RestManagement;

public class BasketFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView shoppingBasketRecycleView;
    private ShoppingBasketItemAdapter shoppingBasketItemAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TextView emptyBasketText;
    private LinearLayout makeOrderButton;

    private Context myContext;
    private ContentStore cntStore;

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

        if(getActivity() != null)
            myContext = getActivity().getApplicationContext();

        cntStore =  new ContentStore(myContext);

        // Initialize the view components
        shoppingBasketRecycleView = rootView.findViewById(R.id.shoppingBasketRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        shoppingBasketItemAdapter = new ShoppingBasketItemAdapter(getActivity());

        // Set the layout manager and the adapter of the Recycler View
        shoppingBasketRecycleView.setLayoutManager(linearLayoutManager);
        shoppingBasketRecycleView.setAdapter(shoppingBasketItemAdapter);

        emptyBasketText.setVisibility(shoppingBasketItemAdapter.getItemCount() < 1 ? View.VISIBLE : View.GONE);
        
        
        final Order myOrder = createNewOrder();

        makeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RestManagement.createNewOrderByUser(myOrder).enqueue(new Callback<ApiStatus>() {
                    @Override
                    public void onResponse(Call<ApiStatus> call, Response<ApiStatus> response) {

                        ApiStatus returnStatus = response.body();
                        Utils.logInfo("status: " + returnStatus.getStatus());
                        Utils.logInfo("message: " + returnStatus.getDescription());

                        if(returnStatus.getStatus() == ApiStatusType.OK_STATUS.getStatus()) {

                            Utils.logInfo("kul order");
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiStatus> call, Throwable t) {
                        Utils.logInfo("api 'orders/new_order/' failed");
                    }
                });
            }
        });

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

    private Order createNewOrder() {
        Order newOrder = new Order();
        Session currentSesh = cntStore.getSession();
        newOrder.setCasNarocila(Utils.createDateTimeString());
        newOrder.setCasPrevzema("2018-12-22T14:34:00");
        
        if(currentSesh.mealsNotEmpty() && currentSesh.userIsSet() && currentSesh.restaurantIsSet()) {
            
            newOrder.setRestavracijaID(currentSesh.getCurrentRestaurant().getIdRestavracija());
            newOrder.setUporabnikID(currentSesh.getCurrentUser());
            newOrder.setMeals(currentSesh.getAllMeals());
        }
        return newOrder;
    }
}
