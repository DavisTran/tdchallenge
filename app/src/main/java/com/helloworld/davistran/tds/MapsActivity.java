package com.helloworld.davistran.tds;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LatLng start = new LatLng(43027459,-81236088);
//    LatLng TD1 = new LatLng(43.0200373,-81.2161815);
//    LatLng TD2 = new LatLng(43.0348858,-81.2581032);
//    LatLng TD3 = new LatLng(43.0067415,-81.2406151);
    LatLng loc;
    double LAT = 0.0;
    double LONG = 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent in = getIntent();
        Bundle extras = in.getExtras();

        LAT = Double.parseDouble(extras.getString("latitude"));
        LONG = Double.parseDouble(extras.getString("longitude"));
        loc = new LatLng(LAT,LONG);
//         getSupportActionBar().setTitle("TD Canada Trust");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


//    @Override
//    public void onBackPressed()
//    {
//        finish();
//    }

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

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(loc).title("TD Canada Trust").icon(BitmapDescriptorFactory.fromResource(R.drawable.tdmaplogo)));
//        mMap.addMarker(new MarkerOptions().position(TD2).title("TD Canada Trust Branch & ATM").icon(BitmapDescriptorFactory.fromResource(R.drawable.tdmaplogo)));
//        mMap.addMarker(new MarkerOptions().position(TD3).title("TD Canada Trust").icon(BitmapDescriptorFactory.fromResource(R.drawable.tdmaplogo)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        markStart();
    }

    private void markStart() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(start.latitude, start.longitude, 1);
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (IllegalArgumentException e2) {
            // Error message to post in the log
            e2.printStackTrace();
        }
        // If the reverse geocode returned an address
        if (addresses != null && addresses.size() > 0) {
            // Get the first address
            Address address = addresses.get(0);

            String addressText = String.format(
                    "%s, %s, %s",
                    // If there's a street address, add it
                    address.getMaxAddressLineIndex() > 0 ? address
                            .getAddressLine(0) : "",
                    // Locality is usually a city
                    address.getLocality(),
                    // The country of the address
                    address.getCountryName());
            mMap.addMarker(new MarkerOptions().position(start).title(addressText));

        }
    }
}
