package com.example.dell.restapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    DatePickerDialog datePickerDialogI;
    DatePickerDialog datePickerDialogF;
    int year, moth, day;
    Button fecha_i,fecha_f,guardar;
    Calendar calendar;

    EditText cantidad,precio,nombre;
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fecha_i = (Button)findViewById(R.id.btn_fi);
        fecha_f = (Button)findViewById(R.id.btn_ff);
        guardar = (Button)findViewById(R.id.btnguardar);


        cantidad = (EditText)findViewById(R.id.cantidad);
        precio = (EditText)findViewById(R.id.precio);
        total = (TextView)findViewById(R.id.total);
        nombre = (EditText)findViewById(R.id.nProducto);


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        moth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialogI = DatePickerDialog.newInstance(MainActivity.this,year,moth,day);
        datePickerDialogF = DatePickerDialog.newInstance(MainActivity.this,year,moth,day);


        fecha_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               datePickerDialogI.setThemeDark(false);
               datePickerDialogI.showYearPickerFirst(false);
               datePickerDialogI.setAccentColor(Color.parseColor("#009688"));
                datePickerDialogI.setTitle("fecha inicial");
                datePickerDialogI.show(getFragmentManager(),"datePickerDialogI");
            }
        });

        fecha_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialogF.setThemeDark(false);
                datePickerDialogF.showYearPickerFirst(false);
                datePickerDialogF.setAccentColor(Color.parseColor("#009688"));
                datePickerDialogF.setTitle("fecha final");
                datePickerDialogF.show(getFragmentManager(),"datePickerDialogF");
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inventario inventario = new Inventario();
                inventario.setCantidad(Integer.parseInt(cantidad.getText().toString()));
                inventario.setPrecio(Double.parseDouble(precio.getText().toString()));
                inventario.setTotal(Double.parseDouble(total.getText().toString()));
                inventario.setFecha_i("2017-04-10");
                inventario.setFecha_f("2017-04-29");
                inventario.setNombre(nombre.getText().toString());
                save(inventario);
            }
        });


         precio.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 try {
                     total.setText(String.valueOf(
                             Integer.parseInt(cantidad.getText().toString())
                                     * Double.parseDouble(precio.getText().toString())

                     ));
                 }catch (NumberFormatException e){
                     total.setText("0");
                 }
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
           if(view.getTag().equals("datePickerDialogI")){
               Log.d("fecha inicial",year+" - "+monthOfYear+" - "+dayOfMonth);
           }else{
               Log.d("fecha final",year+" - "+monthOfYear+" - "+dayOfMonth);
           }
    }

    public void save(Inventario inventario){
        InventarioClient inventarioClient = InventarioClient.retrofit.create(InventarioClient.class);
        final Call<Result> call  = inventarioClient.save(inventario);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                   Log.d("result",response.body().toString());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("error",t.toString());

            }
        });
    }

}
