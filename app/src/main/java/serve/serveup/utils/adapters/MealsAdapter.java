package serve.serveup.utils.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import serve.serveup.R;
import serve.serveup.dataholder.MealInfo;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsHolder> {

    private ArrayList<MealInfo> meals;

    // Define the View Holder
    static class MealsHolder extends RecyclerView.ViewHolder {
        LinearLayout cardMealContainer;
        TextView cardMealTitle;
        TextView cardMealDescription;
        TextView cardMealPrice;

        MealsHolder(final View itemView) {
            super(itemView);
            // Initialize the parameters based on the Card layout names
            cardMealContainer = itemView.findViewById(R.id.cardMealContainer);
            cardMealTitle = itemView.findViewById(R.id.mealTitleText);
            cardMealDescription = itemView.findViewById(R.id.mealDescriptionText);
            cardMealPrice = itemView.findViewById(R.id.mealPriceText);
        }
    }

    public MealsAdapter(ArrayList<MealInfo> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public MealsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Find and inflate the appropriate card layout.
        View cardMeal = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_meal, parent, false);
        return new MealsHolder(cardMeal);
    }

    @Override
    public void onBindViewHolder(@NonNull final MealsHolder holder, int position) {
        MealInfo meal = this.meals.get(position);
        holder.cardMealTitle.setText(meal.getImeJedi());
        holder.cardMealDescription.setText(meal.getOpisJedi());
        holder.cardMealPrice.setText(String.valueOf(meal.getCena()) + " €");

        holder.cardMealContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // empty
            }
        });
    }
    @Override
    public int getItemCount() {
        return meals.size();
    }
}
