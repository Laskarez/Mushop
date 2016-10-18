package com.example.mushop;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.api.model.StringList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import java.util.Collections;
import java.util.Map;


public class SearchFragment extends Fragment {

    public Button buscar;
    public EditText busqueda;
    public TableLayout tabla;
    String busquedaStr;
    public FireApp ref;
    public ArrayList<Cancion> listaCanciones = new ArrayList<Cancion>();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_search, container, false);

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        buscar = (Button) view.findViewById(R.id.buscar);
        busqueda = (EditText) view.findViewById(R.id.busqueda);
        tabla = (TableLayout) view.findViewById(R.id.tablaBusqueda);
        ref = new FireApp();
        ref.searchSongsDataBase();
        listaCanciones = ref.listaCanciones;
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabla.removeAllViews();
                busquedaStr = busqueda.getText().toString();
                showList();

            }
        });


        return view;

    }


    public void showList() {

        Collections.sort(listaCanciones, new ListennigComparator());


        for (int j = 0; j < listaCanciones.size(); j++) {
            String nombreCancion = listaCanciones.get(j).getNombre_cancion();
            if (nombreCancion.toLowerCase().contains(busquedaStr.toLowerCase())) {
                Context act = getContext();
                TableRow fila = new TableRow(act);
                TextView tv = new TextView(act);
                tv.setPadding(10,10,10,10);
                tv.setTextSize(16);
                tv.setText(nombreCancion);
                fila.addView(tv);
                tabla.addView(fila);
            }
        }
    }


}
