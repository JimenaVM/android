package com.comteco.jimenavidal.controlorden;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by Jimena Vidal on 02/09/2016.
 */
public class ListaOrden extends FragmentActivity {

    String nombreTecnico;
    TextView tecnico;
    String TAG = "Response";
    SoapObject resultString;
    ListView listView;
     String[] clientes;
    String orden,tipo,estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_orden);

        nombreTecnico=getIntent().getStringExtra("nombre");
        tecnico=(TextView)findViewById(R.id.txtTecnico);
        tecnico.setText(nombreTecnico);
        listView=(ListView)findViewById(R.id.ltvOrden);

        listView.setOnItemClickListener(new ListClickHandler());




        ImageView salir=(ImageView) findViewById(R.id.imgSalirr);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar= new Intent(ListaOrden.this, OrdenTrabajo.class);
                ingresar.putExtra("nombre",tecnico+"");
                ingresar.putExtra("nroOrden",orden);
                startActivity(ingresar);
            }
        });
        AsyncCallWS task=new AsyncCallWS(tecnico);
        task.execute(nombreTecnico);




    }
    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub

            String text = nombreTecnico.toString();
            Intent intent = new Intent(ListaOrden.this, OrdenTrabajo.class);
            intent.putExtra("selected-item", text);
            startActivity(intent);

        }

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
            clientes = new String[1];
            SoapObject getProperty0=(SoapObject)resultString.getProperty(0);
            SoapObject getDataOrdenes=(SoapObject)getProperty0.getProperty("dataOrdenesGral");
            String getCliente=getDataOrdenes.getProperty("tecnico").toString();

            //inicia datos para listview
            for ( int i=0;i<1;i++ ) {
                orden = getDataOrdenes.getProperty("orden").toString();
                tipo = getDataOrdenes.getProperty("tipoTrabajo").toString();
                estado = getDataOrdenes.getProperty("estadoOt").toString();



                clientes[i]="Nro orden: "+orden+""+tipo+"-"+estado;
               //lista entrada




            }
            //hasta aqui

            Log.d(TAG,"COUNT:"+resultString.getPropertyCount());
            Log.d(TAG,"data:"+resultString.getProperty(0));

            //Log.d(TAG,"dataOrdenes:"+getCliente);



          //  name.setText(getCliente);

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(ListaOrden.this,
                                             android.R.layout.simple_list_item_1, clientes);

            listView.setAdapter(adaptador);






        //Toast.makeText(MainActivity.this, "Response:  " + resultString.toString(), Toast.LENGTH_LONG).show();
        }


    }

}
