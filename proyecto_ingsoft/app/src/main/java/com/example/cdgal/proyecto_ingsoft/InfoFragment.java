package com.example.cdgal.proyecto_ingsoft;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InfoFragment extends Fragment {

    TextView info;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        info = (TextView)view.findViewById(R.id.info);


        info.setText("       ARTICULO 190 TRASLADO DE VEHÍCULOS\n" +
                "             INFRACTORES AL DEPÓSITO\n\n" +
                "Treinta días después de impuesta la multa sin que la misma se haya cancelado, " +
                "la autoridad de tránsito solicitará el traslado del vehículo infractor al depósito correspondiente\n\n\n\n" +
                " ARTS. 189 y 190 DEL ACUERDO GUBERNATIVO\n" +
                "   NO. 273-98, REGLAMENTO DE TRÁNSITO\n\n" +
                "Una boleta de infracción puede ser pagada dentro de los próximos 5 DÍAS HÁBILES a partir de su imposición" +
                "en la municipalidad o bancos autorizados con descuentos del 25%.\n\nDepupes de esa fecha se pagará el monto total " +
                "de la multa más intereses por mora calculado al 20% anual.");

        return view;
    }
}
