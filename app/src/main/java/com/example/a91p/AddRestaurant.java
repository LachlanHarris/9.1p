package com.example.a91p;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.Arrays;
import java.util.List;

public class AddRestaurant extends AppCompatActivity {
    EditText placeNameField;
    EditText locationField;
    Button getCurrentLocationButton;
    Button showMapButton;
    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);



        LatLng latlng = getIntent().getParcelableExtra("latlng");
        String name = getIntent().getStringExtra("Name");
        String address = getIntent().getStringExtra("address");


        placeNameField = findViewById(R.id.placeNameField);
        locationField = findViewById(R.id.locationAutoComplete);
        getCurrentLocationButton = findViewById(R.id.getCurrentLocationButton);
        showMapButton = findViewById(R.id.showOnMapButton);
        saveButton = findViewById(R.id.saveButton);

        locationField.setText(address);
        placeNameField.setText(name);


        getCurrentLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddRestaurant.this, AutocompleteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        showMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentmap = new Intent(AddRestaurant.this, ShowOnMap.class);
                intentmap.putExtra("latlng",latlng);
                intentmap.putExtra( "Name", name);
                startActivity(intentmap);

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSave = new Intent(AddRestaurant.this, MainActivity.class);
                intentSave.putExtra("latlng",latlng);
                startActivity(intentSave);
                finish();
            }
        });



    }
}