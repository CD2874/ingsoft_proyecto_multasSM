package com.example.cdgal.proyecto_ingsoft;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.List;


public class AgregarVehiculoFragment extends Fragment {

    List<DataItemAgregarVehiculos> lsData;
    CustomAdapterAgregarVehiculos adapterAgregarVehiculos;
    String id, nomb, usua, pass, tipo, que_user;
    String idpos="";
    int posicionado=0;

    TextView resultado;

    String idE;

    String []alias;
    String []placa;
    String []pos;
    String []posicionId;

    //IP de mi Url:
    String IP = "https://andproyect123.000webhostapp.com";
    //Ruta de los Web Services:
    String GET;
    String DELETE = IP+"/eliminarMiVehiculo.php";


    obtenerWebService hiloconexion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_agregarvehiculo, container, false);

        /* ACA RECIBO DATOS DE: ActivityPincipalParticular EN: nav_addvehiculo */
        id =getArguments().getString("ident");
        nomb =getArguments().getString("nomb");
        usua =getArguments().getString("usua");
        pass =getArguments().getString("pass");
        tipo =getArguments().getString("tipo");
        que_user =getArguments().getString("que_user");
        /* ------------------------------------ */

        GET = IP+"/misVehiculos.php?idvehiculo="+id;

        FloatingActionButton masV = (FloatingActionButton) v.findViewById(R.id.masVehiculos);
        masV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* ACA ENVÍO DATOS A: EditarVehiculo */
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), NuevoVehiculo.class);
                bundle.putString("ident", id);
                bundle.putString("nomb", nomb);
                bundle.putString("usua", usua);
                bundle.putString("pass", pass);
                bundle.putString("tipo", tipo);

                intent.putExtras(bundle);
                startActivity(intent);
                /* ------------------------------------ */
            }
        });

        lsData = new ArrayList<>();
        lsData.add(new DataItemAgregarVehiculos("","","Error: presione ", "Agregar Vehículo de nuevo", 0));
        llama();

        final ListView listView = (ListView)v.findViewById(R.id.listaMenu);

        adapterAgregarVehiculos = new CustomAdapterAgregarVehiculos(this.getActivity(), R.layout.item_fil_agregar_vehiculos,lsData);

        listView.setAdapter(adapterAgregarVehiculos);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                /* ACA ENVÍO DATOS A: EditarVehiculo */
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), consultarVehiculo.class);
                bundle.putString("ident", id);
                bundle.putString("nomb", nomb);
                bundle.putString("usua", usua);
                bundle.putString("pass", pass);
                bundle.putString("tipo", tipo);

                //bundle.putString("idVehiculo", lsData.get(position).ip);
                //bundle.putString("vehiculo", lsData.get(position).v);
                bundle.putString("placa", lsData.get(position).p);

                intent.putExtras(bundle);
                startActivity(intent);
                /* ------------------------------------ */
            }
        });

        this.registerForContextMenu(listView);

        return v;
    }

    public void llama(){
        hiloconexion = new obtenerWebService();
        hiloconexion.execute(GET,"1");
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.listaMenu){
            getActivity().getMenuInflater().inflate(R.menu.menu_opciones, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int selectItemId = item.getItemId();
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (selectItemId){
            case R.id.editar:
                /* ACA ENVÍO DATOS A: EditarVehiculo */
                Bundle bundle = new Bundle();
                Intent intent = new Intent(AgregarVehiculoFragment.this.getActivity(), EditarVehiculo.class);
                bundle.putString("ident", id);
                bundle.putString("nomb", nomb);
                bundle.putString("usua", usua);
                bundle.putString("pass", pass);
                bundle.putString("tipo", tipo);

                bundle.putString("idPosicion", lsData.get(info.position).ip);
                bundle.putString("Vehiculo", lsData.get(info.position).v);
                bundle.putString("Placa", lsData.get(info.position).p);

                intent.putExtras(bundle);
                startActivity(intent);
                /* ------------------------------------ */
                break;
            case R.id.eliminar:
                idpos = lsData.get(info.position).ip;
                posicionado = Integer.parseInt((lsData.get(info.position).pos)+"");
                AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
                builder.setMessage("¿Desea eliminar Vehículo?").
                        setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                hiloconexion = new obtenerWebService();
                                hiloconexion.execute(DELETE,"2",idpos+""); //Parametro que recibe
                                lsData.remove(posicionado);
                                adapterAgregarVehiculos.notifyDataSetChanged();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
        }
        return super.onContextItemSelected(item);
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
            //Log.d("INFORMACIÓN",s+"");
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
                            JSONArray alumnosJSON = respuestaJSON.getJSONArray("vehiculos");
                            pos = new String[alumnosJSON.length()+1];
                            posicionId = new String[alumnosJSON.length()+1];
                            alias = new String[alumnosJSON.length()+1];
                            placa = new String[alumnosJSON.length()+1];

                            lsData.clear();
                            for (int i=0; i<alumnosJSON.length();i++){
                                pos[i] = String.valueOf((i+1));
                                posicionId[i] = alumnosJSON.getJSONObject(i).getString("id");
                                alias[i] = alumnosJSON.getJSONObject(i).getString("alias");
                                placa[i] = alumnosJSON.getJSONObject(i).getString("placa");
                                lsData.add(new DataItemAgregarVehiculos(alias[i]+"",placa[i]+"", posicionId[i]+"", pos[i]+"", R.drawable.ic_keyboard_arrow_right_black_24dp));
                                devuelve = "Mis Vehiculos";
                            }
                        }else if (resultJSON=="2"){
                            devuelve = "No hay Vehículos.";
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
            }else if (strings[1]=="2"){ //Borrar alumno...
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
                    jsonParam.put("idvehiculo", strings[2]);

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
                            devuelve = "Vehículo eliminado correctamente";
                        }else if(resultJson=="2"){
                            devuelve = "No hay vehículos";
                        }
                    }

                } catch (MalformedURLException e){
                    e.printStackTrace();
                    devuelve = "Error técnico, eliminar vehículos.";
                } catch (IOException e){
                    e.printStackTrace();
                    devuelve = "Error técnico, eliminar vehículos.";
                } catch (JSONException e){
                    e.printStackTrace();
                    devuelve = "Error técnico, eliminar vehículos.";
                }
                return devuelve;
            }

            return null;
        }
    }
}