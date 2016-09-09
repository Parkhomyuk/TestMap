package com.example.testmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener, LocationListener {
    GoogleMap googleMap;
    boolean mapReady=false;
    MarkerOptions newHome;
    MarkerOptions oldHome;
    MarkerOptions ds;
    /*com.example.testmap*/

    private final String LOG_TAG="TestApp";
    private TextView textOutPut;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGoogleApiClient= new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        textOutPut= (TextView)findViewById(R.id.txtoutput);

        Button btnMap= (Button)findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
            }
        });
        Button btnSatellite= (Button)findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
            }
        });
        Button btnHybrid= (Button)findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mapReady){
                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
            }
        });

        Button btnHifa= (Button)findViewById(R.id.btnhifa);
        btnHifa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                LatLng haifa= new LatLng(32.812754, 34.980381);


                CameraPosition target2= CameraPosition.builder().target(haifa).zoom(12).build();
                /*CameraPosition target3= CameraPosition.builder().target(dublin).zoom(7).build();*/

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target2), 3000, null);
               /* googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target3));*/
            }
        });


        Button btnSeattle= (Button)findViewById(R.id.btnierusaliam);
        btnSeattle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LatLng ierusalim= new LatLng(31.778165, 35.229819);

                CameraPosition target2= CameraPosition.builder().target(ierusalim).zoom(12).build();
                /*CameraPosition target3= CameraPosition.builder().target(dublin).zoom(7).build();*/

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target2), 3000, null);
            }
        });
        Button btnkhv= (Button)findViewById(R.id.btnkhv);
        btnkhv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                      LatLng khv= new LatLng(48.47770493439581, 135.09713823552335);

                CameraPosition target2= CameraPosition.builder().target(khv).zoom(12).build();
                /*CameraPosition target3= CameraPosition.builder().target(dublin).zoom(7).build();*/

                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target2), 3000, null);
               /* googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target3));*/
            }
        });
        Button btnmoscow= (Button)findViewById(R.id.btnMoscow);
        btnmoscow.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                LatLng msc= new LatLng(55.70524131624346, 37.66091664414846);

                CameraPosition target2= CameraPosition.builder().target(msc).zoom(12).build();


                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(target2), 3000, null);

            }
        });
         newHome= new MarkerOptions().position(new LatLng(32.8096103376595, 35.10577169974824)).title("My new home");
         oldHome= new MarkerOptions().position(new LatLng(48.47816416896265, 135.0856808709197)).title("My old home");
         ds= new MarkerOptions().position(new LatLng(32.81640954877393, 35.085234440126875)).title("Driving School").icon(BitmapDescriptorFactory.fromResource(R.drawable.ins));
        MapFragment mapFragment= (MapFragment)getFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);




    }


    public void onMapReady(GoogleMap map){
        mapReady=true;
        googleMap=map;
        LatLng ny= new LatLng(40.7484,-73.9857);

        CameraPosition target= CameraPosition.builder().target(ny).zoom(7).build();




        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));

         googleMap.addMarker(newHome);
         googleMap.addMarker(oldHome);
         googleMap.addMarker(ds);


    }
    private void flyTo(CameraPosition position){
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {

        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest=LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,mLocationRequest,this);
    }
}
