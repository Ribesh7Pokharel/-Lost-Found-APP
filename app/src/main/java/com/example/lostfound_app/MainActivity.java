package com.example.lostfound_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    EditText titleInput, descriptionInput, locationInput, contactInput;
    ListView itemsList;
    ArrayList<String> listItem;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        locationInput = findViewById(R.id.locationInput);
        contactInput = findViewById(R.id.contactInput);
        itemsList = findViewById(R.id.itemsList);

        listItem = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
        itemsList.setAdapter(adapter);
        updateUI();

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString();
                String description = descriptionInput.getText().toString();
                String location = locationInput.getText().toString();
                String contact = contactInput.getText().toString();
                dbHelper.addItem(title, description, location, contact);
                titleInput.setText("");
                descriptionInput.setText("");
                locationInput.setText("");
                contactInput.setText("");
                updateUI();
                Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        if (listItem != null) {
            listItem.clear();
            listItem.addAll(dbHelper.getAllItems()); // Method to be implemented in DBHelper
            adapter.notifyDataSetChanged();
        }
    }
}
