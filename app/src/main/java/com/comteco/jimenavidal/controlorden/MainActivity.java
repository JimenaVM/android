package com.comteco.jimenavidal.controlorden;


import android.app.ProgressDialog;
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

                AsyncCallWS task = new AsyncCallWS(res);
                task.execute(getLogin);


                   Intent ingresar= new Intent(MainActivity.this, ListaOrden.class);
                   ingresar.putExtra("nombre",getLogin+"");
                    startActivity(ingresar);


            }
        });

    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void> {

        String SOAP_ACTION = "";
        String METHOD_NAME = "getDataOrdenesGral";
        String NAMESPACE = "http://ws.wsComteco.comteco.com.bo/";
        String URL = "http://192.9.200.150:8080/wsComteco/apiComteco";
        TextView name;

        public AsyncCallWS(TextView paramName) {
            name = paramName;
        }

        @Override
        protected Void doInBackground(String... params) {

            String tecnico = params[0];

            Log.i(TAG, "doInBackground");
            try {
                SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
                Request.addProperty("login",tecnico);


                SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                // soapEnvelope . dotNet   =   true ;
                soapEnvelope.implicitTypes = true;
                soapEnvelope.setOutputSoapObject(Request);

                HttpTransportSE transport = new HttpTransportSE(URL);
                transport.debug = true;

                transport.call(SOAP_ACTION, soapEnvelope);


                Log.v("SOAP_RETURN", transport.requestDump);
                Log.v("SOAP_RETURN", transport.responseDump);


                resultString   =   ( SoapObject )   soapEnvelope . bodyIn;


                Log.i(TAG, "Result Celsius: " + resultString);
            } catch (Exception ex) {
                Log.e(TAG, "Error: " + ex.getMessage());
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            Log.i(TAG, "onPostExecute");

            SoapObject getProperty0=(SoapObject)resultString.getProperty(0);
            SoapObject getDataOrdenes=(SoapObject)getProperty0.getProperty("dataOrdenesGral");
            String getCliente=getDataOrdenes.getProperty("tecnico").toString();

            Log.d(TAG,"COUNT:"+resultString.getPropertyCount());
            Log.d(TAG,"data:"+resultString.getProperty(0));
            //Log.d(TAG,"dataOrdenes:"+getCliente);



           // name.setText(getCliente);
            //Toast.makeText(MainActivity.this, "Response:  " + resultString.toString(), Toast.LENGTH_LONG).show();
        }


    }

}
