package com.example.a91p;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.PlaceReport;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutocompleteActivity extends AppCompatActivity {
    
    Place CurrentPlace;
    Button SaveButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);
        SaveButton = findViewById(R.id.SaveButton);

        // Initialize the SDK
        Places.initialize(getApplicationContext(),"AIzaSyDzqvpVZ1j-oWFVfeQIrAyXkVFBjewAzYc");

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);

        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        // Set up a PlaceSelectionListener to handle the response.
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
               CurrentPlace = place;
            }


            @Override
            public void onError(@NonNull Status status) {
                Log.i("error", "An error occurred: " + status);
            }
        });

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test = CurrentPlace.getId();

                List<Place.Field> fieldList = new ArrayList<>();
                fieldList.add(Place.Field.LAT_LNG);
                fieldList.add(Place.Field.NAME);
                fieldList.add(Place.Field.ADDRESS);

                FetchPlaceRequest request = FetchPlaceRequest.newInstance(test, fieldList);

                placesClient.fetchPlace(request).addOnSuccessListener(fetchPlaceResponse -> {
                    Place place = fetchPlaceResponse.getPlace();
                    LatLng latlng = place.getLatLng();
                    String name = place.getName();
                    String address = place.getAddress();

                    Intent intent = new Intent(AutocompleteActivity.this, AddRestaurant.class);
                    intent.putExtra( "latlng", latlng);
                    intent.putExtra( "Name", name);
                    intent.putExtra("address", address);
                    startActivity(intent);
                    finish();
                });


                //List x = test2.build().getPlaceFields();
                //Log.i("asdfasrfhbewrsfgthgty", x.get(0).toString());

                
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}