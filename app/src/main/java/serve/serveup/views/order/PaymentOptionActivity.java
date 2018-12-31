package serve.serveup.views.order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import serve.serveup.R;
import serve.serveup.dataholder.order.Order;
import serve.serveup.dataholder.session.Session;
import serve.serveup.utils.ContentStore;
import serve.serveup.utils.Utils;

public class PaymentOptionActivity extends AppCompatActivity {

    private ContentStore cntStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_option);


        cntStore =  new ContentStore(getApplicationContext());
        final Order myOrder = createNewOrder();


        /*
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
        */
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
