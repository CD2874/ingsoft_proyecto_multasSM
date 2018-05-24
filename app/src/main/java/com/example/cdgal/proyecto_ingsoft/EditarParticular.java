package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EditarParticular extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_particular);
    }

    public void llamarMiCuenta(View view){
        Intent intent = new Intent(this, ActivityPrincipal.class);
        startActivity(intent);
    }
}
