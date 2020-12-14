package com.example.card_wars.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.card_wars.R;
import com.example.card_wars.objects.Record;
import com.example.card_wars.objects.TopTen;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Fragment_Map extends Fragment  {

    private GoogleMap mMap;
    private MarkerOptions markerOptions;
    private TopTen topTen;


    public Fragment_Map(TopTen topTen) {
        this.topTen = topTen;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Init map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        // Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                if (topTen != null) {
                    ArrayList<Record> records = topTen.getRecords();
                    for (int i = 0; i < records.size(); i++) {
                        Record record = records.get(i);
                        LatLng latLng = new LatLng(record.getMyPosition().getLatitude(), record.getMyPosition().getLongitude());
                        addMarker(latLng);
                    }
                }

            }
        });

        return view;
    }

    public void addMarker(LatLng latLng) {
        markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        mMap.addMarker(markerOptions);
    }

    public void zoomToMarker(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }

} // Fragment_Map

