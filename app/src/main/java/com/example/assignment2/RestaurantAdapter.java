package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> { //extends the RecyclerAdaptor class' ViewHolder
    ArrayList<Restautant> restaurants;
    ItemSelected parentActivity;           //Parent context of type ItemSelected which is a method that passes the index of the clicked item of the list to the parent
    public interface ItemSelected{
        public void onItemClicked(int index);
    }

    public RestaurantAdapter(Context context, ArrayList<Restautant> restaurants) {
        parentActivity= (ItemSelected) context;   //type cast the context
        this.restaurants = restaurants;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivname,ivlocation;
        TextView tvname,tvlocation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivname=itemView.findViewById(R.id.ivname);
            ivlocation=itemView.findViewById(R.id.ivlocation);
            tvname=itemView.findViewById(R.id.tvname);
            tvlocation=itemView.findViewById(R.id.tvlocation);

            itemView.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View view) {
                    //passes to the parent the tag of the itemView
                    parentActivity.onItemClicked(restaurants.indexOf((Restautant) itemView.getTag()));
                }
            } );

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflates the view with the special list_item we made and inflate it on parent activity

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(restaurants.get(position));
        holder.tvname.setText(restaurants.get(position).getName());
        holder.tvlocation.setText(restaurants.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
}
