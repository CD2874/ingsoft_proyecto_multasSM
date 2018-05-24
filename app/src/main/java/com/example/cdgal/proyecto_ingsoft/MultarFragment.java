package com.example.cdgal.proyecto_ingsoft;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.cdgal.proyecto_ingsoft.R;

import java.util.Calendar;

public class MultarFragment extends Fragment implements View.OnClickListener {

    EditText editTextDate, editTextTime;
    Button buttonDate, buttonTime;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    //EditText monto1, monto2, monto3, total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_multar, container, false);

        String [] valuesSexo = {"- - -", "M", "F"};
        Spinner spinnerS = (Spinner) v.findViewById(R.id.sexo);
        ArrayAdapter<String> adapterS = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesSexo);
        adapterS.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerS.setAdapter(adapterS);

        String [] valuesTipoLicencia = {"- - -","A", "B", "C", "M", "E"};
        Spinner spinnerT = (Spinner) v.findViewById(R.id.tipo_licencia);
        ArrayAdapter<String> adapterT = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesTipoLicencia);
        adapterT.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerT.setAdapter(adapterT);

        editTextDate = (EditText)  v.findViewById(R.id.fecha_infraccion);
        editTextTime = (EditText)  v.findViewById(R.id.hora_infraccion);

        buttonDate = (Button) v.findViewById(R.id.btn_fechaI);
        buttonTime = (Button) v.findViewById(R.id.btn_horaI);

        buttonDate.setOnClickListener(this);
        buttonTime.setOnClickListener(this);

        String [] valuesNegado = {"- - -", "Si", "No"};
        Spinner spinnerN = (Spinner) v.findViewById(R.id.negado);
        ArrayAdapter<String> adapterN = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesNegado);
        adapterN.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerN.setAdapter(adapterN);

        /*monto1 = (EditText)v.findViewById(R.id.monto1);
        monto2 = (EditText)v.findViewById(R.id.monto2);
        monto3 = (EditText)v.findViewById(R.id.monto3);
        total = (EditText)v.findViewById(R.id.total);

        suma();*/

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fechaI:
                Calendar calendarF = Calendar.getInstance();
                int y = calendarF.get(Calendar.YEAR);
                int m = calendarF.get(Calendar.MONTH);
                int d = calendarF.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        editTextDate.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, y, m, d);

                datePickerDialog.show();

                break;

            case R.id.btn_horaI:
                Calendar calendarH = Calendar.getInstance();
                int h = calendarH.get(Calendar.HOUR);
                int min = calendarH.get(Calendar.MINUTE);
                int s = calendarH.get(Calendar.SECOND);

                timePickerDialog = new TimePickerDialog(getActivity(), R.style.Theme_AppCompat_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        editTextTime.setText(h + ":" + m);
                    }
                }, h, min, false);

                timePickerDialog.show();

                break;
        }
    }

    /*public void suma(){
        monto1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try {
                    total.setText(String.valueOf(
                            Double.parseDouble(monto1.getText().toString())
                                    + Double.parseDouble(monto2.getText().toString())
                                    + Double.parseDouble(monto3.getText().toString())
                    ));
                }catch (NumberFormatException e){
                    total.setText("0.00");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        monto2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try {
                    total.setText(String.valueOf(
                            Double.parseDouble(monto1.getText().toString())
                                    + Double.parseDouble(monto2.getText().toString())
                                    + Double.parseDouble(monto3.getText().toString())
                    ));
                }catch (NumberFormatException e){
                    total.setText("0.00");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        monto3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try {
                    total.setText(String.valueOf(
                            Double.parseDouble(monto1.getText().toString())
                                    + Double.parseDouble(monto2.getText().toString())
                                    + Double.parseDouble(monto3.getText().toString())
                    ));
                }catch (NumberFormatException e){
                    total.setText("0.00");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }*/
}
