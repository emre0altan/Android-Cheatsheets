package com.example.recyclerview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<ColorItem> colorItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int l = 0; l < 5; l++) {
                    ColorItem colorItem = new ColorItem(
                            getIntFromColor((int)Math.pow(2,i*2),(int)Math.pow(2,j*2),(int)Math.pow(2,l*2))
                    );
                    colorItems.add(colorItem);
                }
            }
        }

        RecyclerView.Adapter adapter = new ColorAdaptor(colorItems, this);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.nameButton).setOnClickListener(this::CallNameActivity);
    }

    private void CallNameActivity(View view){
        Intent myIntent = new Intent(MainActivity.this, NameActivity.class);
        myIntent.putExtra("key", 1); //Optional parameters
        MainActivity.this.startActivity(myIntent);
    }

    public int getIntFromColor(int Red, int Green, int Blue){
        Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
        Blue = Blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }
}