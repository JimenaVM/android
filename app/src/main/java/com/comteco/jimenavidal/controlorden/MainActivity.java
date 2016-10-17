package com.comteco.jimenavidal.controlorden;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Array;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String TAG = "Response";
    Usuario user;
    String getLogin;
    String getPass;
    SoapObject resultString;


    String usus, contr, lg, ps;

    ImageView ingresar;
    EditText login, pass;
    TextView res;
    Handler handler = new Handler();
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (EditText) findViewById(R.id.txtLogin);
        pass = (EditText) findViewById(R.id.txtPass);

        res = (TextView) findViewById(R.id.txtRes);


        ingresar = (ImageView) findViewById(R.id.imgIngresar);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLogin = login.getText().toString().toUpperCase();
                getPass = pass.getText().toString();

                Intent ingresar= new Intent(MainActivity.this, ListaOrden.class);
                ingresar.putExtra("nombre",getLogin);
                startActivity(ingresar);


            }
        });

    }


}
