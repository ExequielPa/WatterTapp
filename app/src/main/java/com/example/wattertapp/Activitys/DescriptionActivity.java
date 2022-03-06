package com.example.wattertapp.Activitys;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.wattertapp.Adapter.AdapterEmergencia;
import com.example.wattertapp.Model.Emergencia;
import com.example.wattertapp.Model.EmergenciaL;
import com.example.wattertapp.R;
import com.example.wattertapp.VolleySingleton;
import com.getbase.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity implements ActivityDialog.Custom_DialogInterface{

    //variables del entorno de descripcion
    TextView Titulo,Ciudad,Estado,Codigo,Fecha,Comentario;
    Button cambio,tomar,qr;
    Emergencia adapter = null;
    FloatingActionButton fb1,fb2,fb3;
    private static final String URL="https://api.teamproyecto.cl/emergencia";
    List<EmergenciaL> valEmergencia;
    private String val1,val2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        //extractEmerg();

        final Object object1 = getIntent().getSerializableExtra("emergencias");
        if (object1 instanceof Emergencia){
            adapter = (Emergencia) object1;
        }

        // ----------------------- Relacion de ID con la vista -----------------------
        Titulo = findViewById(R.id.Titulo);
        Ciudad = findViewById(R.id.Ciudad);
        Estado = findViewById(R.id.Estado);
        Fecha = findViewById(R.id.Fecha);
        Codigo = findViewById(R.id.Codigo);
        Comentario = findViewById(R.id.Comentario);


        // ----------------------- Modificacion datos a mostrar  -----------------------
        if (adapter != null){
            Titulo.setText(adapter.getDescripcion_emer());
            Ciudad.setText(adapter.getDireccion());
            Fecha.setText(adapter.getId_cod_emer());
            Estado.setText(adapter.getEstado_emergencia());
            Codigo.setText(adapter.getNombre_compania());

        }

        // ----------------------- Botones flotantes -----------------------
        fb1 = findViewById(R.id.fab_action1X);
        fb2 = findViewById(R.id.fab_action2X);
        fb3 = findViewById(R.id.fab_action3X);

        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cambio de Mando", Toast.LENGTH_SHORT).show();
                openDialogX(v);
            }
        });

        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Toma de Emergencia", Toast.LENGTH_SHORT).show();
                openDialog(v);
            }
        });

        fb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Vista mapa", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("emer1",val1);
                intent.putExtra("emer2",val2);
                startActivity(intent);
            }
        });
        // ----------------------- Botones flotantes -----------------------


        // ----------------------- Vista del QR -----------------------
        qr = findViewById(R.id.btnScanner);

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ScannerActivity.class);
                startActivity(intent);
            }
        });
        // ----------------------- Vista del QR -----------------------
    }

    private void extractEmerg() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        EmergenciaL em = new EmergenciaL();

                        em.setId_emergencia(jsonObject.getString("id_emergencia"));
                        em.setLatitud(jsonObject.getString("latitud"));
                        em.setLonguitud(jsonObject.getString("longuitud"));

                        valEmergencia.add(em);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void Esta(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject emergObject = response.getJSONObject(i);

                        EmergenciaL emer = new EmergenciaL();

                        emer.setId_emergencia(emergObject.getString("id_emergencia"));
                        emer.setDescripcion_emer(emergObject.getString("descripcion_emer"));
                        emer.setFecha_inicio(emergObject.getString("fecha_inicio"));
                        emer.setHora_inicio(emergObject.getString("hora_inicio"));
                        emer.setFecha_termino(emergObject.getString("fecha_termino"));
                        emer.setHora_termino(emergObject.getString("hora_termino"));
                        emer.setLonguitud(emergObject.getString("latitud"));
                        emer.setLatitud(emergObject.getString("longuitud"));
                        emer.setDireccion(emergObject.getString("direccion"));
                        emer.setId_estado_eme(emergObject.getString("id_estado_eme"));
                        emer.setId_responsable(emergObject.getString("id_responsable"));
                        emer.setId_categoria(emergObject.getString("id_categoria"));

                        valEmergencia.add(emer);
                        //valEmergencia.sort(Collections.reverseOrder());
                    } catch (JSONException  e) {
                        e.printStackTrace();
                    }
                }

                /*
                AdapterEmergencia adapter = new AdapterEmergencia(valEmergencia, getApplicationContext(), new AdapterEmergencia.OnItemClickListener() {
                    @Override
                    public void onItemClick(Emergencia item) {
                        moveToDescription(item);
                    }
                });*/
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

        for (int i = 0;i<valEmergencia.size();i++){
            if (valEmergencia.get(i).getId_emergencia() == adapter.getId_emergencia()) {
                val1 = valEmergencia.get(i).getLatitud();
                val2 = valEmergencia.get(i).getLonguitud();
            }
        }
    }

    public  void moveToDescription(Emergencia item){
        Intent intent = new Intent(getApplicationContext(),  MapsActivity.class);
        intent.putExtra("emergencias",item);
        startActivity(intent);
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void openDialog(View view){
        ActivityDialog dialog = new ActivityDialog();
        dialog.show(getSupportFragmentManager(),"Test");
    }

    @Override
    public void applyTexts(String EDT1, String EDT2) {
        Comentario.setText(EDT1);
        Comentario.setText(EDT2);
    }


    public void openDialogX(View view){
        ActivityDialog1 dialog1 = new ActivityDialog1();
        dialog1.show(getSupportFragmentManager(),"Test");
    }

}