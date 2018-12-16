package serve.serveup.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import serve.serveup.R;
import serve.serveup.dataholder.RestaurantHome;

public class DiscoveryRecyclerAdapter
        extends RecyclerView.Adapter<DiscoveryRecyclerAdapter.DiscoveryRecyclerHolder> {

    // Contains the data from which the adapter will build the cards
    private List<RestaurantHome> restaurantHomes;

    // Define the View Holder
    static class DiscoveryRecyclerHolder extends RecyclerView.ViewHolder {
        // Parameters based on the layout of the Card that will be used in the Adapter
        // In this case layout/card_discovery.xml
        int restaurantID;
        CardView cardDiscoveryView;
        ImageView cardDiscoveryImage;
        TextView cardDiscoveryTitle;
        TextView cardDiscoveryType;
        RatingBar cardDiscoveryRating;

        DiscoveryRecyclerHolder(final View itemView) {
            super(itemView);
            // Initialize the parameters based on the Card layout names
            cardDiscoveryView = itemView.findViewById(R.id.cardDiscoveryView);
            cardDiscoveryImage = itemView.findViewById(R.id.cardDiscoveryImage);
            cardDiscoveryTitle = itemView.findViewById(R.id.cardDiscoveryTitle);
            cardDiscoveryType = itemView.findViewById(R.id.cardDiscoveryType);
            cardDiscoveryRating = itemView.findViewById(R.id.cardDiscoveryRating);
        }
    }

    // Adapter constructor
    public DiscoveryRecyclerAdapter(List<RestaurantHome> restaurantHomes) {
        this.restaurantHomes = restaurantHomes;
    }
    /*
    onCreateViewHolder, onBindViewHolder and getItemCount() HAVE to be defined in order to get rid
    of the errors!
    */

    @NonNull
    @Override
    public DiscoveryRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Find and inflate the appropriate card layout.
        // In this case layout/card_discovery.xml
        View cardDiscoveryView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_discovery, parent, false);

        return new DiscoveryRecyclerHolder(cardDiscoveryView);
    }

    @Override
    public void onBindViewHolder(@NonNull final DiscoveryRecyclerHolder holder, int position) {
        // Get the restaurant home info for the appropriate restaurant
        RestaurantHome restaurantHome = this.restaurantHomes.get(position);
        // Set the Holder (card) values based on the values from the data list
        //holder.cardDiscoveryImage.setImageBitmap(restaurantHome.getImage());
        holder.restaurantID = restaurantHome.getId();
        holder.cardDiscoveryTitle.setText(restaurantHome.getName());
        holder.cardDiscoveryType.setText(restaurantHome.getType());
        holder.cardDiscoveryRating.setRating(restaurantHome.getRating());
        holder.cardDiscoveryImage.setImageBitmap(restaurantHome.getImage());

        holder.cardDiscoveryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.logInfo("Restaurant ID: " + String.valueOf(holder.restaurantID));
                Utils.logInfo("Position: " + String.valueOf(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantHomes.size();
    }

    // In case the adapter needs to be refreshed. A simple implementation
    public void refreshAdapter(List<RestaurantHome> restaurantHomesNew) {
        this.restaurantHomes.clear();
        this.restaurantHomes.addAll(restaurantHomesNew);
        notifyDataSetChanged();
    }
}
