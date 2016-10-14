package com.comteco.jimenavidal.controlorden;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.comteco.jimenavidal.controlorden.servicio.GetDataOrdenes;

/**
 * Created by Jimena Vidal on 02/09/2016.
 */
public class Detalle extends Fragment  {
    String nombreTec;
    TextView tecnico,numTrabajo,tipoTrabajo,fechaEmision,Observacion,Cliente,TipoCliente,Digitador,numContacto;
    String numOrden;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //nombreTec=getIntent().getStringExtra("nombre");
        Bundle bundle =getId();
        String valorRecibido= getIntent().getStringExtra("dato_bundle");


    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.detalle, container, false);

        //return inflater.inflate(R.layout.detalle, container, false);

        tecnico=(TextView)rootView.findViewById(R.id.txtTecn);
        numTrabajo=(TextView)rootView.findViewById(R.id.txtOrdTrab);
        tipoTrabajo=(TextView)rootView.findViewById(R.id.txtTipoTrab);
        fechaEmision=(TextView)rootView.findViewById(R.id.txtFecEmision);
        TipoCliente=(TextView)rootView.findViewById(R.id.txtTCli);
        Digitador=(TextView)rootView.findViewById(R.id.txtDig);
        Cliente=(TextView)rootView.findViewById(R.id.txtCliente);

        GetDataOrdenes n=new GetDataOrdenes();
        tecnico.setText(n.tecnico);
        numTrabajo.setText(ordenTrabajo);






        return rootView;


    }



}
