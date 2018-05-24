package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cdgal.proyecto_ingsoft.IniciarSesion;
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

public class AddParticular extends AppCompatActivity {

    EditText password;
    Button eyeButton;

    EditText nomb, usua, pass;
    Button agregar;

    String IP = "https://andproyect123.000webhostapp.com";
    String UPDATE = IP+"/agregarUsuarioParticular.php";

    obtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addparticular);
        setTitle(R.string.title_activity_crear_usuario);

        password=(EditText) findViewById(R.id.password);
        eyeButton=(Button) findViewById(R.id.eyeButton);
        agregar = (Button)findViewById(R.id.btnAddPar);

        nomb = (EditText)findViewById(R.id.nombre);
        usua = (EditText)findViewById(R.id.usuario);
        pass = (EditText)findViewById(R.id.password);

        hideAndShow();

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llama();
            }
        });
    }

    public void llama(){
        hiloconexion = new obtenerWebService();
        hiloconexion.execute(UPDATE,"3",nomb.getText().toString(),usua.getText().toString()+"",pass.getText().toString()+""); //Parametro que recibe
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
            String devuelve = "Error técnico al ingresar Usuario Particular";

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
                    jsonParam.put("nombre", strings[2]);
                    jsonParam.put("usuario", strings[3]);
                    jsonParam.put("password", strings[4]);

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
                            devuelve = "Usuario Particular insertado correctamente.";
                            nomb.setText("");
                            usua.setText("");
                            pass.setText("");
                        }else if(resultJson=="2"){
                            devuelve = "El Usuario Particular no pudo insertarse.";
                        }
                    }
                } catch (MalformedURLException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al ingresar Usuario Particular";
                } catch (IOException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al ingresar Usuario Particular";
                } catch (JSONException e){
                    e.printStackTrace();
                    devuelve = "Error técnico al ingresar Usuario Particular";
                }
                return devuelve;
            }
            return null;
        }
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

    public void llamarIniciarSesion(View view){
        Intent intent = new Intent(this, IniciarSesion.class);
        startActivity(intent);
    }
}
