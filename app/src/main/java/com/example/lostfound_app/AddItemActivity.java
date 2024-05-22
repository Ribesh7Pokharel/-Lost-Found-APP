package com.example.lostfound_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SELECT_LOCATION = 1;
    private DBHelper dbHelper;
    private double selectedLatitude = 0.0;
    private double selectedLongitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);

        dbHelper = new DBHelper(this);

        EditText itemName = findViewById(R.id.itemName);
        EditText itemDescription = findViewById(R.id.itemDescription);
        EditText contactInfo = findViewById(R.id.contactInfo);
        Button saveButton = findViewById(R.id.saveButton);
        Button selectLocationButton = findViewById(R.id.selectLocationButton);

        selectLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MapActivity to select location
                Intent intent = new Intent(AddItemActivity.this, MapActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_LOCATION);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemName.getText().toString();
                String description = itemDescription.getText().toString();
                String contact = contactInfo.getText().toString();

                dbHelper.addItem(name, description, selectedLatitude, selectedLongitude, contact);

                finish(); // Close the activity after saving the item
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_LOCATION && resultCode == RESULT_OK) {
            // Get selected location from MapActivity
            if (data != null) {
                selectedLatitude = data.getDoubleExtra("latitude", 0.0);
                selectedLongitude = data.getDoubleExtra("longitude", 0.0);
            }
        }
    }
}
