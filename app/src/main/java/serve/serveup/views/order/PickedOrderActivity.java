package serve.serveup.views.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import serve.serveup.R;
import serve.serveup.dataholder.MealInfo;
import serve.serveup.dataholder.order.ReturnedOrder;
import serve.serveup.utils.adapters.PickedOrderMealsAdapter;

public class PickedOrderActivity extends AppCompatActivity {

    private ImageView backButton;
    private TextView restuarantName;
    private LinearLayoutManager layoutManager;
    private RecyclerView myRecyclerView;
    private PickedOrderMealsAdapter mealsAdapter;

    private ArrayList<MealInfo> orderMeals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_order);

        backButton = findViewById(R.id.backToOrdersIcon);
        restuarantName = findViewById(R.id.pickedOrderRestaurantText);
        myRecyclerView = findViewById(R.id.pickedOrderMealsRecyclerView);
        layoutManager = new LinearLayoutManager(getApplicationContext());

        Intent myIntent = getIntent();
        if(myIntent != null) {
            ReturnedOrder order = (ReturnedOrder) myIntent.getSerializableExtra("picked_order");
            restuarantName.setText(order.getImeRestavracije());
            orderMeals = new ArrayList<>(order.getMeals());

            mealsAdapter = new PickedOrderMealsAdapter(orderMeals);
            myRecyclerView.setAdapter(mealsAdapter);
            myRecyclerView.setLayoutManager(layoutManager);

        }


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
