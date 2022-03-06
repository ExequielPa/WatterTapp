package com.example.wattertapp.Activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.wattertapp.Model.Emergencia;
import com.example.wattertapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    //private ActivityMapsBinding binding;
    private Button btEmergencia, btUbicacion,btDistance,btclose;
    public Double distance;
    public LatLng ubicacion,emergencia;
    private ArrayList<LatLng> locationArrayList;
    private ArrayList<String> callesArrayList;
    private ArrayList<Integer> presionArrayList;
    private ArrayList<Integer> idArrayList;
    private ArrayList<Integer> estadoArraylist;
    private TextView ubicaciontv,presiontv;
    private ImageView estadoGrifoIv;
    private String ubicacionvalue;
    private Integer presionvalue, estadovalue;
    RequestQueue requestQueue;
    Emergencia adapter = null;

    Bundle bundle = null;
    String Lon = "";
    String Lat = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //binding = ActivityMapsBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        bundle = getIntent().getExtras();
        Lat = bundle.getString("emer1");
        Lon = bundle.getString("emer2");

        ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        getLocalizacion();
        btEmergencia = (Button)findViewById(R.id.btn_emergencia);
        btDistance = (Button)findViewById(R.id.btn_distancia);
        btUbicacion = (Button)findViewById(R.id.btn_ubicacion);

        btUbicacion.setOnClickListener(this);
        btDistance.setOnClickListener(this);
        btEmergencia.setOnClickListener(this);

        locationArrayList = new ArrayList<>();
        callesArrayList = new ArrayList<>();
        presionArrayList = new ArrayList<>();
        idArrayList = new ArrayList<>();
        estadoArraylist = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void jsonArrayRequestGrifos (){
        locationArrayList.clear();
        callesArrayList.clear();
        String URL1 = "https://api.teamproyecto.cl/grifo";
        JsonArrayRequest jsonArrayRequestGrifo = new JsonArrayRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for (int i=0; i<size;i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                Double latitud = jsonObject.getDouble("coordenada_y");
                                Double longitud = jsonObject.getDouble("coordenada_x");
                                String direccion = jsonObject.getString("direccion");
                                Integer id_grifo = jsonObject.getInt("id_grifo");
                                Integer presion = jsonObject.getInt("presion_mca");
                                Integer estado = jsonObject.getInt("estado");
                                locationArrayList.add(new LatLng(latitud,longitud));
                                callesArrayList.add(direccion);
                                idArrayList.add(id_grifo);
                                presionArrayList.add(presion);
                                estadoArraylist.add(estado);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i = 0; i < locationArrayList.size(); i++) {
                            distance = SphericalUtil.computeDistanceBetween(locationArrayList.get(i), emergencia);
                            if (distance <= 300) {
                                if(estadoArraylist.get(i) == 0) {
                                    mMap.addMarker(new MarkerOptions()
                                            .position(locationArrayList.get(i))
                                            .title(callesArrayList.get(i))
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.grifodisponible)));
                                }if(estadoArraylist.get(i) == 1) {
                                    mMap.addMarker(new MarkerOptions()
                                            .position(locationArrayList.get(i))
                                            .title(callesArrayList.get(i))
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.grifonodisponible)));
                                }
                            }
                        }
                    }
                }
                ,new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequestGrifo);
    }

    private void jsonArrayRequestUser() {
        locationArrayList.clear();
        callesArrayList.clear();
        String URL1 = "https://api.teamproyecto.cl/grifo";
        JsonArrayRequest jsonArrayRequestGrifo = new JsonArrayRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for (int i=0; i<size;i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                Double latitud = jsonObject.getDouble("coordenada_y");
                                Double longitud = jsonObject.getDouble("coordenada_x");
                                String direccion = jsonObject.getString("direccion");
                                Integer id_grifo = jsonObject.getInt("id_grifo");
                                Integer presion = jsonObject.getInt("presion_mca");
                                Integer estado = jsonObject.getInt("estado");
                                locationArrayList.add(new LatLng(latitud,longitud));
                                callesArrayList.add(direccion);
                                idArrayList.add(id_grifo);
                                presionArrayList.add(presion);
                                estadoArraylist.add(estado);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i = 0; i < locationArrayList.size(); i++) {
                            distance = SphericalUtil.computeDistanceBetween(locationArrayList.get(i), ubicacion);
                            if (distance <= 300) {
                                if(estadoArraylist.get(i) == 0) {
                                    mMap.addMarker(new MarkerOptions()
                                            .position(locationArrayList.get(i))
                                            .title(callesArrayList.get(i))
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.grifodisponible)));
                                }if(estadoArraylist.get(i) == 1) {
                                    mMap.addMarker(new MarkerOptions()
                                            .position(locationArrayList.get(i))
                                            .title(callesArrayList.get(i))
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.grifonodisponible)));
                                }
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequestGrifo);
    }

    private void getLocalizacion() {
        int permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            addMarkersUser();
        }
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @SuppressLint("PotentialBehaviorOverride")
            @Override
            public boolean onMarkerClick(Marker marker) {
                for (int i = 0; i < callesArrayList.size(); i++) {
                    if (locationArrayList.get(i).latitude == marker.getPosition().latitude) {
                        ubicacionvalue = callesArrayList.get(i);
                        presionvalue = presionArrayList.get(i);
                        estadovalue = estadoArraylist.get(i);
                        showDialog();
                    }
                }
                return false;
            }
        });

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        LocationManager locationManager = (LocationManager) MapsActivity.this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                mMap.clear();

                if (Lat != null && Lon != null){
                    mMap.clear();
                    LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                    ubicacion = miUbicacion;
                    emergenciaMarker();
                    moveCameraEmerg();
                    locationManager.removeUpdates(this);
                    jsonArrayRequestGrifos();
                    distanceFc();
                    addMarkersUser();
                }
                else {
                    mMap.clear();
                    LatLng miUbicacion = new LatLng(location.getLatitude(), location.getLongitude());
                    ubicacion = miUbicacion;
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 16));
                    locationManager.removeUpdates(this);
                    jsonArrayRequestUser();
                    addMarkersUser();
                    btEmergencia.setEnabled(false);
                    btDistance.setEnabled(false);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_pop_up_grifo);

        ubicaciontv = dialog.findViewById(R.id.ubicationgrifotv);
        presiontv = dialog.findViewById(R.id.presiongrifotv);
        estadoGrifoIv = dialog.findViewById(R.id.statusGrifo);
        btclose = dialog.findViewById(R.id.closebt);

        ubicaciontv.setText(ubicacionvalue);
        presiontv.setText(presionvalue.toString());

        if (estadovalue == 0) {
            estadoGrifoIv.setImageResource(R.drawable.grifodisponible);
        } else if(estadovalue == 1) {
            estadoGrifoIv.setImageResource(R.drawable.grifonodisponible);
        }

        btclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void distanceFc(){
        distance = SphericalUtil.computeDistanceBetween(ubicacion, emergencia);
    }

    private void emergenciaMarker (){
        LatLng miEmergencia = new LatLng(Double.parseDouble(Lat),Double.parseDouble(Lon));
        emergencia = miEmergencia;
        mMap.addMarker(new MarkerOptions().position(emergencia).title("Emergencia").icon(BitmapDescriptorFactory.fromResource(R.drawable.emergenciaicon)));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void moveCamerame (){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 16));
        mMap.setMaxZoomPreference(30);
        mMap.setMinZoomPreference(1);
    }

    private void moveCameraEmerg() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(emergencia, 16));
        mMap.setMaxZoomPreference(30);
        mMap.setMinZoomPreference(1);
    }

    private void addMarkersUser () {
        for (int i = 0; i < locationArrayList.size(); i++) {
            distance = SphericalUtil.computeDistanceBetween(locationArrayList.get(i), emergencia);
            if (distance <= 300) {
                if(estadoArraylist.get(i) == 0) {
                    mMap.addMarker(new MarkerOptions()
                            .position(locationArrayList.get(i))
                            .title(callesArrayList.get(i))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.grifodisponible)));
                }if(estadoArraylist.get(i) == 1) {
                    mMap.addMarker(new MarkerOptions()
                            .position(locationArrayList.get(i))
                            .title(callesArrayList.get(i))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.grifonodisponible)));
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_emergencia:
                // AÑADIR A EL CODIGO
                addMarkersUser();
                moveCameraEmerg();
                break;
            case R.id.btn_distancia:
                Toast.makeText(this, "Distancia entre tu ubicacion y la emergencia \n " + String.format("%.2f", distance / 1000) + "km", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_ubicacion:
                moveCamerame();
                addMarkersUser();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}