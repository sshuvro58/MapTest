package me.shuvro.maptest;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        final LatLng sydney = new LatLng(23.7097279, 90.4126556);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Luxmibazar"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14));

        Toast.makeText(this,""+getDestinationPoint(sydney,90.00,10.10),Toast.LENGTH_SHORT).show();


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(getDestinationPoint(sydney,90.00,10.10)));

                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,45.00,1.10)).title("Luxmibazar 45"+getDestinationPoint(sydney,45.00,1.10)));
                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,-45.00,1.10)).title("Luxmibazar -45"+getDestinationPoint(sydney,-45.00,1.10)));
                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,-90.00,1.10)).title("Luxmibazar -90"));
                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,90.00,1.10)).title("Luxmibazar 90"));
                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,0.00,1.10)).title("Luxmibazar 00"));
                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,180.00,1.10)).title("Luxmibazar 180"));
                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,135.00,1.10)).title("Luxmibazar 180"));
                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,-135.00,1.10)).title("Luxmibazar 180"));
//                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,120,1.10)).title("Luxmibazar"));
//                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,-120,1.10)).title("Luxmibazar"));
//                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,-90,1.10)).title("Luxmibazar"));
//                mMap.addMarker(new MarkerOptions().position(getDestinationPoint(sydney,90,1.10)).title("Luxmibazar"));


//                PolylineOptions rectOptions = new PolylineOptions()
//                        .add(getDestinationPoint(sydney,15.00,1.10))
//                        .add(getDestinationPoint(sydney,25.00,0.50))  // North of the previous point, but at the same longitude
//                        .add(getDestinationPoint(sydney,35.00,2.10))  // Same latitude, and 30km to the west
//                        .add(getDestinationPoint(sydney,45.00,0.310))  // Same longitude, and 16km to the south
//                        .add(getDestinationPoint(sydney,90.00,2.5)); // Closes the polyline.
//
//// Get back the mutable Polyline
//                Polyline polyline = mMap.addPolyline(rectOptions);

                PolylineOptions rectOptions = new PolylineOptions()
                        .add(new LatLng(37.35, -122.0))
                        .add(new LatLng(37.45, -122.0))  // North of the previous point, but at the same longitude
                        .add(new LatLng(37.45, -122.2))  // Same latitude, and 30km to the west
                        .add(new LatLng(37.35, -122.2))  // Same longitude, and 16km to the south
                        .add(new LatLng(37.35, -122.0)); // Closes the polyline.

// Get back the mutable Polyline
                Polyline polyline = mMap.addPolyline(rectOptions);

                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.35, -122.0), 14));

            }
        });

    }


    private LatLng getDestinationPoint(LatLng source, double brng, double dist) {
        dist = dist / 6371;
        brng = Math.toRadians(brng);

        double lat1 = Math.toRadians(source.latitude), lon1 = Math.toRadians(source.longitude);
        double lat2 = Math.asin(Math.sin(lat1) * Math.cos(dist) +
                Math.cos(lat1) * Math.sin(dist) * Math.cos(brng));
        double lon2 = lon1 + Math.atan2(Math.sin(brng) * Math.sin(dist) *
                        Math.cos(lat1),
                Math.cos(dist) - Math.sin(lat1) *
                        Math.sin(lat2));
        if (Double.isNaN(lat2) || Double.isNaN(lon2)) {
            return null;
        }
        return new LatLng(Math.toDegrees(lat2), Math.toDegrees(lon2));
    }
}
