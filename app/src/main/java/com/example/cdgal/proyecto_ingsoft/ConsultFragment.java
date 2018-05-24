package com.example.cdgal.proyecto_ingsoft;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ConsultFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_consult, container, false);

        String [] valuesTipo = {"- # -","A", "B", "C", "M", "E"};
        Spinner spinnerT = (Spinner) v.findViewById(R.id.tipo);
        ArrayAdapter<String> adapterT = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesTipo);
        adapterT.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerT.setAdapter(adapterT);

        return v;
    }
}
