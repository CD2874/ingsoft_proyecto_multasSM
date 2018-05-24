package com.example.cdgal.proyecto_ingsoft;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cdgal on 1/03/2018.
 */

public class CustomAdapterInfrac extends ArrayAdapter<DataItemInfrac>{

    Context context;
    int layoutResourceId;
    List<DataItemInfrac> data = null;

    public CustomAdapterInfrac(Context context, int resource, List<DataItemInfrac> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;

    }

    static class DataHolder{
        TextView tvdesc;
        TextView tvcosto;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataHolder holder = null;
        if (convertView==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();

            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new DataHolder();
            holder.tvdesc = (TextView)convertView.findViewById(R.id.desinfrac);
            holder.tvcosto = (TextView)convertView.findViewById(R.id.cosinfrac);
            convertView.setTag(holder);
        }else{
            holder = (DataHolder)convertView.getTag();
        }

        DataItemInfrac dataItemInfrac = data.get(position);
        holder.tvdesc.setText(dataItemInfrac.descripcion);
        holder.tvcosto.setText(dataItemInfrac.cant);

        return convertView;
    }
}
