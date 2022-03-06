package com.example.wattertapp.Activitys;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.wattertapp.Adapter.AdapterEmergencia;
import com.example.wattertapp.Adapter.AdapterEstadoEmerg;
import com.example.wattertapp.Adapter.ListAadapter;
import com.example.wattertapp.Model.Emergencia;
import com.example.wattertapp.Model.EstadoEmerg;
import com.example.wattertapp.Model.list_element;
import com.example.wattertapp.R;
import com.example.wattertapp.VolleySingleton;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    //----------------------PRUEBA RECYCLERVIEW VOLLEY----------------------
    RecyclerView recyclerView;

    List<Emergencia> valEmergencia;

    AdapterEmergencia adapter;

    RequestQueue requestQueue;

    private static final String URL="https://api.teamproyecto.cl/vista_emergencia";
    //----------------------PRUEBA RECYCLERVIEW VOLLEY----------------------

    Button btncerrar;

    // ----------- Prueba retorno datos usuario -----------
    TextView txt_nombre,txt_rut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // ----------- Prueba retorno datos usuario -----------
        txt_nombre  = findViewById(R.id.txt_name_user);
        txt_rut = findViewById(R.id.txtrut);
        //getUser();

        //----------------------PRUEBA RECYCLERVIEW VOLLEY----------------------
        recyclerView = findViewById(R.id.listRecyclerView);

        valEmergencia = new ArrayList<>();

        extractEmerg();// Extraer valores

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();
        //----------------------PRUEBA RECYCLERVIEW VOLLEY----------------------

        //init();

        MaterialToolbar toolbar= findViewById(R.id.topAppBar);             //Barra lateral
        DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);       //Vista principal del men
        NavigationView navigationView= findViewById(R.id.navigation_view); //Navegacion en el menu

        //------------------------------------------------------------------------------------------

        //Metodo que permite abrir el menu principal
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //Seleccionar una opcion del menu y muestra un mensaje
        // y retorna a la vista principal del proyecto.
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                item.setChecked(true);

                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id){
                    case R.id.nav_home:
                        Toast.makeText(PrincipalActivity.this, "Vista Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_message:
                        Toast.makeText(PrincipalActivity.this, "Ver el Mapa",Toast.LENGTH_SHORT).show();
                        Intent intentM = new Intent(PrincipalActivity.this, MapsActivity.class);
                        startActivity(intentM);
                        break;
                    case R.id.navEvento:
                        Toast.makeText(PrincipalActivity.this, "Vista de eventos",Toast.LENGTH_SHORT).show();
                        Intent intentEve = new Intent(PrincipalActivity.this, AsistenciaEventoActivity.class);
                        startActivity(intentEve);
                        break;
                    case R.id.navEmer:
                        Toast.makeText(PrincipalActivity.this, "Emergencia",Toast.LENGTH_SHORT).show();
                        Intent intentEmer = new Intent(PrincipalActivity.this, AsistenciaEmergencia.class);
                        startActivity(intentEmer);
                        break;
                    case R.id.navAsistencia:
                        Toast.makeText(PrincipalActivity.this, "Ver asistencia", Toast.LENGTH_SHORT).show();
                        Intent intentAsi = new Intent(PrincipalActivity.this, InformeAsistencia.class);
                        startActivity(intentAsi);
                        break;
                    case R.id.navEquipoCarro:
                        Toast.makeText(PrincipalActivity.this, "Equipo Carro", Toast.LENGTH_SHORT).show();
                        Intent intentEquipo = new Intent(PrincipalActivity.this, InformeEquipoCarro.class);
                        startActivity(intentEquipo);
                        break;
                    case R.id.navQr:
                        Toast.makeText(PrincipalActivity.this, "Vista QR", Toast.LENGTH_SHORT).show();
                        Intent intentQR = new Intent(PrincipalActivity.this, ActivityQR.class);
                        startActivity(intentQR);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        //------------------------------------------------------------------------------------------

        //Cierre de session con Sharedpreferences
        btncerrar = findViewById(R.id.btnCerrarSession);

        //cerrar Listener
        btncerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                logout();
            }
        });
    }

    private void logout() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    // ----------- Prueba retorno datos usuario -----------
    private void getUser() {
        txt_nombre.setText("NOM: -");
        txt_rut.setText("RUT");
    }

    //----------------------PRUEBA RECYCLERVIEW VOLLEY----------------------
    private void extractEmerg() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject emergObject = response.getJSONObject(i);

                        Emergencia emer = new Emergencia();
                        emer.setId_emergencia(emergObject.getString("id_emergencia"));
                        emer.setDescripcion_emer(emergObject.getString("descripcion_emer"));
                        emer.setDireccion(emergObject.getString("direccion"));
                        emer.setId_cod_emer(emergObject.getString("id_cod_emer"));
                        emer.setCodigo_categoria(emergObject.getString("codigo_categoria"));
                        emer.setNombre_categoria(emergObject.getString("nombre_categoria"));
                        emer.setRut_voluntario(emergObject.getString("rut_voluntario"));
                        emer.setNombre_voluntario(emergObject.getString("nombre_voluntario"));
                        emer.setApellido_voluntario(emergObject.getString("apellido_voluntario"));
                        emer.setEstado_emergencia(emergObject.getString("estado_emergencia"));
                        emer.setNombre_compania(emergObject.getString("nombre_compania"));
                        emer.setCod_camion(emergObject.getString("cod_camion"));

                        valEmergencia.add(emer);
                        //valEmergencia.sort(Collections.reverseOrder());
                    } catch (JSONException  e) {
                        e.printStackTrace();
                    }
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setHasFixedSize(true);
                adapter = new AdapterEmergencia(valEmergencia, getApplicationContext(),
                new AdapterEmergencia.OnItemClickListener() {
                    @Override
                    public void onItemClick(Emergencia item) {
                        moveToDescription(item);
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        }
        ,
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }
    //----------------------PRUEBA RECYCLERVIEW VOLLEY----------------------


    public  void moveToDescription(Emergencia item){
        Intent intent = new Intent(getApplicationContext(),  DescriptionActivity.class);
        intent.putExtra("emergencias",item);
        startActivity(intent);
    }
}