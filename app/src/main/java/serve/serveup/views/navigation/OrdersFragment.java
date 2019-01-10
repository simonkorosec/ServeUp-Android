package serve.serveup.views.navigation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import serve.serveup.R;
import serve.serveup.dataholder.order.ReturnedOrderApiResponse;
import serve.serveup.utils.ContentStore;
import serve.serveup.utils.Utils;
import serve.serveup.webservices.RestManagement;

public class OrdersFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ContentStore cntStore;
    private String userID;

    public OrdersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_orders, container, false);

        cntStore = new ContentStore(getActivity().getApplicationContext());

        if (cntStore.getSession().userIsSet())
            userID = cntStore.getSession().getCurrentUser();

        RestManagement.getUserOrders(userID, 10).enqueue(new Callback<ReturnedOrderApiResponse>() {
            @Override
            public void onResponse(Call<ReturnedOrderApiResponse> call, Response<ReturnedOrderApiResponse> response) {
                if(response.code() == 200) {
                    ReturnedOrderApiResponse returnedResponse = response.body();
                    if(returnedResponse.getStatus() == 1) {
                        Utils.logInfo("recieved back orders from server for current user");
                        // TODO need to be implemented to show all orders

                    }



                }
            }

            @Override
            public void onFailure(Call<ReturnedOrderApiResponse> call, Throwable t) {
                Utils.logInfo("api 'user/get_orders' failed");
            }
        });



        return root;
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
