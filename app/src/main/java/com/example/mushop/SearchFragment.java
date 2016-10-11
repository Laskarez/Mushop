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
        searchDataBase();
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


    public void searchDataBase() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        DatabaseReference song = mData.child("song");
        song.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    Map<String, Object> map2 = null;
                    int cantCanciones = map.values().size();
                    for (int i = 1; i <= cantCanciones; i++) {
                        String song = "song_";
                        if (i < 10) {
                            song += "0" + i;
                        } else {
                            song += i;
                        }
                        map2 = (Map<String, Object>) map.get(song);
                        Cancion cancion = new Cancion(
                                Integer.parseInt(map2.get("id_song").toString()),
                                map2.get("song_name").toString(),
                                Integer.parseInt(map2.get("listening").toString()));
                        listaCanciones.add(cancion);
                    }
                } catch (Exception e) {
                    Log.v("Excepcion", e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void showList() {
        for (int j = 0; j < listaCanciones.size(); j++) {
            String nombreCancion = listaCanciones.get(j).getNombre_cancion();
            if (nombreCancion.toLowerCase().contains(busquedaStr.toLowerCase())) {
                Context act = getContext();
                TableRow fila = new TableRow(act);
                TextView tv = new TextView(act);
                tv.setText(nombreCancion);
                fila.addView(tv);
                tabla.addView(fila);
            }
        }
    }


}
