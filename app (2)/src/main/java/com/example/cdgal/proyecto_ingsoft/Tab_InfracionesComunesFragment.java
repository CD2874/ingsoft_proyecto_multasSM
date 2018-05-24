package com.example.cdgal.proyecto_ingsoft;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tab_InfracionesComunesFragment extends Fragment {

    List<DataItemInfraCom> lstDataInfraCom;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_infraccionescomunes, container, false);

        lstDataInfraCom = new ArrayList<>();
        lstDataInfraCom.add(new DataItemInfraCom("\n\n\n","\n\n\n"));
        lstDataInfraCom.add(new DataItemInfraCom("Por quedarse sin combustible.","Q.200.00"));
        lstDataInfraCom.add(new DataItemInfraCom("Por obstaculizar la vía sin contar con equipamiento básico.","Q.500.00"));
        lstDataInfraCom.add(new DataItemInfraCom("Por estacionarse en doble fila.","Q.500.00"));
        lstDataInfraCom.add(new DataItemInfraCom("Por conducir sin licencia.","Q.400.00"));
        lstDataInfraCom.add(new DataItemInfraCom("Por circular con lincencia vencida.","Q.300.00"));
        lstDataInfraCom.add(new DataItemInfraCom("Por no tener la tarjeta de circulación vigente y el vehículo es llevado al predio municipal.","Q.500.00"));
        lstDataInfraCom.add(new DataItemInfraCom("Si no porta tarjeta de circulación.","Q.200.00"));
        lstDataInfraCom.add(new DataItemInfraCom("Por conducir en estado de ebriedad.","Q.500.00"));
        lstDataInfraCom.add(new DataItemInfraCom("No respetar semáforos en rojo.","Q.200.00"));
        lstDataInfraCom.add(new DataItemInfraCom("Abuso a la velocidad.","Q.300.00"));

        ListView listView = (ListView)view.findViewById(R.id.listInfraCom);
        CustomAdapterInfraCom adapter = new CustomAdapterInfraCom(this.getActivity(),R.layout.item_fil_infra_com,lstDataInfraCom);
        listView.setAdapter(adapter);

        return view;
    }
}
