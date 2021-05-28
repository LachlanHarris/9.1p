package com.example.a91p;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class ShowAllRestaurantsOnMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_restaurants_on_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        intent = getIntent();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = intent.getParcelableExtra("latlng");
        mMap.addMarker(new MarkerOptions().position(latLng).title(""));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //other points to act as restaurants to display
        //the task sheet did not state if we needed to create a database for this task so im just using basic points to act as choices
        mMap.addMarker(new MarkerOptions().position(new LatLng(-37.67748005820255, 144.93410723333545)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-36.381711262743245, 145.39110883032143)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-33.787422555369005, 150.85904764279073)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-32.94770603532379, 151.58243607440926)).title(""));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-16.87957881487174, 145.64461090149427)).title(""));


    }
}