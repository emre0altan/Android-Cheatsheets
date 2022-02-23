package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ColorAdaptor extends RecyclerView.Adapter<ColorAdaptor.ColorHolder> {

    private ArrayList<ColorItem> colorItems;
    private Context context;

    public ColorAdaptor(ArrayList<ColorItem> colorItems, Context context) {
        this.colorItems = colorItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.color_item, parent, false);
        return new ColorHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorHolder holder, int position) {
        ColorItem colorItem = colorItems.get(position);
        holder.imageView.setColorFilter(colorItem.getColor());
    }

    @Override
    public int getItemCount() {
        return colorItems.size();
    }

    public class ColorHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;

        public ColorHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

        }
    }

}
