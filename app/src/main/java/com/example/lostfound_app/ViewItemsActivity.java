package com.example.lostfound_app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewItemsActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private ListView itemsList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_entry);

        dbHelper = new DBHelper(this);
        itemsList = findViewById(R.id.itemsList);
        listItem = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
        itemsList.setAdapter(adapter);
        updateUI();

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finishes this activity and returns to the previous activity (MainActivity)
            }
        });
    }

    private void updateUI() {
        listItem.clear();
        listItem.addAll(dbHelper.getAllItems());
        adapter.notifyDataSetChanged();
    }
}
