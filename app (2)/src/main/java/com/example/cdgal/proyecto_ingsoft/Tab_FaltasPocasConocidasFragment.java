package com.example.cdgal.proyecto_ingsoft;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tab_FaltasPocasConocidasFragment extends Fragment {
    //List<DataItemInfraCom> lstData;

    //List<DataItemInfraCom> lstDataFaltCono;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view2 = inflater.inflate(R.layout.fragment_tab_faltaspococonocidas, container, false);

        /*lstDataFaltCono = new ArrayList<>();
        lstDataFaltCono.add(new DataItemInfraCom("\n\n\n","\n\n\n"));
        lstDataFaltCono.add(new DataItemInfraCom("fsdfPor quedarse sin combustible.","Q.200.00"));
        lstDataFaltCono.add(new DataItemInfraCom("Por obstaculizar la vía sin contar con equipamiento básico.","Q.500.00"));
        lstDataFaltCono.add(new DataItemInfraCom("Por estacionarse en doble fila.","Q.500.00"));
        lstDataFaltCono.add(new DataItemInfraCom("Por conducir sin licencia.","Q.400.00"));
        lstDataFaltCono.add(new DataItemInfraCom("Por circular con lincencia vencida.","Q.300.00"));
        lstDataFaltCono.add(new DataItemInfraCom("Por no tener la tarjeta de circulación vigente y el vehículo es llevado al predio municipal.","Q.500.00"));
        lstDataFaltCono.add(new DataItemInfraCom("Si no porta tarjeta de circulación.","Q.200.00"));
        lstDataFaltCono.add(new DataItemInfraCom("Por conducir en estado de ebriedad.","Q.500.00"));
        lstDataFaltCono.add(new DataItemInfraCom("No respetar semáforos en rojo.","Q.200.00"));
        lstDataFaltCono.add(new DataItemInfraCom("Abuso a la velocidad.","Q.300.00"));

        ListView listView = (ListView)view2.findViewById(R.id.listFaltCono);
        CustomAdapterInfraCom adapter = new CustomAdapterInfraCom(this.getActivity(),R.layout.item_fil_falt_cono,lstDataFaltCono);
        listView.setAdapter(adapter);*/

        return view2;
    }
}
