package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditarMiCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_micuenta);
    }

    public void llamarMiCuenta(View view){
        Intent intent = new Intent(this, ActivityPrincipalAgente.class);
        startActivity(intent);
    }
}
