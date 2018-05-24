package com.example.cdgal.proyecto_ingsoft;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cdgal on 1/03/2018.
 */

public class CustomAdapterInfraCom extends ArrayAdapter<DataItemInfraCom>{

    Context context;
    int layoutResourceId;
    List<DataItemInfraCom> data = null;

    public CustomAdapterInfraCom(Context context, int resource, List<DataItemInfraCom> objects) {
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
            holder.tvdesc = (TextView)convertView.findViewById(R.id.descripcion);
            holder.tvcosto = (TextView)convertView.findViewById(R.id.costo);
            convertView.setTag(holder);
        }else{
            holder = (DataHolder)convertView.getTag();
        }

        DataItemInfraCom dataItemInfraCom = data.get(position);
        holder.tvdesc.setText(dataItemInfraCom.descripcion);
        holder.tvcosto.setText(dataItemInfraCom.cosoto);

        return convertView;
    }
}
