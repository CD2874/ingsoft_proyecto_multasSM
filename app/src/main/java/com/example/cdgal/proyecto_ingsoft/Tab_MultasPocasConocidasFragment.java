package com.example.cdgal.proyecto_ingsoft;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tab_MultasPocasConocidasFragment extends Fragment {

    List<DataItemMultCono> lstDataFaltCono;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view2 = inflater.inflate(R.layout.fragment_tab_multaspococonocidas, container, false);

        lstDataFaltCono = new ArrayList<>();
        lstDataFaltCono.add(new DataItemMultCono("\n\n\n","\n\n\n"));
        lstDataFaltCono.add(new DataItemMultCono("Conducir con auriculares conectados o hablar por teléfono.","Q.100.00"));
        lstDataFaltCono.add(new DataItemMultCono("Bocinazos exagerados o innecesarios.","Q.200.00"));
        lstDataFaltCono.add(new DataItemMultCono("Estacionarse a más de 25cm. del bordillo.","Q.200.00"));
        lstDataFaltCono.add(new DataItemMultCono("Reparaciones mecánicas de más de dos horas en la vía pública.","Q.200.00"));
        lstDataFaltCono.add(new DataItemMultCono("Tirar basura desde el vehículo.","Q.300.00"));
        lstDataFaltCono.add(new DataItemMultCono("No ceder paso a peatones.","Q.300.00"));
        lstDataFaltCono.add(new DataItemMultCono("Instalar objetos que parezcan señales de tránsito.","Q.400.00"));
        lstDataFaltCono.add(new DataItemMultCono("Transportar personas en lugares exteriores en transporte público.","Q.500.00"));
        lstDataFaltCono.add(new DataItemMultCono("Recoger o dejar pasajeros en paradas no autorizadas.","Q.500.00"));
        lstDataFaltCono.add(new DataItemMultCono("No portar chaleco, ni casco en motocicleta o circular en lado izquierdo.","Q.1,00.00"));
        lstDataFaltCono.add(new DataItemMultCono("Faltar el respeto a un agente de tránsito.","Q.1,000.00"));
        lstDataFaltCono.add(new DataItemMultCono("Colocar obstáculos que entorpezcan la vía.","Q.5,000.00"));
        lstDataFaltCono.add(new DataItemMultCono("No atender el requerimiento de los vehículos de emergencia.","Q.25,000.00"));
        lstDataFaltCono.add(new DataItemMultCono("Circular con el escape abierto o llantas lisas.","Q.200.00"));

        ListView listView2 = (ListView)view2.findViewById(R.id.listFaltCono);
        CustomAdaptermMultCono adapter = new CustomAdaptermMultCono(this.getActivity(),R.layout.item_fil_mult_cono,lstDataFaltCono);
        listView2.setAdapter(adapter);

        return view2;
    }
}
