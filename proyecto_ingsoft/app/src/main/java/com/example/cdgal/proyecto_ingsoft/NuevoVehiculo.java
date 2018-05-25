package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class NuevoVehiculo extends AppCompatActivity {

    String id, nomb, usua, pass, tipo;
    EditText alias, placa;
    Spinner spinnerT;

    Button actualizar;

    String IP = "https://andproyect123.000webhostapp.com";
    String UPDATE = IP+"/agregarMiVehiculo.php";

    obtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_vehiculo);

        /* ACA RECIBO DATOS DE: MiCuentaFragment */
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("ident");
        nomb = bundle.getString("nomb");
        usua = bundle.getString("usua");
        pass = bundle.getString("pass");
        tipo = bundle.getString("tipo");
        /* ------------------------------------ */

        String [] valuesTipoLicencia = {"- # -","A", "B", "C", "M", "E"};
        spinnerT = (Spinner) findViewById(R.id.tipo_placa);
        ArrayAdapter<String> adapterT = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valuesTipoLicencia);
        adapterT.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerT.setAdapter(adapterT);


        alias = (EditText)findViewById(R.id.alias);
        placa = (EditText)findViewById(R.id.numero);
    }

    public void llama(){
        hiloconexion = new obtenerWebService();
        hiloconexion.execute(UPDATE,"3",alias.getText().toString(),spinnerT.getSelectedItem().toString()+"-"+placa.getText().toString(),id+""); //Parametro que recibe
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
            Toast.makeText(getApplicationContext(),s+"", Toast.LENGTH_LONG).show();
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
            String devuelve = "";

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
                    jsonParam.put("alias", strings[2]);
                    jsonParam.put("placa", strings[3]);
                    jsonParam.put("credenciales_id", strings[4]);

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
                            devuelve = "Vehiculo insertado correctamente.";
                        }else if(resultJson=="2"){
                            devuelve = "El vehiculo no pudo insertarse.";
                        }
                    }

                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException e){
                    e.printStackTrace();
                }
                return devuelve;
            }
            return null;
        }
    }

    public void llamarAgregarVehiculo(View view){
        /* ACA ENVÍO DATOS A: ActivityPrincipalPaticular */
        Bundle bundle = new Bundle();
        Intent intent = new Intent(this,ActivityPrincipalParticular.class);
        bundle.putString("ident", id);
        bundle.putString("nomb", nomb);
        bundle.putString("usua", usua);
        bundle.putString("pass", tipo);
        bundle.putString("tipo", pass);
        intent.putExtras(bundle);
        startActivity(intent);
        /* ------------------------------------ */
    }

    public void agregarNuevoVehiculo(View view){
        llama();

        /* ACA ENVÍO DATOS A: ActivityPrincipalPaticular */
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getApplicationContext(),ActivityPrincipalParticular.class);
        bundle.putString("ident", id);
        bundle.putString("nomb", nomb);
        bundle.putString("usua", usua);
        bundle.putString("pass", pass);
        bundle.putString("tipo", tipo);
        intent.putExtras(bundle);
        startActivity(intent);
        /* ------------------------------------ */
    }
}
