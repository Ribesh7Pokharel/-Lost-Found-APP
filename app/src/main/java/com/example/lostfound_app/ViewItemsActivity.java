package com.example.lostfound_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class ViewItemsActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_entry);

        dbHelper = new DBHelper(this);

        ListView listView = findViewById(R.id.listView);
        Cursor cursor = dbHelper.getAllItems();

        String[] from = { "name", "description", "contact" };
        int[] to = { R.id.itemName, R.id.itemDescription, R.id.contactInfo };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item_list_layout, cursor, from, to, 0);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Move cursor to the clicked item
                cursor.moveToPosition(position);

                // Get the location data from the cursor
                double latitude = cursor.getDouble(cursor.getColumnIndexOrThrow("latitude"));
                double longitude = cursor.getDouble(cursor.getColumnIndexOrThrow("longitude"));

                // Create an intent to start MapActivity
                Intent intent = new Intent(ViewItemsActivity.this, MapActivity.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            }
        });
    }

    public Cursor getItemsCursor() {
        return dbHelper.getAllItems();
    }
}
