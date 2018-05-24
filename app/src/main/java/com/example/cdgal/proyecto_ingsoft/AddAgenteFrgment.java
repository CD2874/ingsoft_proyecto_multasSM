package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by cdgal on 26/03/2018.
 */

public class AddAgenteFrgment extends Fragment {

    EditText password;
    Button eyeButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_addagente, container, false);

        String [] valuesP = {"- - -", "Uno", "Dos"};
        Spinner spinnerP = (Spinner) v.findViewById(R.id.privilegio);
        ArrayAdapter<String> adapterP = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesP);
        adapterP.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerP.setAdapter(adapterP);

        password=(EditText) v.findViewById(R.id.password);
        eyeButton=(Button) v.findViewById(R.id.eyeButton);

        hideAndShow();

        return v;
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

    public void llamarMiCuenta(View view){
        Intent intent = new Intent(getActivity(), MiCuentaFragment.class);
        startActivity(intent);
    }
}
