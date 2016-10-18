package com.example.mushop;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ShareCompat;
import android.text.method.ArrowKeyMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;


public class MainFragment extends Fragment implements View.OnClickListener{

    public static ArrayList<Cancion> listaCanciones = new ArrayList<Cancion>();
    public static ArrayList<Album> listaAlbums = new ArrayList<Album>();
    public static ArrayList<Artista> listaArtistas = new ArrayList<Artista>();
    public FireApp ref = new FireApp();
    public TextView cancionesMasEscuchadas;
    public TextView albumsVendidos;
    public TableLayout canciones;
    public TableRow albums;




    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        cancionesMasEscuchadas = (TextView) view.findViewById(R.id.listaCanciones);
        canciones = (TableLayout) view.findViewById(R.id.tablaCanciones);
        cancionesMasEscuchadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                canciones.removeAllViews();
                addSongs();
            }
        });

        ref.searchSongsDataBase();
        ref.searchAlbumsDataBase();
        ref.searchArtistDataBase();

        listaCanciones= ref.listaCanciones;
        listaAlbums = ref.listaAlbums;
        listaArtistas = ref.listaArtistas;

        return  view;
    }


    public void addSongs(){

        Collections.sort(listaCanciones, new ListennigComparator());

        for(int i=0;i<listaCanciones.size();i++){
            TableRow tr = new TableRow(getContext());
            TextView numero = new TextView(getContext());
            TextView tv = new TextView(getContext());
            numero.setText(String.valueOf(i+1)+"  ");
            tv.setTextSize(16);
            tv.setPadding(10,10,10,10);
            numero.setTextSize(16);
            tr.setId(i);
            tv.setText(listaCanciones.get(i).getNombre_cancion());
            tr.setClickable(true);
            tr.setOnClickListener(this);
            tr.addView(numero);
            tr.addView(tv);
            canciones.addView(tr);
        }
        canciones.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        try {
            int idView =v.getId();
            String nombreCancion = listaCanciones.get(idView).nombre_cancion;
            int reproducciones = listaCanciones.get(idView).getVecesEscuchada();
            int idCancion = listaCanciones.get(idView).getId_cancion();
            int idAlbum = listaCanciones.get(idView).getAlbum();
            int idArtista = listaCanciones.get(idView).getArtista();
            String nombreAlbum = listaAlbums.get(idAlbum-1).getNombreAlbum();
            String nombreArtista = listaArtistas.get(idArtista-1).getNombre();
            FragmentManager fragmentManager2 = getFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
            fragmentTransaction2.addToBackStack("xyz");
            fragmentTransaction2.hide(MainFragment.this);
            Fragment canciones = new SongFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",idCancion);
            bundle.putString("nombre", nombreCancion);
            bundle.putInt("reproducciones", reproducciones);
            bundle.putInt("idAlbum", idAlbum);
            bundle.putString("nombreAlbum", nombreAlbum);
            bundle.putInt("artista", idArtista);
            bundle.putString("nombreArtista", nombreArtista);
            canciones.setArguments(bundle);
            fragmentTransaction2.add(R.id.framePrincipal,canciones);
            fragmentTransaction2.commit();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
