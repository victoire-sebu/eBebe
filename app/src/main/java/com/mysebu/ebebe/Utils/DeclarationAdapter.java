package com.mysebu.ebebe.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mysebu.ebebe.R;

import java.util.List;

public class DeclarationAdapter extends ArrayAdapter<ListDeclarerationClass> {
    private List<ListDeclarerationClass> listDeclareration;
    private Context mContext;

    public DeclarationAdapter(List<ListDeclarerationClass> listDeclareration, Context mContext) {
        super(mContext, R.layout.declaration_items,listDeclareration);
        this.listDeclareration = listDeclareration;
        this.mContext = mContext;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(mContext);
        View view=inflater.inflate(R.layout.declaration_items,null,true);


//



//
        TextView noms_enf=(TextView) view.findViewById(R.id.item_noms_enfant);
        ListDeclarerationClass listDecl = listDeclareration.get(position);
        noms_enf.setText(listDecl.getNoms_enfant());

        return view;

        //return super.getView(position, convertView, parent);


    }

}
