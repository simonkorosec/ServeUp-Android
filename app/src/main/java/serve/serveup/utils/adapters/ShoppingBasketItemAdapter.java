package serve.serveup.utils.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import serve.serveup.R;
import serve.serveup.dataholder.MealInfo;
import serve.serveup.dataholder.session.SessionContent;
import serve.serveup.utils.ContentStore;
import serve.serveup.utils.Utils;

public class ShoppingBasketItemAdapter
        extends RecyclerView.Adapter<ShoppingBasketItemAdapter.ShoppingBasketItemHolder> {

    @NonNull
    private MealInfo currentMeal;
    private List<MealInfo> meals;
    private ContentStore cntStore;
    private Context myContext;

    // Define the View Holder
    static class ShoppingBasketItemHolder extends RecyclerView.ViewHolder {
        // Parameters based on the layout of the Card that will be used in the Adapter
        // In this case layout/card_discovery.xml
        LinearLayout cardBasketMealView;
        TextView cardBasketTitle;
        TextView cardBasketDescription;
        TextView cardBasketPrice;
        TextView cardBasketAmount;
        ImageView cancelMealButton;

        ShoppingBasketItemHolder(final View itemView) {
            super(itemView);
            cardBasketMealView = itemView.findViewById(R.id.basketMealInfoCard);
            cardBasketTitle = itemView.findViewById(R.id.basketMealTitleText);
            cardBasketDescription = itemView.findViewById(R.id.basketMealDescriptionText);
            cardBasketPrice = itemView.findViewById(R.id.basketMealPriceText);
            cardBasketAmount = itemView.findViewById(R.id.basketMealKolicinaText);
            cancelMealButton = itemView.findViewById(R.id.cancelMealButton);
        }
    }

    // Adapter constructor
    public ShoppingBasketItemAdapter(Context myContext) {
        this.myContext = myContext;
        this.cntStore = new ContentStore(myContext);
        this.meals = cntStore.getSession().getAllMeals();
    }
    /*
    onCreateViewHolder, onBindViewHolder and getItemCount() HAVE to be defined in order to get rid
    of the errors!
    */

    @NonNull
    @Override
    public ShoppingBasketItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardMealView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_meal_basket, parent, false);
        return new ShoppingBasketItemHolder(cardMealView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShoppingBasketItemHolder holder, final int position) {
        MealInfo myMeal = this.meals.get(position);

        // Set the Holder (card) values based on the values from the data list
        holder.cardBasketTitle.setText(myMeal.getImeJedi());
        holder.cardBasketDescription.setText(myMeal.getOpisJedi());
        holder.cardBasketPrice.setText(holder.cardBasketPrice.getText().toString() + " " + myMeal.getCena() + " €");
        holder.cardBasketAmount.setText(holder.cardBasketAmount.getText().toString() + " " + myMeal.getKolicina() + "x");

        
        // delete meal from adapter and from current Session
        holder.cancelMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentMeal = meals.get(holder.getAdapterPosition());
                Utils.logInfo("Current deleted meal: " + currentMeal);
                if(currentMeal != null) {
                    meals.remove(currentMeal);
                    cntStore.deleteFromSession(SessionContent.MEALS, currentMeal);
                    notifyItemRemoved(holder.getAdapterPosition());
                }
                makeEmptyBasketTextVisible(view);
            }
        });


        holder.cardBasketMealView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO sent meals into order and stuff
                /*Bundle myBundle = new Bundle();
                currentRestaurant = meals.get(holder.getAdapterPosition());
                if (view.getContext() != null) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantActivity.class);
                    myIntent.putExtras(myBundle);
                    view.getContext().startActivity(myIntent);
                }*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    // In case the adapter needs to be refreshed. A simple implementation
    public void refreshAdapter() {
        this.meals.clear();
        this.meals.addAll(cntStore.getSession().getAllMeals());
        notifyDataSetChanged();
    }

    private void makeEmptyBasketTextVisible(View view) {
        // get child TextView emptyBasketText and set its visibility if adapter is empty
        ConstraintLayout parentLayout = (ConstraintLayout) view
                .getParent()
                .getParent()
                .getParent()
                .getParent();

        TextView emptyBasketText = (TextView) parentLayout.getChildAt(2);
        if (meals.size() < 1) {
            if (emptyBasketText != null) {
                emptyBasketText.setVisibility(View.VISIBLE);
            }
        }
    }
}
