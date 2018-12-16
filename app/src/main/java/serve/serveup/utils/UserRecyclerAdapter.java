package serve.serveup.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import serve.serveup.R;
import serve.serveup.dataholder.ProfileOption;

public class UserRecyclerAdapter
        extends RecyclerView.Adapter<UserRecyclerAdapter.UserRecyclerHolder> {

    private List<ProfileOption> userOptions;

    /* TODO implement user profile functionalities for use profile
    *  TODO info about email, account info, settings, log out option
    * */


    static class UserRecyclerHolder extends RecyclerView.ViewHolder {
        CardView cardUserOptionView;
        ImageView cardDiscoveryImage;
        TextView cardDiscoveryTitle;

        UserRecyclerHolder(final View itemView) {
            super(itemView);
            cardUserOptionView = itemView.findViewById(R.id.cardUserOptionView);
            cardDiscoveryImage = itemView.findViewById(R.id.userOptionIcon);
            cardDiscoveryTitle = itemView.findViewById(R.id.userOptionText);
        }
    }

    public UserRecyclerAdapter(List<ProfileOption> userOptions) {
        this.userOptions = userOptions;
    }

    @NonNull
    @Override
    public UserRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardUserOptionView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_user_option, parent, false);
        return new UserRecyclerHolder(cardUserOptionView);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserRecyclerHolder holder, int position) {
        ProfileOption profileOption = this.userOptions.get(position);
        holder.cardDiscoveryTitle.setText(profileOption.getOptionTitle());
        holder.cardDiscoveryImage.setImageDrawable(profileOption.getIcon());

        holder.cardUserOptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (holder.getAdapterPosition()) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        logOut();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userOptions.size();
    }


    private void logOut() {
        // log out from the app
    }

}
