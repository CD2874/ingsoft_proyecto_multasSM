package com.example.cdgal.proyecto_ingsoft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AgregarVehiculo extends AppCompatActivity {

    List<DataItemAgregarVehiculos> lsData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vehiculo);

        lsData = new ArrayList<>();

        lsData.add(new DataItemAgregarVehiculos("Vehiculo 1",'»'));
        lsData.add(new DataItemAgregarVehiculos("Vehiculo 2",'»'));
        lsData.add(new DataItemAgregarVehiculos("Vehiculo 3",'»'));

        ListView listView = (ListView)findViewById(R.id.listView);

        CustomAdapterAgregarVehiculos  adapterAgregarVehiculos = new CustomAdapterAgregarVehiculos(this, R.layout.item_fil_agregar_vehiculos,lsData);

        listView.setAdapter(adapterAgregarVehiculos);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
            }
        });
    }
}
