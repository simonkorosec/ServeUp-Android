package serve.serveup.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import serve.serveup.R;
import serve.serveup.dataholder.RestaurantInfo;
import serve.serveup.utils.FoodTypesAdapter;

public class RestaurantActivity extends AppCompatActivity {

    private TextView imeRestavracijeText;
    private ImageView backButton;

    private RecyclerView foodRecyclerView;
    private FoodTypesAdapter foodRecyclerAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<String> foodTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        imeRestavracijeText = findViewById(R.id.imeRestavracijeText);
        foodRecyclerView = findViewById(R.id.foodTypesRecyclerview);
        backButton = findViewById(R.id.backIcon);

        foodTypes = new ArrayList<>();
        populateFoodTypes();

        Bundle getBundle = getIntent().getExtras();

        if (getBundle != null) {
            RestaurantInfo pickedRestaurant = (RestaurantInfo) getBundle.getSerializable("restaurant_info");
            imeRestavracijeText.setText(pickedRestaurant.getImeRestavracije());
        }
        // Initialize the view components
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        foodRecyclerAdapter = new FoodTypesAdapter(foodTypes);
        // Set the layout manager and the adapter of the Recycler View
        foodRecyclerView.setLayoutManager(linearLayoutManager);
        foodRecyclerView.setAdapter(foodRecyclerAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void populateFoodTypes() {
        foodTypes.add(getResources().getString(R.string.kosila));
        foodTypes.add(getResources().getString(R.string.juhe));
        foodTypes.add(getResources().getString(R.string.glavne_jedi));
        foodTypes.add(getResources().getString(R.string.solate));
        foodTypes.add(getResources().getString(R.string.sladice));
        foodTypes.add(getResources().getString(R.string.pijace));
    }

}
