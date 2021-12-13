package com.example.wavelength;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.concurrent.Executor;

public class MapsFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 12;
    private final LatLng mDestinationLatLng = new LatLng(43.0757339, -89.4040064);
    private FusedLocationProviderClient mFusedLocationProviderClient;

    public static String closestLib = "Business Library";

    LatLng memorial;
    LatLng college;
    LatLng steenbock;
    LatLng business;
    LatLng social;


    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            memorial = new LatLng(43.075226, -89.397990);
            college = new LatLng(43.076662, -89.401000);
            steenbock = new LatLng(43.076110, -89.413369);
            business = new LatLng(43.0727087, -89.401391);
            social = new LatLng(43.074097, -89.408299);

            googleMap.addMarker(new MarkerOptions().position(memorial).title("Memorial Library"));
            googleMap.addMarker(new MarkerOptions().position(college).title("College Library"));
            googleMap.addMarker(new MarkerOptions().position(steenbock).title("Steenbock Library"));
            googleMap.addMarker(new MarkerOptions().position(business).title("Business Library"));
            googleMap.addMarker(new MarkerOptions().position(social).title("Social Work Library"));

            closestLib = "";

            mFusedLocationProviderClient =
                    LocationServices.getFusedLocationProviderClient(getActivity());
            displayMyLocation(googleMap);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        Log.v("MAP", "Get map fragment");
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void displayMyLocation(GoogleMap mmap) {
        //Permission Check
        int permission = ActivityCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        Log.v("Perm", "Permission asked");

        //If not granted ask
        if (permission == PackageManager.PERMISSION_DENIED) {
            Log.v("Perm", "Permission denied");
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        //If permission granted, display
        else {
            mFusedLocationProviderClient.getLastLocation().addOnCompleteListener(getActivity(), task -> {
                Location mLastKnownLocation = task.getResult();
                if (task.isSuccessful() && mLastKnownLocation != null) {
                    LatLng myloc = new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude());

                    double minDistance = 10000000.0;
                    LatLng selLib = new LatLng(0.0, 0.0);

                    double mem_dist = calcDistance(memorial, myloc);
                    double coll_dist = calcDistance(college, myloc);
                    double steen_dist = calcDistance(steenbock, myloc);
                    double bus_dist = calcDistance(business, myloc);
                    double soc_dist = calcDistance(social, myloc);

                    if (mem_dist <= minDistance) {
                        minDistance = mem_dist;
                        selLib = memorial;
                        closestLib = "Memorial";
                    }
                    if (coll_dist <= minDistance) {
                        minDistance = coll_dist;
                        selLib = college;
                        closestLib = "College";
                    }
                    if (steen_dist <= minDistance) {
                        minDistance = steen_dist;
                        selLib = steenbock;
                        closestLib = "Steenbock";
                    }
                    if (bus_dist <= minDistance) {
                        minDistance = bus_dist;
                        selLib = business;
                        closestLib = "Business";
                    }
                    if (soc_dist <= minDistance) {
                        minDistance = soc_dist;
                        selLib = social;
                        closestLib = "Social";
                    }

                    mmap.addMarker(new MarkerOptions().position(selLib).title("Closest Library")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                    mmap.setMyLocationEnabled(true);
                    mmap.animateCamera(CameraUpdateFactory.newLatLngZoom(myloc,
                            13));
                }
            });
        }
    }


    private double calcDistance(LatLng loc1, LatLng loc2) {
        double x1 = loc1.latitude;
        double x2 = loc2.latitude;
        double y1 = loc1.longitude;
        double y2 = loc2.longitude;

        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        return distance;
    }

}