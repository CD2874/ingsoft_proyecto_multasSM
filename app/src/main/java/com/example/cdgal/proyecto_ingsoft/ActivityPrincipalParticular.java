package com.example.cdgal.proyecto_ingsoft;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ActivityPrincipalParticular extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText nom, cor, num, con;
    String idd;

    String id, nomb, usua, pass, tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_particular);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* ACA RECIBO DATOS DE: Iniciar Sesión */
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("ident");
        String nomb = bundle.getString("nomb");
        String usua = bundle.getString("usua");
        String pass = bundle.getString("pass");
        String tipo = bundle.getString("tipo");
        String que_user = bundle.getString("que_user");
        /* ------------------------------------ */

        /* ACA ENVÍO DATOS A: MiCuentaFragment */
        Bundle data = new Bundle();
        data.putString("ident", id);
        data.putString("nomb", nomb);
        data.putString("usua", usua);
        data.putString("pass", pass);
        data.putString("tipo", tipo);
        data.putString("que_user", que_user);
        AgregarVehiculoFragment agregarVehiculoFragment = new AgregarVehiculoFragment();
        MiCuentaFragment miCuentaFragment = new MiCuentaFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor2, miCuentaFragment).commit();
        miCuentaFragment.setArguments(data);
        /* ---------------------------------- */
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings_particular, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        /* ACA RECIBO DATOS DE: Iniciar Sesión */
        Bundle bundle = getIntent().getExtras();
        String id123 = bundle.getString("ident");
        String nomb123 = bundle.getString("nomb");
        String usua123 = bundle.getString("usua");
        String pass123 = bundle.getString("pass");
        String tipo123 = bundle.getString("tipo");
        String que_user = bundle.getString("que_user");
            /* ------------------------------------ */

        if (id == R.id.nav_addvehiculo) {
            /* ACA ENVÍO DATOS A: AgregarVehiculoFragment */
            AgregarVehiculoFragment agregarVehiculoFragment = new AgregarVehiculoFragment();
            Bundle data = new Bundle();
            data.putString("ident", id123);
            data.putString("nomb", nomb123);
            data.putString("usua", usua123);
            data.putString("pass", pass123);
            data.putString("tipo", tipo123);
            data.putString("que_user", que_user);
            fragmentManager.beginTransaction().replace(R.id.contenedor2, agregarVehiculoFragment).commit();
            agregarVehiculoFragment.setArguments(data);
            /* ---------------------------------- */
        } else if (id == R.id.nav_top) {
            fragmentManager.beginTransaction().replace(R.id.contenedor2, new TopVehiculosFragment()).commit();
        } else if (id == R.id.nav_costoMultas) {
            fragmentManager.beginTransaction().replace(R.id.contenedor2, new CostMultFragment()).commit();
        } else if (id == R.id.nav_info) {
            fragmentManager.beginTransaction().replace(R.id.contenedor2, new InfoFragment()).commit();
        } else if (id == R.id.nav_miCuenta) {
            /* ACA ENVÍO DATOS A: MiCuenta */
            MiCuentaFragment miCuentaFragment = new MiCuentaFragment();
            Bundle data = new Bundle();
            data.putString("ident", id123);
            data.putString("nomb", nomb123);
            data.putString("usua", usua123);
            data.putString("pass", pass123);
            data.putString("tipo", tipo123);
            data.putString("que_user", que_user);
            fragmentManager.beginTransaction().replace(R.id.contenedor2, miCuentaFragment).commit();
            miCuentaFragment.setArguments(data);
            /* ---------------------------------- */
        } else if (id == R.id.nav_X) {
            try {
                Thread.sleep(1000);
                startActivity(new Intent(getApplicationContext(),IniciarSesion.class));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
