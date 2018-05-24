package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cdgal.proyecto_ingsoft.IniciarSesion;
import com.example.cdgal.proyecto_ingsoft.R;

public class AddParticular extends AppCompatActivity {

    EditText password;
    Button eyeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addparticular);
        setTitle(R.string.title_activity_crear_usuario);

        password=(EditText) findViewById(R.id.password);
        eyeButton=(Button) findViewById(R.id.eyeButton);

        hideAndShow();

    }

    public void hideAndShow(){
        eyeButton.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch ( event.getAction() ) {
                            case MotionEvent.ACTION_DOWN:
                                password.setInputType(InputType.TYPE_CLASS_TEXT);
                                break;
                            case MotionEvent.ACTION_UP:
                                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                                break;
                        }
                        return true;
                    }
                }
        );
    }

    public void llamarIniciarSesion(View view){
        Intent intent = new Intent(this, IniciarSesion.class);
        startActivity(intent);
    }
}
