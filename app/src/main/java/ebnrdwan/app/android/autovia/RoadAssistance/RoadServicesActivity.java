package ebnrdwan.app.android.autovia.RoadAssistance;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import ebnrdwan.app.android.autovia.R;
import ebnrdwan.app.android.autovia.utility.ItemclickforRecycler;

public class RoadServicesActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
        , LocationListener, OnMapReadyCallback, ItemclickforRecycler.OnItemClickListener {
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 100;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 7172;
    private static final long UPDATE_INTERVAL = 1000;
    private static final long FATEST_INTERVAL = 100;
    private static final float DISPLACEMENT = 10;
    GoogleApiClient googleApiClient;
    LocationRequest mLocationRequest;
    Location locationService;
    MapFragment mapfragment;
    FloatingSearchView searchView;
    GoogleMap googleMap;
    FragmentManager fm = getSupportFragmentManager();
    Dialog dialog;


    @Override
    protected void onResume() {
        super.onResume();
        if (isLocationPermitted()) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                checkPlayServices();
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_services);
        RecyclerView rvRoad = (RecyclerView) findViewById(R.id.roadServieRecycler);
        initializeViews();

        if (isLocationPermitted()) {
            initializeGoogleAPiClinet();
            createLocationRequest();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvRoad.setLayoutManager(linearLayoutManager);
//        ItemclickforRecycler.addTo(rvRoad).setOnItemClickListener(this);

        RoadAssistAdapter assistAdapter = new RoadAssistAdapter(this, getmodels());
        rvRoad.setAdapter(assistAdapter);


    }

    // initialize your Api Component
    private synchronized void initializeGoogleAPiClinet() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        //Fix first time run app if permission doesn't grant yet so can't get anything
        googleApiClient.connect();


    }

    // Initialize Location Request
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setSmallestDisplacement(DISPLACEMENT);
    }


    private void initializeViews() {
        mapfragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapFrag);
        mapfragment.getMapAsync(this);
        searchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, final String newQuery) {
            }
        });
    }

    private ArrayList<RoadServiceModel> getmodels() {
        ArrayList<RoadServiceModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        modelArrayList.add(new RoadServiceModel("clean car", 100, "http"));
        return modelArrayList;
    }


    private boolean isLocationPermitted() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.alertTitle)
                        .setMessage(R.string.alertMessage)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(RoadServicesActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create().show();
            } else {
                ActivityCompat.requestPermissions(RoadServicesActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(), "This device is not supported", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    private void flyTo(GoogleMap map, CameraPosition position, int milliSeconds) {
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position), milliSeconds, null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (isLocationPermitted()) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_LOCATION:
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            //Request location updates:
                            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, mLocationRequest, this);
                            //Request location updates:

                        }
                    }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.dialog_ok:

                break;
            case R.id.dialog_cancel:
                dialog.dismiss();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationService = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        if (locationService != null) {

            LatLng location = new LatLng(locationService.getLatitude(), locationService.getLongitude());
            CameraPosition currentLocation = CameraPosition.builder().target(location).zoom(15).tilt(30.4f).build();

            flyTo(googleMap, currentLocation, 1000);

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected())
            googleApiClient.disconnect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!googleApiClient.isConnected())
            googleApiClient.connect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;

    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        Toast.makeText(RoadServicesActivity.this, "service Test of item " + position, Toast.LENGTH_SHORT).show();

//                bottomSheet.showWithSheetView(LayoutInflater.from(WorkshopActivity.this).inflate(R.layout.custom_view, bottomSheet, false));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        RoadRequestDialogF dialogF = new RoadRequestDialogF();
//                dialogF.show(fragmentTransaction,"ALRT");
        dialog = new Dialog(RoadServicesActivity.this);
        dialog.setContentView(R.layout.road_dialog);
        dialog.setTitle("Request A Service");
        Button ok = (Button) dialog.findViewById(R.id.dialog_ok);
        ok.setOnClickListener(RoadServicesActivity.this);
        Button cancel = (Button) dialog.findViewById(R.id.dialog_cancel);
        cancel.setOnClickListener(RoadServicesActivity.this);


        dialog.show();
        Snackbar.make(findViewById(R.id.roadServieRecycler), "heey yeb", Snackbar.LENGTH_SHORT).show();
    }


}

