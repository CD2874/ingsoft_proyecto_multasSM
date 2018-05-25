package com.example.cdgal.proyecto_ingsoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExpLVAdapter extends BaseExpandableListAdapter {

    private ArrayList<String> listDescripciones;
    private Map<String, ArrayList<String>> mapChil;
    private Context context;

    public ExpLVAdapter(Context context, ArrayList<String> listDescripciones, Map<String, ArrayList<String>> mapChil) {
        this.listDescripciones = listDescripciones;
        this.mapChil = mapChil;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return listDescripciones.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mapChil.get(listDescripciones.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDescripciones.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mapChil.get(listDescripciones.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String tituloDescripcion = (String) getGroup(i);
        view = LayoutInflater.from(context).inflate(R.layout.elv_grupo,null);
        TextView tvGrup = view.findViewById(R.id.tvGrupoDescripcion);
        tvGrup.setText(tituloDescripcion);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        String item = (String) getChild(i,i1);
        view = LayoutInflater.from(context).inflate(R.layout.elv_hijo, null);
        TextView tvChild = (TextView) view.findViewById(R.id.tvDescripcion);
        tvChild.setText(item);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
