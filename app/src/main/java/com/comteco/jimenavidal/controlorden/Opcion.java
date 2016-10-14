package com.comteco.jimenavidal.controlorden;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Jimena Vidal on 02/09/2016.
 */
public class Opcion  extends Fragment implements ImageView.OnClickListener{

    ImageView prod,eq,act,da;
    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }



    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {


        View view = null;
        view = inflater.inflate(R.layout.opcion, container, false);
        prod = (ImageView) view.findViewById(R.id.imgProd);
        prod.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                Intent bu = new Intent(getActivity(), Detalle_Producto.class);

                startActivity(bu);

            }


        });
        eq = (ImageView) view.findViewById(R.id.imgEqu);
        eq.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                Intent bu = new Intent(getActivity(), Detalle_Equipo.class);
                startActivity(bu);

            }


        });
        act = (ImageView) view.findViewById(R.id.imgAct);
        act.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                Intent bu = new Intent(getActivity(), DetalleActividad.class);
                startActivity(bu);

            }


        });
        da = (ImageView) view.findViewById(R.id.imgDat);
        da.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {

                Intent bu = new Intent(getActivity(), DetalleDanio.class);
                startActivity(bu);

            }


        });
        return view;

    }

    @Override
    public void onClick(View v) {

    }
}
