package com.example.cdgal.proyecto_ingsoft;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.cdgal.proyecto_ingsoft.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class MultarFragment extends Fragment implements View.OnClickListener {

    EditText editTextDate, editTextTime;
    Button buttonDate, buttonTime, guardar;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    private EditText noPlaca, noTCirculacion, tipoV, marca, color, nombre, apellido, conductorAusente, dpi, domic, noLic, lugarI, articulo, monto, baseLegal, noIdent, observaciones;
    private Spinner genero, tipoLic, firmaN, firmUA, firmUP;

    String IP = "https://andproyect123.000webhostapp.com";
    String UPDATE = IP+"/agregarInfraccion.php";

    obtenerWebService hiloconexion;

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

        String [] siNo = {"- - -","SI", "NO"};
        Spinner spinnerFA = (Spinner) v.findViewById(R.id.firAgente);
        ArrayAdapter<String> adapterFA = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, siNo);
        adapterFA.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerFA.setAdapter(adapterFA);
        Spinner spinnerFP = (Spinner) v.findViewById(R.id.firParticular);
        ArrayAdapter<String> adapterFP = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, siNo);
        adapterFP.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerFP.setAdapter(adapterFP);

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

        noPlaca=(EditText)v.findViewById(R.id.no_placa);
        noTCirculacion=(EditText)v.findViewById(R.id.no_tarjetaCirculacion);
        tipoV=(EditText)v.findViewById(R.id.tipo_vehiculo);
        marca=(EditText)v.findViewById(R.id.marca);
        color=(EditText)v.findViewById(R.id.color);
        nombre=(EditText)v.findViewById(R.id.nombres);
        apellido=(EditText)v.findViewById(R.id.apellidos);
        conductorAusente=(EditText)v.findViewById(R.id.ausente);
        noLic=(EditText)v.findViewById(R.id.no_licencia);
        dpi=(EditText)v.findViewById(R.id.nodpi);
        domic=(EditText)v.findViewById(R.id.domicilio);
        lugarI=(EditText)v.findViewById(R.id.lugar_infraccion);
        articulo=(EditText)v.findViewById(R.id.articuloT);
        monto=(EditText)v.findViewById(R.id.montoT);
        baseLegal=(EditText)v.findViewById(R.id.baseLegal);
        noIdent=(EditText)v.findViewById(R.id.no_identificacion);
        observaciones=(EditText)v.findViewById(R.id.observacion);

        genero=(Spinner)v.findViewById(R.id.sexo);
        tipoLic=(Spinner)v.findViewById(R.id.tipo_licencia);
        firmaN=(Spinner)v.findViewById(R.id.negado);
        firmUA=(Spinner)v.findViewById(R.id.firParticular);
        firmUP=(Spinner)v.findViewById(R.id.firAgente);

        guardar=(Button)v.findViewById(R.id.guardarMulta);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    llama();
                    //Log.d("GENERO",genero.getSelectedItem().toString());
                }
            }
        });

        return v;
    }

    public void llama(){
        hiloconexion = new obtenerWebService();
        hiloconexion.execute(UPDATE,"3",
                "1", noPlaca.getText().toString(),noTCirculacion.getText().toString(),tipoV.getText().toString(),
                marca.getText().toString(),color.getText().toString(),

                nombre.getText().toString(),apellido.getText().toString(),conductorAusente.getText().toString(),
                genero.getSelectedItem().toString(),noLic.getText().toString(),tipoLic.getSelectedItem().toString(),
                dpi.getText().toString(),domic.getText().toString(),

                lugarI.getText().toString(),"2001-01-01", "01:01:01",

                "1",articulo.getText().toString(),baseLegal.getText().toString(),monto.getText().toString(),

                noIdent.getText().toString(),firmUA.getSelectedItem().toString(),
                firmUP.getSelectedItem().toString(),firmaN.getSelectedItem().toString(),

                observaciones.getText().toString()); //Parametro que recibe
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        /*// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class obtenerWebService extends AsyncTask<String,Void,String> {
        Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getActivity(), s+"", Toast.LENGTH_SHORT).show();
            //resultado.setText(s);
            //imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String cadena = strings[0];
            URL url = null; //URL donde se obtiene información
            String devuelve = "Error técnico al ingresar Infracción";

            if (strings[1]=="3"){ //Insertar alumno...
                try{
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();

                    JSONObject jsonParam = new JSONObject();

                    jsonParam.put("no_multa", strings[2]);
                    jsonParam.put("no_placa", strings[3]);
                    jsonParam.put("no_tarjeta", strings[4]);
                    jsonParam.put("tipo_v", strings[5]);
                    jsonParam.put("marca", strings[6]);
                    jsonParam.put("color", strings[7]);

                    jsonParam.put("nom_inf", strings[8]);
                    jsonParam.put("ape_inf", strings[9]);
                    jsonParam.put("cond_aus", strings[10]);
                    jsonParam.put("sexo", strings[11]);
                    jsonParam.put("no_lic", strings[12]);
                    jsonParam.put("tip_lic", strings[13]);
                    jsonParam.put("dpi", strings[14]);
                    jsonParam.put("domicilio", strings[15]);

                    jsonParam.put("lugar_inf", strings[16]);
                    jsonParam.put("fecha_inf", strings[17]);
                    jsonParam.put("hora_inf", strings[18]);

                    jsonParam.put("no_art", strings[19]);
                    jsonParam.put("desc_art", strings[20]);
                    jsonParam.put("base_leg", strings[21]);
                    jsonParam.put("monto", strings[22]);

                    jsonParam.put("no_ident", strings[23]);
                    jsonParam.put("fir_ag", strings[24]);
                    jsonParam.put("fir_inf", strings[25]);
                    jsonParam.put("nego_fir", strings[26]);

                    jsonParam.put("observacion", strings[27]);

                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();

                    StringBuilder result = new StringBuilder();

                    if (respuesta==HttpURLConnection.HTTP_OK){
                        String line;
                        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null){
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());

                        String resultJson = respuestaJSON.getString("estado");
                        if (resultJson=="1"){
                            devuelve = "Infracción insertada correctamente.";
                            /*noPlaca.setText("");
                            noTCirculacion.setText("");
                            tipoV.setText("");
                            marca.setText("");
                            color.setText("");
                            nombre.setText("");
                            apellido.setText("");
                            conductorAusente.setText("");
                            genero.setSelection(0);
                            noLic.setText("");
                            tipoLic.setSelection(0);
                            dpi.setText("");
                            domic.setText("");
                            lugarI.setText("");
                            articulo.setText("");
                            baseLegal.setText("");
                            monto.setText("");
                            noIdent.setText("");
                            firmUA.setSelection(0);
                            firmUP.setSelection(0);
                            firmaN.setSelection(0);
                            observaciones.setText("");*/
                        }else if(resultJson=="2"){
                            devuelve = "La Infracción no pudo insertarse.";
                        }
                    }
                } catch (MalformedURLException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al ingresar Infracción";
                } catch (IOException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al ingresar Infracción";
                } catch (JSONException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al ingresar Infracción";
                }
                return devuelve;
            }
            return null;
        }
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

    //////////this will take care that input fields are not empty/////////////
    public boolean validate() {
        String _noPlaca=noPlaca.getText().toString();
        String _noTCirculacion=noTCirculacion.getText().toString();
        String _tipoV=tipoV.getText().toString();
        String _marca=marca.getText().toString();
        String _color=color.getText().toString();
        String _nombre=nombre.getText().toString();
        String _apellido=apellido.getText().toString();
        String _conductorAusente=conductorAusente.getText().toString();
        String _noLic=noLic.getText().toString();
        String _dpi=dpi.getText().toString();
        String _domic=domic.getText().toString();
        String _lugarI=lugarI.getText().toString();
        String _articulo=articulo.getText().toString();
        String _monto=monto.getText().toString();
        String _baseLegal=baseLegal.getText().toString();
        String _noIdent=noIdent.getText().toString();
        String _observaciones=observaciones.getText().toString();

        String _genero = genero.getSelectedItem().toString();
        String _tipoLic=tipoLic.getSelectedItem().toString();
        String _firmaN=firmaN.getSelectedItem().toString();
        String _firmUA=firmUA.getSelectedItem().toString();
        String _firmUP=firmUP.getSelectedItem().toString();

        if (_genero.equals("- - -")){
            TextView errorText = (TextView)genero.getSelectedView();
            errorText.setError("Campo vacío");
        }
        if (_tipoLic.equals("- - -")){
            TextView errorText = (TextView)tipoLic.getSelectedView();
            errorText.setError("Campo vacío");
        }
        if (_firmaN.equals("- - -")){
            TextView errorText = (TextView)firmaN.getSelectedView();
            errorText.setError("Campo vacío");
        }
        if (_firmUA.equals("- - -")){
            TextView errorText = (TextView)firmUA.getSelectedView();
            errorText.setError("Campo vacío");
        }
        if (_firmUP.equals("- - -")){
            TextView errorText = (TextView)firmUP.getSelectedView();
            errorText.setError("Campo vacío");
        }

        if(_noPlaca.isEmpty()){
            noPlaca.setError("Campo vacío");
            return false;
        }else{
            noPlaca.setError(null);
            if(_noTCirculacion.isEmpty()){
                noTCirculacion.setError("Campo vacío");
                return false;
            }else{
                noTCirculacion.setError(null);
                if(_tipoV.isEmpty()){
                    tipoV.setError("Campo vacío");
                    return false;
                }else{
                    tipoV.setError(null);
                    if(_marca.isEmpty()){
                        marca.setError("Campo vacío");
                        return false;
                    }else{
                        marca.setError(null);
                        if(_color.isEmpty()){
                            color.setError("Campo vacío");
                            return false;
                        }else{
                            color.setError(null);
                            if(_nombre.isEmpty()){
                                nombre.setError("Campo vacío");
                                return false;
                            }else{
                                nombre.setError(null);
                                if(_apellido.isEmpty()){
                                    apellido.setError("Campo vacío");
                                    return false;
                                }else{
                                    apellido.setError(null);
                                    if(_conductorAusente.isEmpty()){
                                        conductorAusente.setError("Campo vacío");
                                        return false;
                                    }else{
                                        conductorAusente.setError(null);
                                        if(_noLic.isEmpty()){
                                            noLic.setError("Campo vacío");
                                            return false;
                                        }else{
                                            noLic.setError(null);
                                            if (_dpi.isEmpty()){
                                                dpi.setError("Campo vacío");
                                                return false;
                                            }else {
                                                dpi.setError(null);
                                                if (_domic.isEmpty()) {
                                                    domic.setError("Campo vacío");
                                                    return false;
                                                } else {
                                                    domic.setError(null);
                                                    if (_lugarI.isEmpty()) {
                                                        lugarI.setError("Campo vacío");
                                                        return false;
                                                    } else {
                                                        lugarI.setError(null);
                                                        if (_articulo.isEmpty()) {
                                                            articulo.setError("Campo vacío");
                                                            return false;
                                                        } else {
                                                            articulo.setError(null);
                                                            if (_monto.isEmpty()) {
                                                                monto.setError("Campo vacío");
                                                                return false;
                                                            } else {
                                                                monto.setError(null);
                                                                if (_baseLegal.isEmpty()) {
                                                                    baseLegal.setError("Campo vacío");
                                                                    return false;
                                                                } else {
                                                                    baseLegal.setError(null);
                                                                    if (_noIdent.isEmpty()) {
                                                                        noIdent.setError("Campo vacío");
                                                                        return false;
                                                                    } else {
                                                                        noIdent.setError(null);
                                                                        if (_observaciones.isEmpty()) {
                                                                            observaciones.setError("Campo vacío");
                                                                            return false;
                                                                        } else {
                                                                            observaciones.setError(null);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}