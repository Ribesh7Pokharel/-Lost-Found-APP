package com.example.lostfound_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private EditText titleInput, descriptionInput, locationInput, contactInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);

        dbHelper = new DBHelper(this);
        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        locationInput = findViewById(R.id.locationInput);
        contactInput = findViewById(R.id.contactInput);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    addItem();
                }
            }
        });
    }

    private boolean validateInputs() {
        if (titleInput.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter a title.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (descriptionInput.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter a description.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (locationInput.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter a location.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (contactInput.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Please enter contact information.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true; // All fields are filled
    }

    private void addItem() {
        String title = titleInput.getText().toString().trim();
        String description = descriptionInput.getText().toString().trim();
        String location = locationInput.getText().toString().trim();
        String contact = contactInput.getText().toString().trim();

        dbHelper.addItem(title, description, location, contact);
        Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
        finish(); // Closes the activity and returns to the previous one
    }
}
