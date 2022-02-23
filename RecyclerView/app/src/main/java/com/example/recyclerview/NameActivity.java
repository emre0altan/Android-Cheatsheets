package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class NameActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        RecyclerView nameRecyclerView = (RecyclerView) findViewById(R.id.nameRecyclerView);
        nameRecyclerView.setHasFixedSize(true);
        nameRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<NameItem> nameItems = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            NameItem nameItem = new NameItem(String.valueOf(i));
            nameItems.add(nameItem);
        }

        RecyclerView.Adapter nameAdaptor = new NameAdaptor(nameItems, this);
        nameRecyclerView.setAdapter(nameAdaptor);

        findViewById(R.id.backButton).setOnClickListener(this::GoPreviousActivity);
    }

    private void GoPreviousActivity(View view){
        finish();
    }
}