package com.comteco.jimenavidal.controlorden.servicio;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by comteco11 on 11/10/2016.
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class GetDataSubscriber extends AsyncTask <Void, Void, Void> {

    // TODO: Crear como variables locales textviews


    TextView tecnico,ordentrab,numOrden,fecha,cliente,tipoCliente,digitador,tipoTrabajo;

    String TAG = "Response";
    String SOAP_ACTION = "";
    String METHOD_SUBS = "getDataSubscriber";
    String NAMESPACE = "http://ws.wsComteco.comteco.com.bo/";
    String URL = "http://192.9.200.150:8080/wsComteco/apiComteco";
    SoapObject resultsuscriber;
    String order_id;


    // TODO: adicionar como paremtros los textviews
    public GetDataSubscriber(String order_id, TextView tecnicos, TextView ordenTrabajo, TextView numeroOrden, TextView fechaEmi, TextView clienteNom, TextView tipoCliente, TextView digitador, TextView tipoTrab ){
        this.order_id = order_id;
        this.tecnico=tecnicos;
        this.ordentrab=ordenTrabajo;
        this.fecha=fechaEmi;
        this.cliente=clienteNom;
        this.tipoCliente=tipoCliente;
        this.digitador=digitador;
        this.tipoTrabajo=tipoTrab;

        // TODO: unir los paremetros textviews con las varialbes locales

    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            //METHOD_SUBS
            SoapObject RequestORDER = new SoapObject(NAMESPACE, METHOD_SUBS);
            RequestORDER.addProperty("order_id",order_id);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // soapEnvelope . dotNet   =   true ;
            soapEnvelope.implicitTypes = true;
            soapEnvelope.setOutputSoapObject(RequestORDER);

            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.debug = true;

            transport.call(SOAP_ACTION, soapEnvelope);


            Log.v("SOAP_RETURN", transport.requestDump);
            Log.v("SOAP_RETURN", transport.responseDump);

            resultsuscriber=   ( SoapObject )   soapEnvelope . bodyIn;


            Log.i(TAG, "Result Celsius: " + resultsuscriber);
        } catch (Exception ex) {
            Log.e(TAG, "Error: " + ex.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // TODO: Acceder a la propiedad dataOrdenes y visualiizar sus atributos
        // SoapObject getProperty0=(SoapObject)resultsuscriber.getProperty(0);
        // SoapObject getDataOrdenes=(SoapObject)getProperty0.getProperty("dataOrdenes");
        // String getCliente=getDataOrdenes.getProperty("nombreCliente").toString();

         SoapObject getProperty0=(SoapObject)resultsuscriber.getProperty(0);
         SoapObject getDataOrdenes=(SoapObject)getProperty0.getProperty("dataOrdenes");
         String getTecnico=getDataOrdenes.getProperty("tecnico").toString();
         String getOrden=getDataOrdenes.getProperty("ot").toString();
         String getTipoTrab=getDataOrdenes.getProperty("tipoTrabajo").toString();

         String getfecha=getDataOrdenes.getProperty("emision").toString();
         String getCliente=getDataOrdenes.getProperty("nombreCliente").toString();
         String getTipoCli=getDataOrdenes.getProperty("tipoCliente").toString();
         String getDigitador=getDataOrdenes.getProperty("digitador").toString();


        // TODO: Visualizar en la Actividad Data Ordenes, pero antes debes recibit como paramentro texviews en el constructor
        //cliente.setText(getCliente);
        //tipoCliente.setText("");
        //TipoTrabajocliente.setText("");

        tecnico.setText(getTecnico);
        ordentrab.setText(getOrden);
        tipoTrabajo.setText(getTipoTrab);

        fecha.setText(getfecha);
        cliente.setText(getCliente);
        tipoCliente.setText(getTipoCli);
        digitador.setText(getDigitador);
    }
}
