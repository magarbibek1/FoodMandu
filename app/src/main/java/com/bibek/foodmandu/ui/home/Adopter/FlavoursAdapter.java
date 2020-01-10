package com.bibek.foodmandu.ui.home.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bibek.foodmandu.ui.home.Model.FlavoursViewModel;
import com.bibek.foodmandu.R;
import com.bibek.foodmandu.ui.home.Model.FlavoursViewModel;

import java.util.List;

public class FlavoursAdapter extends RecyclerView.Adapter<FlavoursAdapter.FlavourViewHolder> {

    Context context;
    List<FlavoursViewModel> flavoursList;

    public FlavoursAdapter(Context context, List<FlavoursViewModel> flavoursList) {
        this.context = context;
        this.flavoursList = flavoursList;
    }

    @NonNull
    @Override
    public FlavourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.flavours,parent,false);
        return new FlavourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlavourViewHolder holder, int position) {

        FlavoursViewModel flavoursViewModel=flavoursList.get(position);
        holder.image.setImageResource(flavoursViewModel.getImage());
        holder.tvItem.setText(flavoursViewModel.getItem());
        holder.tvCafe.setText(flavoursViewModel.getCafe());
    }

    @Override
    public int getItemCount() {
        return flavoursList.size();
    }

    public class FlavourViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView tvItem, tvCafe;

        public FlavourViewHolder(@NonNull View itemView) {
            super(itemView);

            image=itemView.findViewById(R.id.image);
            tvItem=itemView.findViewById(R.id.tvitem);
            tvCafe=itemView.findViewById(R.id.tvcafe);
        }
    }
}
