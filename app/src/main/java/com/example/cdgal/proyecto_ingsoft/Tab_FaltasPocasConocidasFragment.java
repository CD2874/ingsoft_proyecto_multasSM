package com.example.cdgal.proyecto_ingsoft;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.DataInput;
import java.util.ArrayList;
import java.util.List;

public class Tab_FaltasPocasConocidasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view2 = inflater.inflate(R.layout.fragment_tab_infraccionescomunes, container, false);
        String[] faltasConocidasDes = {"", "",
                "Conducir con auriculares conectados o hablar por teléfono.",
                "Bocinazos exagerados o innecesarios.",
                "Estacionarse a más de 25cm. del bordillo.",
                "Reparaciones mecánicas de más de dos horas en la vía pública.",
                "Tirar basura desde el vehículo.",
                "No ceder paso a peatones.",
                "Instalar objetos que parezcan señales de tránsito.",
                "Transportar personas en lugares exteriores en transporte público.",
                "Recoger o dejar pasajeros en paradas no autorizadas.",
                "No portar chaleco, ni casco en motocicleta o circular en lado izquierdo.",
                "Faltar el respeto a un agente de tránsito.",
                "Colocar obstáculos que entorpezcan la vía.",
                "No atender el requerimiento de los vehículos de emergencia.",
                "Circular con el escape abierto o llantas lisas."
        };

        ListView lv2des = (ListView) view2.findViewById(R.id.listViewdes);

        ArrayAdapter<String> lv2sdes = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, faltasConocidasDes);
        lv2des.setAdapter(lv2sdes);

        String[] faltasConocidasCos = {"", "",
                "Q.100.00",
                "Q.200.00",
                "Q. 200.00",
                "Q.200.00",
                "Q.300.00",
                "Q.300.00",
                "Q.400.00",
                "Q.500.00",
                "Q.500.00",
                "Q.1,000.00",
                "Q.1,000.00",
                "Q5,000.00",
                "Q.25,000.00",
                "Q.200.00"
        };

        ListView lv2cos = (ListView) view2.findViewById(R.id.listViewcos);

        ArrayAdapter<String> lvs2cos = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, faltasConocidasCos);
        lv2cos.setAdapter(lvs2cos);
        return view2;
    }
}
