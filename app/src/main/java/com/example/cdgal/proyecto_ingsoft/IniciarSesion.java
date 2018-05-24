package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class IniciarSesion extends AppCompatActivity {

    private EditText username,password;
    int counter=4;
    int time=10;
    Button eyeButton;
    Button loginButton;

    private RequestQueue requestQueue;
    private static final String URL = "https://andproyect123.000webhostapp.com/user_control.php";
    private StringRequest request;

    public String datos[] = new String[5];
    public int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        loginButton=(Button) findViewById(R.id.loginButton);
        eyeButton=(Button) findViewById(R.id.eyeButton);

        hideAndShow();

        TextView tv = (TextView) findViewById(R.id.recuperar);
        tv.setPaintFlags(tv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        requestQueue = Volley.newRequestQueue(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.names().get(0).equals("success")){
                                    if((""+jsonObject.getString("success")).equals("Usuario Particular")){
                                        Toast.makeText(getApplicationContext(),"Bienvenido usuario: particular",Toast.LENGTH_SHORT).show();
                                        datos[0] = jsonObject.getString("nomb_user")+"";
                                        datos[1] = jsonObject.getString("usua_user")+"";
                                        datos[2] = jsonObject.getString("pass_user")+"";
                                        datos[3] = jsonObject.getString("success")+"";
                                        datos[4] = jsonObject.getString("unosolo_user");
                                        /* ACA ENVÍO DATOS A: ActivityPrincipalParticular que inicia con (MiCuentaFagment)*/
                                        Intent intent = new Intent(getApplicationContext(),ActivityPrincipalParticular.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("nomb", datos[0]);
                                        bundle.putString("usua", datos[1]);
                                        bundle.putString("pass", datos[2]);
                                        bundle.putString("tipo", datos[3]);
                                        bundle.putString("ident", datos[4]);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        /* ------------------------------------------------------------------------------ */
                                    }else if((""+jsonObject.getString("success")).equals("Usuario Agente")){
                                        Toast.makeText(getApplicationContext(),"Bienvenido usuario: agente, id:"+jsonObject.getString("id_user"),Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),ActivityPrincipalAgente.class));
                                    }
                                }else {
                                    Toast.makeText(getApplicationContext(), "Error:" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("usuario",username.getText().toString());
                            hashMap.put("password",password.getText().toString());

                            return hashMap;
                        }
                    };

                    requestQueue.add(request);
                }
            }
        });
    }

    public void  lanzarMsjCorto(){
        Toast.makeText(this, "Crear un nuevo usuario", Toast.LENGTH_SHORT).show();
    }

    public void llamarCrearUsuario(View view){
        lanzarMsjCorto();
        Intent intent = new Intent(this, AddParticular.class);
        startActivity(intent);
    }

    //////////this will take care that input fields are not empty/////////////
    public boolean validate() {
        String user_name=username.getText().toString();
        String pass_word = password.getText().toString();
        if(user_name.isEmpty()){
            username.setError("Ingrese un nombre de usuario válido");
            return false;
        }
        else{
            username.setError(null);
            if(pass_word.isEmpty()){
                password.setError("Ingrese una contraseña válida");
                return false;
            }
            else{
                password.setError(null);
            }
            return true;
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

    public void recuperarContra(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("http://www.facebook.com"));
        startActivity(intent);
    }
}
