package com.example.cdgal.proyecto_ingsoft;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class ConsultFragment extends Fragment {

    Button consultarporid;
    Spinner noPlaca;
    EditText placa;

    TextView resultado;

    //IP de mi Url:
    String IP = "https://andproyect123.000webhostapp.com";
    //Ruta de los Web Services:
    String GET_BY_ID = IP+"/obtenerVehiculoPorPlaca.php";

    obtenerWebService hiloconexion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_consult, container, false);

        consultarporid = (Button)v.findViewById(R.id.busVehiculo);
        noPlaca = (Spinner)v.findViewById(R.id.tipo);
        placa = (EditText)v.findViewById(R.id.placa);
        resultado = (TextView)v.findViewById(R.id.resultado);

        String [] valuesTipo = {"- # -","A", "B", "C", "M", "E"};
        Spinner spinnerT = (Spinner) v.findViewById(R.id.tipo);
        ArrayAdapter<String> adapterT = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, valuesTipo);
        adapterT.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerT.setAdapter(adapterT);

        consultarporid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llama();
            }
        });


        return v;
    }

    public void llama(){
        hiloconexion = new obtenerWebService();
        String cadenallamada = GET_BY_ID+"?placaVehiculo="+noPlaca.getSelectedItem()+"-"+placa.getText()+"";
        hiloconexion.execute(cadenallamada,"2"); //Parametro que recibe*/
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
            String devuelve = "No hay resultados";

            if (strings[1]=="2"){ //Consulta por id...

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
                            devuelve = "NOMBRE: "+respuestaJSON.getJSONObject("vehiculos").getString("nombre")+"\n\n"+
                                    "CORREO: "+respuestaJSON.getJSONObject("vehiculos").getString("usuario")+"\n\n"+
                                    "VEHICULO (ALIAS): "+respuestaJSON.getJSONObject("vehiculos").getString("alias")+"\n\n"+
                                    "PLACA: "+respuestaJSON.getJSONObject("vehiculos").getString("placa");
                        }else if (resultJSON=="2"){
                            devuelve = "No hay resultados";
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
