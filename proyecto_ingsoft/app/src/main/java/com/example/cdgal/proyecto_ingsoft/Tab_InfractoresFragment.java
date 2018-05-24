package com.example.cdgal.proyecto_ingsoft;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Tab_InfractoresFragment extends Fragment {

    TextView resultado;

    //IP de mi Url:
    String IP = "https://andproyect123.000webhostapp.com";
    //Ruta de los Web Services:
    String GET = IP+"/top.php";

    obtenerWebService hiloconexion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_infractores, container, false);

        resultado = (TextView)view.findViewById(R.id.txtRes);

        llama();

        return view;
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
            resultado.setText(s);
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
                            JSONArray alumnosJSON = respuestaJSON.getJSONArray("datos_topvehiculo");
                            for (int i=0; i<alumnosJSON.length();i++){
                                devuelve += alumnosJSON.getJSONObject(i).getString("no_placa")+
                                        "                                                         "+
                                        alumnosJSON.getJSONObject(i).getString("no_multa")+"\n\n";
                            }
                        }else if (resultJSON=="2"){
                            devuelve = "... No hay datos vehículares.";
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
}
