package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NuevoVehiculo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_vehiculo);


    }

    public void llamarAgregarVehiculo(View view){
        Intent intent = new Intent(this, AgregarVehiculo.class);
        startActivity(intent);
    }
}
