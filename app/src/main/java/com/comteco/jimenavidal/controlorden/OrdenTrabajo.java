package com.comteco.jimenavidal.controlorden;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

/**
 * Created by Jimena Vidal on 02/09/2016.
 */
public class OrdenTrabajo extends FragmentActivity {
    String nombreTec,numOrden;
    private FragmentTabHost tabHosts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orden_trab);
        nombreTec=getIntent().getStringExtra("nombre");
        numOrden=getIntent().getStringExtra("nroOrden");


        Bundle bundle = new Bundle();
        bundle.putString("ordenTrab", numOrden );
        bundle.putString("nombreTecnico",nombreTec);




        tabHosts= (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHosts.setup(this,
                getSupportFragmentManager(),android.R.id.tabcontent);
        tabHosts.addTab(tabHosts.newTabSpec("tab1").setIndicator("DETALLE"),
               Detalle.class, null);
        tabHosts.addTab(tabHosts.newTabSpec("tab2").setIndicator("OPCIONES"),
                Opcion.class, null);
        tabHosts.addTab(tabHosts.newTabSpec("tab3").setIndicator("EJECUTADO"),
                Ejecutado.class, null);



    }

}
