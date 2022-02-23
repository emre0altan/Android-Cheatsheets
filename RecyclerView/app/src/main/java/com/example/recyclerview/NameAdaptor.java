package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NameAdaptor extends RecyclerView.Adapter<NameAdaptor.NameHolder> {

    private ArrayList<NameItem> nameItems;
    private Context context;

    public NameAdaptor(ArrayList<NameItem> nameItems, Context context) {
        this.nameItems = nameItems;
        this.context = context;
    }

    @NonNull
    @Override
    public NameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.name_item, parent, false);
        return new NameHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NameHolder holder, int position) {
        NameItem nameItem = nameItems.get(position);
        holder.textView.setText(nameItem.getName());
    }

    @Override
    public int getItemCount() {
        return nameItems.size();
    }

    public class NameHolder extends RecyclerView.ViewHolder{

        public TextView textView;

        public NameHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.nameTextView);
        }
    }
}
