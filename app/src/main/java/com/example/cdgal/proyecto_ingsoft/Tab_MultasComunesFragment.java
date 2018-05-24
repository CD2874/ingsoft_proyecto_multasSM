package com.example.cdgal.proyecto_ingsoft;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tab_MultasComunesFragment extends Fragment {

    List<DataItemMultCom> lstDataInfraCom;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_multascomunes, container, false);

        lstDataInfraCom = new ArrayList<>();
        lstDataInfraCom.add(new DataItemMultCom("\n\n\n","\n\n\n"));
        lstDataInfraCom.add(new DataItemMultCom("Por quedarse sin combustible.","Q.200.00"));
        lstDataInfraCom.add(new DataItemMultCom("Por obstaculizar la vía sin contar con equipamiento básico.","Q.500.00"));
        lstDataInfraCom.add(new DataItemMultCom("Por estacionarse en doble fila.","Q.500.00"));
        lstDataInfraCom.add(new DataItemMultCom("Por conducir sin licencia.","Q.400.00"));
        lstDataInfraCom.add(new DataItemMultCom("Por circular con lincencia vencida.","Q.300.00"));
        lstDataInfraCom.add(new DataItemMultCom("Por no tener la tarjeta de circulación vigente y el vehículo es llevado al predio municipal.","Q.500.00"));
        lstDataInfraCom.add(new DataItemMultCom("Si no porta tarjeta de circulación.","Q.200.00"));
        lstDataInfraCom.add(new DataItemMultCom("Por conducir en estado de ebriedad.","Q.500.00"));
        lstDataInfraCom.add(new DataItemMultCom("No respetar semáforos en rojo.","Q.200.00"));
        lstDataInfraCom.add(new DataItemMultCom("Abuso a la velocidad.","Q.300.00"));

        ListView listView = (ListView)view.findViewById(R.id.listInfraCom);
        CustomAdapterMultCom adapter = new CustomAdapterMultCom(this.getActivity(),R.layout.item_fil_mult_com,lstDataInfraCom);
        listView.setAdapter(adapter);

        return view;
    }
}
