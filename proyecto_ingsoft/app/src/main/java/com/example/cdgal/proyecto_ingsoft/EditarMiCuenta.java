package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditarMiCuenta extends AppCompatActivity {

    EditText nom, cor, num, con;
    String nombAntes, usuaAntes, passAntes, tipoAntes;
    String nombDsps, usuaDsps, passDsps, tipoDsps, que_user;

    Button actualizar;
    String id;

    Boolean enviado;

    String IP = "https://andproyect123.000webhostapp.com";
    String UPDATE = IP+"/actualizarUsuario.php";

    obtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_micuenta);

        /* ACA RECIBO DATOS DE: MiCuentaFragment */
        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("ident");
        nombAntes = bundle.getString("nomb");
        usuaAntes = bundle.getString("usua");
        passAntes = bundle.getString("pass");
        tipoAntes = bundle.getString("tipo");
        que_user = bundle.getString("que_user");
        /* ------------------------------------ */

        actualizar = (Button)findViewById(R.id.editarCuenta);
        nom = (EditText)findViewById(R.id.nombre);
        cor = (EditText)findViewById(R.id.correo);
        num = (EditText)findViewById(R.id.numero);
        con = (EditText)findViewById(R.id.password);

        nom.setText(nombAntes);
        cor.setText(usuaAntes);
        num.setText(tipoAntes);
        con.setText(passAntes);
    }


    public void llama(){
        hiloconexion = new obtenerWebService();
        hiloconexion.execute(UPDATE,"4",id+"",nom.getText().toString(),cor.getText().toString()); //Parametro que recibe
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        /*// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);*/
        return true;
    }

    public void llamarMiCuenta(View view){
        if (que_user.equals("1")){
            /* ACA ENVÍO DATOS A: ActivityPrincipalPaticular */
            Bundle bundle = new Bundle();
            Intent intent = new Intent(getApplicationContext(),ActivityPrincipalParticular.class);
            bundle.putString("ident", id);
            bundle.putString("nomb", nombAntes);
            bundle.putString("usua", usuaAntes);
            bundle.putString("pass", tipoAntes);
            bundle.putString("tipo", passAntes);
            bundle.putString("que_user", que_user);
            intent.putExtras(bundle);
            startActivity(intent);
        /* ------------------------------------ */
        } else if (que_user.equals("2")){
            /* ACA ENVÍO DATOS A: ActivityPrincipalPaticular */
            Bundle bundle = new Bundle();
            Intent intent = new Intent(getApplicationContext(),ActivityPrincipalAgente.class);
            bundle.putString("ident", id);
            bundle.putString("nomb", nombAntes);
            bundle.putString("usua", usuaAntes);
            bundle.putString("pass", tipoAntes);
            bundle.putString("tipo", passAntes);
            bundle.putString("que_user", que_user);
            intent.putExtras(bundle);
            startActivity(intent);
        /* ------------------------------------ */
        }
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

            if (strings[1]=="4"){ //Actualizar alumno...
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
                    jsonParam.put("id", strings[2]);
                    jsonParam.put("nombre", strings[3]);
                    jsonParam.put("usuario", strings[4]);

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
                            devuelve = "Usuario actualizado correctamente.";
                            //enviado = true;
                        }else if(resultJson=="2"){
                            devuelve = "El usuario no pudo actualizarse.";
                            //enviado = false;
                        }
                    }else{
                        devuelve = "Error de ingreso para actualizar usuario.";
                        //enviado = false;
                    }

                } catch (MalformedURLException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al actualizar usuario.";
                } catch (IOException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al actualizar usuario.";
                } catch (JSONException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al actualizar usuario.";
                }
                return devuelve;
            }
            return null;
        }
    }

    public void llamarEditar(View view){
        /* ACA ENVÍO DATOS A: ActivityPrincipalPaticular */
        llama();

        //if (enviado==true){
            nombDsps = nom.getText()+"";
            usuaDsps = cor.getText()+"";
            passDsps = con.getText()+"";
            tipoDsps = num.getText()+"";
        /*}else if (enviado==false){
            Bundle bundle = getIntent().getExtras();
            nombDsps = bundle.getString("nomb");
            usuaDsps = bundle.getString("usua");
            passDsps = bundle.getString("pass");
            tipoDsps = bundle.getString("tipo");
        }*/

        if (que_user.equals("1")){
            //Toast.makeText(getApplicationContext(), "Particular", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            Intent intent = new Intent(getApplicationContext(),ActivityPrincipalParticular.class);
            bundle.putString("ident", id);
            bundle.putString("nomb", nombDsps);
            bundle.putString("usua", usuaDsps);
            bundle.putString("pass", passDsps);
            bundle.putString("tipo", tipoDsps);
            bundle.putString("que_user", que_user);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(que_user.equals("2")){
            //Toast.makeText(getApplicationContext(), "Agente", Toast.LENGTH_SHORT).show();
            Bundle bundle = new Bundle();
            Intent intent = new Intent(getApplicationContext(),ActivityPrincipalAgente.class);
            bundle.putString("ident", id);
            bundle.putString("nomb", nombDsps);
            bundle.putString("usua", usuaDsps);
            bundle.putString("pass", passDsps);
            bundle.putString("tipo", tipoDsps);
            bundle.putString("que_user", que_user);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        /* ------------------------------------ */
    }
}
