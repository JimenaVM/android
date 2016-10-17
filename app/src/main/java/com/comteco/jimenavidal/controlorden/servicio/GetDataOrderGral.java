package com.comteco.jimenavidal.controlorden.servicio;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by gonzalopro on 10/17/16.
 */

public class GetDataOrderGral extends AsyncTask<String, Void, Void> {

    String SOAP_ACTION = "";
    String METHOD_NAME = "getDataOrdenesGral";
    String NAMESPACE = "http://ws.wsComteco.comteco.com.bo/";
    String URL = "http://192.9.200.150:8080/wsComteco/apiComteco";

    Context context;
    ProgressDialog progressDialog;
    TextView name;

    SoapObject resultString;

    public GetDataOrderGral(Context context, TextView paramName) {
        this.context  = context;
        name = paramName;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context, "Conectando con el Servidor", "Obteniendo los Orden...",true);
    }

    @Override
    protected Void doInBackground(String... params) {

        String tecnico = params[0];

        Log.i("ASYNC", "doInBackground");
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


            Log.i("ASYNC", "Result Celsius: " + resultString);
        } catch (Exception ex) {
            Log.e("ASYNC", "Error: " + ex.getMessage());
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        Log.i("ASYNC", "onPostExecute");
        progressDialog.dismiss();

        SoapObject getProperty0=(SoapObject)resultString.getProperty(0);
        SoapObject getDataOrdenes=(SoapObject)getProperty0.getProperty("dataOrdenesGral");
        String getCliente=getDataOrdenes.getProperty("tecnico").toString();

        Log.d("ASYNC","COUNT:"+resultString.getPropertyCount());
        Log.d("ASYNC","data:"+resultString.getProperty(0));
        Log.d("ASYNC","dataOrdenes:"+getCliente);



        // name.setText(getCliente);
        //Toast.makeText(MainActivity.this, "Response:  " + resultString.toString(), Toast.LENGTH_LONG).show();
    }
}
