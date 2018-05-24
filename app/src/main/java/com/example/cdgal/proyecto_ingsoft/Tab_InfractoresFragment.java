package com.example.cdgal.proyecto_ingsoft;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tab_InfractoresFragment extends Fragment {

    List<DataItemInfrac> lstDataInfrac;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_infractores, container, false);

        lstDataInfrac = new ArrayList<>();
        lstDataInfrac.add(new DataItemInfrac("\n\n\n","\n\n\n"));
        lstDataInfrac.add(new DataItemInfrac("A A A.","Q.100.00"));
        lstDataInfrac.add(new DataItemInfrac("A A A.","Q.200.00"));

        ListView listView = (ListView)view.findViewById(R.id.listInfrac);
        CustomAdapterInfrac adapter = new CustomAdapterInfrac(this.getActivity(),R.layout.item_fil_infrac,lstDataInfrac);
        listView.setAdapter(adapter);

        return view;
    }
}
