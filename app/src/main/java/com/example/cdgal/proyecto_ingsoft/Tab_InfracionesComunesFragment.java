package com.example.cdgal.proyecto_ingsoft;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tab_InfracionesComunesFragment extends Fragment {

    List<DataItem> lsData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_infraccionescomunes, container, false);

        lsData = new ArrayList<>();

        lsData.add(new DataItem("Hola mundo 1","Q. 500.00"));
        lsData.add(new DataItem("Hola mundo 2","Q. 600.00"));

        ListView lvdes = (ListView) view.findViewById(R.id.listViewdes);

        ArrayAdapter<String> lvsdes = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, lsData);
        lvdes.setAdapter(lvsdes);

        /*String[] infraccionesComunesDes = {"", "",
                "Por quedarse sin combustible.",
                "Por obstaculizar la vía sin contar con equipamiento básico.",
                "Por estacionarse en doble fila.",
                "Por conducir sin licencia.",
                "Por circular con lincencia vencida.",
                "Por no tener la tarjeta de circulación vigente y el vehículo es llevado al predio municipal.",
                "Si no porta tarjeta de circulación.",
                "Por conducir en estado de ebriedad.",
                "No respetar semáforos en rojo.",
                "Abuso a la velocidad."
        };

        ListView lvdes = (ListView) view.findViewById(R.id.listViewdes);

        ArrayAdapter<String> lvsdes = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, infraccionesComunesDes);
        lvdes.setAdapter(lvsdes);

        String[] infraccionesComunesCos = {"", "",
                "Q.200.00",
                "Q.500.00",
                "Q.500.00",
                "Q.400.00",
                "Q.300.00",
                "Q.500.00",
                "Q.200.00",
                "Q.500.00",
                "Q.200.00",
                "Q.300.00"
        };

        ListView lvcos = (ListView) view.findViewById(R.id.listViewcos);

        ArrayAdapter<String> lvscos = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, infraccionesComunesCos);
        lvcos.setAdapter(lvscos);*/
        return view;
    }
}
