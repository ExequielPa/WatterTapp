package com.example.wattertapp.Activitys;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wattertapp.Http;
import com.example.wattertapp.LocalStorage;
import com.example.wattertapp.Model.Emergencia;
import com.example.wattertapp.Model.Usuario;
import com.example.wattertapp.R;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText usuario,password;
    Button button;
    String val_usu,val_pass;
    LocalStorage localStorage;

    private ArrayList<String> usuarios ,Contrasena;

    RequestQueue queue;

    TextView headeruser,headerrut;

    String URL1="https://api.teamproyecto.cl/usuario";

    int REQUEST_CODE=200;
    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarios = new ArrayList<>();
        Contrasena = new ArrayList<>();

        usuario = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);

        button = findViewById(R.id.btnLogin);

        localStorage = new LocalStorage(MainActivity.this);

        jsonArrayRequestUser();

        verificarPermisos();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),PrincipalActivity.class);
                startActivity(intent);
            }
        });
    }

    public void checkLogin(){
        val_usu = usuario.getText().toString();
        val_pass = password.getText().toString();
        if (val_usu.isEmpty() && val_pass.isEmpty()){
            alert("Email y password son requeridos");
        }
    }

    public void alert(String s){
        new AlertDialog.Builder(this)
            .setTitle("Fallo")
            .setMessage(s)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
    }

    private void sendLogin(){
        JSONObject params = new JSONObject();
        try {
            params.put("email",usuario);
            params.put("password",password);
        }catch (JSONException e){
            e.printStackTrace();
        }

        String data = params.toString();
        String url = getString(R.string.API_SERVER)+"/login";

        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(MainActivity.this,URL1);
                http.setMethod("POST");
                http.setData(data);
                http.send();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String token = response.getString("token");
                                localStorage.setToken(token);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else if (code == 422){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String msg = response.getString("messgase");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else if (code == 401){
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }).start();
    }

    /*public void ValidarUsuario(){
        usuarios.clear();
        String URL1="https://api.teamproyecto.cl/usuario";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int size = response.length();
                for (int i=0; i < size; i++) {
                    try {
                        JSONObject emergObject =  new JSONObject(response.get(i).toString());
                        String rut = emergObject.getString("rut_voluntario");
                        String pass = emergObject.getString("contrasena_usuario");
                        usuarios.add(rut);
                        Contrasena.add(pass);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ,
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue = Volley.newRequestQueue(getApplicationContext());
        //00queue.add(jsonArrayRequest);
    }*/
    private void jsonArrayRequestUser() {
        usuarios.clear();
        Contrasena.clear();
        String URL1 = "https://api.teamproyecto.cl/usuario";
        JsonArrayRequest jsonArrayRequestGrifo = new JsonArrayRequest(
                Request.Method.GET,
                URL1,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int size = response.length();
                        for (int i=0; i < size;i++){
                            try {
                                JSONObject jsonObject = new JSONObject(response.get(i).toString());
                                String rut = jsonObject.getString("rut_voluntario");
                                String pass = jsonObject.getString("contrasena_usuario");
                                usuarios.add(rut);
                                Contrasena.add(pass);
                            } catch (JSONException e) {
                                e.printStackTrace();
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
        queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequestGrifo);
    }

    public void compar(String usua, String pass){
        for (int i=0;i<usuarios.size();i++){
            if (usua == usuarios.get(i)){
                for (int j=0;j<Contrasena.size();j++){
                    if (pass == Contrasena.get(i)){

                    }
                }
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verificarPermisos() {
        int permi = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int permi2 = ContextCompat.checkSelfPermission(this,Manifest.permission.CAMERA);

        if (permi == PackageManager.PERMISSION_GRANTED
                //&& permi2 == PackageManager.PERMISSION_GRANTED
        ){
            Toast.makeText(this, "Tienes los permisos", Toast.LENGTH_SHORT).show();
        }else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA},REQUEST_CODE);
        }
    }

    private void guardarPreferencias(){
        SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("usuario",val_usu);
        editor.putString("password",val_pass);
        editor.putBoolean("session",true);
        editor.commit();
    }

    private void recuperarPreferencias(){
        SharedPreferences preferences= getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        usuario.setText(preferences.getString("usuario",""));
            password.setText(preferences.getString("password",""));
    }
}