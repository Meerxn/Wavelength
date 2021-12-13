package com.example.wavelength;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private Integer[] imagesArray;
    private String[] namesArray;

    public RoomAdapter(Integer[] imagesArray, String[] namesArray) {
        this.imagesArray = imagesArray;
        this.namesArray = namesArray;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_image, parent, false);
        return new RoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.roomImage.setImageResource(imagesArray[position]);
        holder.roomName.setText(namesArray[position]);
    }

    @Override
    public int getItemCount() {
        return imagesArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView roomImage;
        TextView roomName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("HELLO", "VIEWHOLDER Constructor: called.");
            roomImage = itemView.findViewById(R.id.adapter_img);
            roomName = itemView.findViewById(R.id.adapter_name);
        }
    }
}