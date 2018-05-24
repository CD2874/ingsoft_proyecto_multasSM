package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class consultarVehiculo extends AppCompatActivity {

    String id,nomb, usua, pass, tipo, /*idV, vehiculo,*/ placa;

    //IP de mi Url:
    String IP = "https://andproyect123.000webhostapp.com";
    //Ruta de los Web Services:
    String GET;

    ArrayList<String> listContSM = new ArrayList<>();
    ArrayList<String> listContSP = new ArrayList<>();


    obtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_vehiculo);

        /* ACA RECIBO DATOS DE: MiCuentaFragment */
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("ident");
        nomb = bundle.getString("nomb");
        usua = bundle.getString("usua");
        pass = bundle.getString("pass");
        tipo = bundle.getString("tipo");

        //idV = bundle.getString("idVehiculo");
        //vehiculo = bundle.getString("vehiculo");
        placa = bundle.getString("placa");
        /* ------------------------------------ */

        GET = IP+"/miVehiculoDescripcion.php?placavehiculo="+placa;

        ExpandableListView expLV;
        ExpLVAdapter adapter;
        ArrayList<String> listCategorias;
        Map<String, ArrayList<String>> mapChild;

        expLV = (ExpandableListView) findViewById(R.id.expLV);
        listCategorias = new ArrayList<>();
        mapChild = new HashMap<>();

        listCategorias.add("SAN MARCOS Y SAN PEDRO SAC. ↓↓");
        //listCategorias.add("SAN PEDRO SAC.");

        //listContSM.add("Esto 1");
        //listContSP.add("Descripcion numero 1... \f\f\f (Q.800.00)");

        llama();

        mapChild.put(listCategorias.get(0), listContSM);
        //mapChild.put(listCategorias.get(1), listContSP);

        adapter = new ExpLVAdapter(this, listCategorias, mapChild);
        expLV.setAdapter(adapter);
    }

    public void llamarAgregarVehiculo(View view){

    }

    public void llama(){
        hiloconexion = new obtenerWebService();
        hiloconexion.execute(GET,"1");
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
            Toast.makeText(getApplicationContext(), s+"", Toast.LENGTH_SHORT).show();
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
            String devuelve = "No hay infracciones.";

            if (strings[1]=="1"){ //Consulta de todos los alumnos...
                try{
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux: Android 1.5; es-ES) Ejemplo HTTP");
                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null){
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());
                        String resultJSON = respuestaJSON.getString("estado");

                        if (resultJSON=="1"){
                            JSONArray alumnosJSON = respuestaJSON.getJSONArray("vehiculos");
                            for (int i=0; i<alumnosJSON.length();i++){
                                listContSM.add("                           INFRACCION NO. "+(i+1)+"\n\n"+
                                        "Lugar: "+alumnosJSON.getJSONObject(i).getString("lugar_infraccion")+"\n"+
                                        "Fecha: "+alumnosJSON.getJSONObject(i).getString("fecha_infraccion")+"\n"+
                                        "Placa: "+alumnosJSON.getJSONObject(i).getString("no_placa")+"\n"+
                                        "Tipo vehículo: "+alumnosJSON.getJSONObject(i).getString("tipo_vehiculo")+"\n"+
                                        "Marca vehículo: "+alumnosJSON.getJSONObject(i).getString("marca")+"\n"+
                                        "Color vehículo: "+alumnosJSON.getJSONObject(i).getString("color"));
                                devuelve = "Descripciones del Vehiculo";
                            }
                        }else if (resultJSON=="2"){
                            devuelve = "No hay infracciones.";
                        }
                    }
                } catch (MalformedURLException e){
                    e.printStackTrace();
                    devuelve = "Error técnico, listar vehículos.";
                } catch (IOException e){
                    e.printStackTrace();
                    devuelve = "Error técnico, listar vehículos.";
                } catch (JSONException e){
                    e.printStackTrace();
                    devuelve = "Error técnico, listar vehículos.";
                }
                return devuelve;
            }
            return null;
        }
    }
}
