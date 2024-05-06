package com.example.lostfound_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNewItemButton = findViewById(R.id.addNewItemButton);
        Button viewItemsButton = findViewById(R.id.viewItemsButton);

        addNewItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open the activity to add new item
                Intent intent = new Intent(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        viewItemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to open the activity to view items
                Intent intent = new Intent(MainActivity.this, ViewItemsActivity.class);
                startActivity(intent);
            }
        });
    }
}

