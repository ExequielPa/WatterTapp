package com.example.wattertapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private final RequestQueue requestQueue;
    private static VolleySingleton mInstancia;

    private VolleySingleton(Context context){
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getmInstance(Context context){
        if (mInstancia==null){
            mInstancia = new VolleySingleton(context);
        }
        return mInstancia;
    }

    public RequestQueue getRequestQueue() { return requestQueue;}
}
