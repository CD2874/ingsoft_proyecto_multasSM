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

public class CustomAdapterMultCom extends ArrayAdapter<DataItemMultCom>{

    Context context;
    int layoutResourceId;
    List<DataItemMultCom> data = null;

    public CustomAdapterMultCom(Context context, int resource, List<DataItemMultCom> objects) {
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
            holder.tvdesc = (TextView)convertView.findViewById(R.id.desinfracom);
            holder.tvcosto = (TextView)convertView.findViewById(R.id.cosinfracom);
            convertView.setTag(holder);
        }else{
            holder = (DataHolder)convertView.getTag();
        }

        DataItemMultCom dataItemInfraCom = data.get(position);
        holder.tvdesc.setText(dataItemInfraCom.descripcion);
        holder.tvcosto.setText(dataItemInfraCom.cosoto);

        return convertView;
    }
}
