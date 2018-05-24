package com.example.cdgal.proyecto_ingsoft;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cdgal on 5/04/2018.
 */

public class CustomAdapterAgregarVehiculos extends ArrayAdapter<DataItemAgregarVehiculos> {

    Context context;
    int layoutResourceId;
    List<DataItemAgregarVehiculos> data = null;
    public CustomAdapterAgregarVehiculos(@NonNull Context context, int resource, @NonNull List<DataItemAgregarVehiculos> objects) {
        super(context, resource, objects);

        this.layoutResourceId = resource;
        this.context = context;
        this.data = objects;
    }

    static  class DataHolder{
        TextView ipos;
        TextView vehiculos;
        TextView placa;
        ImageView image;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DataHolder holder = null;
        if (convertView==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent,false);

            holder = new DataHolder();
            holder.ipos = (TextView)convertView.findViewById(R.id.idp);
            holder.vehiculos = (TextView)convertView.findViewById(R.id.vehiculo);
            holder.placa = (TextView)convertView.findViewById(R.id.placa);
            holder.image = (ImageView)convertView.findViewById(R.id.flecha);

            convertView.setTag(holder);
        }else{
            holder = (DataHolder)convertView.getTag();
        }

        DataItemAgregarVehiculos dataItemAgregarVehiculos = data.get(position);
        holder.ipos.setText(dataItemAgregarVehiculos.ip);
        holder.vehiculos.setText(dataItemAgregarVehiculos.v);
        holder.placa.setText(dataItemAgregarVehiculos.p);
        holder.image.setImageResource(dataItemAgregarVehiculos.i);

        return convertView;
    }
}
