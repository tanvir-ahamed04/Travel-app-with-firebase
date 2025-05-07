package com.example.travelappdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private Context context;
    private List<Hotel> hotelList;

    public HotelAdapter(Context context, List<Hotel> hotelList) {
        this.context = context;
        this.hotelList = hotelList;
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription, tvRating;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvHotelName);
            tvDescription = itemView.findViewById(R.id.tvHotelDescription);
            tvRating = itemView.findViewById(R.id.tvHotelRating);

        }
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        Hotel hotel = hotelList.get(position);
        holder.tvName.setText(hotel.getName());
        holder.tvDescription.setText(hotel.getDescription());
        holder.tvRating.setText("Rating: " + hotel.getRating());

    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }

    public void updateList(List<Hotel> newList) {
        hotelList = newList;
        notifyDataSetChanged();
    }
}