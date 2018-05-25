package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MiCuentaFragment extends Fragment {

    Button b1;
    EditText nom, cor, num, con;
    private Button log_out;
    String id, nomb, usua, pass, tipo, que_user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_micuenta, container, false);

        /* ACA RECIBO DATOS DE: ActivityPincipalParticular QUE VIENEN YA SEA DE: IniciaSesion Y/O EditarMiCuenta O EN: R.id.nav_miCuenta */
        id =getArguments().getString("ident");
        nomb =getArguments().getString("nomb");
        usua =getArguments().getString("usua");
        pass =getArguments().getString("pass");
        tipo =getArguments().getString("tipo");
        que_user =getArguments().getString("que_user");
        /* ------------------------------------ */

        nom = (EditText)v.findViewById(R.id.nombre);
        cor = (EditText)v.findViewById(R.id.correo);
        num = (EditText)v.findViewById(R.id.numero);
        con = (EditText)v.findViewById(R.id.password);

        nom.setEnabled(false);
        cor.setEnabled(false);
        num.setEnabled(false);
        con.setEnabled(false);

        b1=(Button)v.findViewById(R.id.editarCuenta);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /* ACA ENVÍO DATOS A: EditaMiCuenta */
                Bundle bundle = new Bundle();
                Intent intent = new Intent(MiCuentaFragment.this.getActivity(), EditarMiCuenta.class);
                bundle.putString("ident", id);
                bundle.putString("nomb", nom.getText()+"");
                bundle.putString("usua", cor.getText()+"");
                bundle.putString("pass", con.getText()+"");
                bundle.putString("tipo", num.getText()+"");
                bundle.putString("que_user", que_user);
                intent.putExtras(bundle);
                startActivity(intent);
                /* ------------------------------------ */
            }
        });

        log_out = (Button) v.findViewById(R.id.cerrarsesion);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(),IniciarSesion.class));
            }
        });

        nom.setText(nomb);
        cor.setText(usua);
        num.setText(tipo);
        con.setText(pass);

        return v;
    }
}