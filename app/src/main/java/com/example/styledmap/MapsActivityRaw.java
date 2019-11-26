package com.example.map_crime;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;


import android.util.Log;
import android.widget.Toast;

/**
 * A styled map using JSON styles from a raw resource.
 */
public class MapsActivityRaw extends MainMenuActivity
        implements OnMyLocationButtonClickListener,
        OnMyLocationClickListener,
        OnMapReadyCallback{

    private static final String TAG = MapsActivityRaw.class.getSimpleName();

    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps_raw);

        // Get the SupportMapFragment and register for the callback
        // when the map is ready for use.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

/*    public void getMapAsync(OnMapReadyCallback callback){
        GoogleMapOptions options = new GoogleMapOptions();
        options.mapType(GoogleMap.MAP_TYPE_SATELLITE)
                .compassEnabled(true)
                .rotateGesturesEnabled(true)
                .tiltGesturesEnabled(true);

    }*/
    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready for use.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        mMap.setMyLocationEnabled(true);
        // File directory = context.getFilesDir();
        //File file = new File(directory,gunMarker.bmp);
        //getMapAsync(this);
        BitmapDescriptor armedRob = BitmapDescriptorFactory.fromResource(R.drawable.armedrob);
        BitmapDescriptor assault = BitmapDescriptorFactory.fromResource(R.drawable.assault);
        BitmapDescriptor assaultTwo = BitmapDescriptorFactory.fromResource(R.drawable.assaulttwo);
        BitmapDescriptor burglary = BitmapDescriptorFactory.fromResource(R.drawable.burglary);
        BitmapDescriptor carburglary = BitmapDescriptorFactory.fromResource(R.drawable.carburglary);
        BitmapDescriptor homicide = BitmapDescriptorFactory.fromResource(R.drawable.homicide);
        BitmapDescriptor kidnap = BitmapDescriptorFactory.fromResource(R.drawable.kidnap);
        BitmapDescriptor rape = BitmapDescriptorFactory.fromResource(R.drawable.rape);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.052235, -118.243683))
                .title("Robbery")
                .snippet("11/10/19 Armed robbery via handgun")
                .icon(armedRob));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.052235, -118.343683))
                .title("Assault")
                .snippet("10/10/19 Assault with a deadly weapon using a handgun")
                .icon(assault));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.052235, -118.143683))
                .title("Assault")
                .snippet("09/09/19 Assault with a deadly weapon using a knife")
                .icon(assaultTwo));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.052235, -118.343633))
                .title("Burglary")
                .snippet("08/07/19 Burglary of a residence")
                .icon(burglary));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.052235, -118.343583))
                .title("Burglary")
                .snippet("07/01/19 Burglary of a vehicle")
                .icon(carburglary));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.052235, -118.342683))
                .title("Homicide")
                .snippet("06/02/19 Criminal homicide involving a handgun")
                .icon(homicide));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.052235, -118.313683))
                .title("Kidnap")
                .snippet("05/01/19 Kidnapping occurred with use of a hand gun")
                .icon(kidnap));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(34.052235, -118.303683))
                .title("Rape")
                .snippet("06/06/19 Forcible rape using a weapon")
                .icon(rape));

/*        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }*/
        // Position the map's camera near Sydney, Australia.
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(34.752235, -118.243683)));
    }
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */


/*    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
        }
    }*/

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        //if (PermissionUtils.isPermissionGranted(permissions, grantResults,
          //      Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            //enableMyLocation();
       /// } else {
            // Display the missing permission error dialog when the fragments resume.
          //  mPermissionDenied = true;
        }
   // }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        //PermissionUtils.PermissionDeniedDialog.newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

}




