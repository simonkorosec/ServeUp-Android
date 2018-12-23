package serve.serveup.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import serve.serveup.R;

public class FoodTypesAdapter extends RecyclerView.Adapter<FoodTypesAdapter.FoodTypesHolder> {

    private ArrayList<String> foodTypes;

    // Define the View Holder
    static class FoodTypesHolder extends RecyclerView.ViewHolder {
        LinearLayout cardFoodTypeContainer;
        TextView cardFoodTypeTitle;

        FoodTypesHolder(final View itemView) {
            super(itemView);
            // Initialize the parameters based on the Card layout names
            cardFoodTypeContainer = itemView.findViewById(R.id.cardFoodType);
            cardFoodTypeTitle = itemView.findViewById(R.id.foodTypeText);
        }
    }

    public FoodTypesAdapter(ArrayList<String> foodTypes) {
        this.foodTypes = foodTypes;
    }

    @NonNull
    @Override
    public FoodTypesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Find and inflate the appropriate card layout.
        View cardFoodType = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_food_type, parent, false);
        return new FoodTypesHolder(cardFoodType);
    }

    @Override
    public void onBindViewHolder(@NonNull final FoodTypesHolder holder, int position) {
        // Get the restaurant home info for the appropriate restaurant
        String foodType = this.foodTypes.get(position);
        holder.cardFoodTypeTitle.setText(foodType);

        holder.cardFoodTypeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Bundle myBundle = new Bundle();
                currentRestaurant = restaurantsHome.get(holder.getAdapterPosition());
                Utils.logInfo("current restaurant position: " + holder.getAdapterPosition());
                myBundle.putSerializable("restaurant_info", currentRestaurant);
                if (view.getContext() != null) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantActivity.class);
                    myIntent.putExtras(myBundle);
                    view.getContext().startActivity(myIntent);
                }
                */
            }
        });
    }
    @Override
    public int getItemCount() {
        return foodTypes.size();
    }
}
