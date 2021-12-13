package com.example.wavelength;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private List<String> interval_boxes;
    private List<String> times;
    private Context mContext;
    HashMap<Integer, String> positionMap;
    public DataAdapter(List<String> interval_boxes, List<String> times, Context mContext,
                       HashMap<Integer, String> positionMap) {
        this.interval_boxes = interval_boxes;
        this.times = times;
        this.mContext = mContext;
        this.positionMap = positionMap;
        Log.d("MAP3", positionMap.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("HELLO", "onCreateViewHolder: called.");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("HELLO", "onBindViewHolder: called.");
        CardView cardView = new CardView(mContext);
        if (positionMap.get(position) != null && positionMap.get(position).equals("New")) {
            holder.interval_box.setBackgroundColor(Color.parseColor("#59cf98"));
        }
        else if (positionMap.get(position) != null) {
            holder.interval_box.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        else {
            holder.interval_box.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        holder.time.setText(times.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d("HELLO", "ItemCount Class: called.");
        Log.d("HELLO", String.valueOf(interval_boxes.size()));
        return times.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView interval_box;
        TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("HELLO", "VIEWHOLDER Constructor: called.");
            interval_box = itemView.findViewById(R.id.interval_box);
            time = itemView.findViewById(R.id.time);
        }
    }
}

