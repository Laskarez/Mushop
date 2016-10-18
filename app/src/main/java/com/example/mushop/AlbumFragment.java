package com.example.mushop;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

public class AlbumFragment extends Fragment implements View.OnClickListener {

    public FireApp ref;
    public ArrayList<Album> listaAlbums = new ArrayList<Album>();
    public ArrayList<Artista> listaArtistas = new ArrayList<Artista>();
    public TextView titulo;
    public TableLayout albumes;

    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_album, container, false);

        ref = new FireApp();

        titulo = (TextView) view.findViewById(R.id.Albums);
        albumes = (TableLayout) view.findViewById(R.id.tablaAlbumes);
        ref.searchAlbumsDataBase();
        ref.searchArtistDataBase();
        listaAlbums = ref.listaAlbums;
        listaArtistas = ref.listaArtistas;
        titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                albumes.removeAllViews();
                addAlbums();
            }
        });
        return view;
    }

    public void addAlbums() {

        Collections.sort(listaAlbums, new SellingComparator());

        for (int i = 0; i < listaAlbums.size(); i++) {

            TableLayout.LayoutParams tableRowParams =
                    new TableLayout.LayoutParams
                            (TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);

            int leftMargin = 0;
            int topMargin = 10;
            int rightMargin = 0;
            int bottomMargin = 5;

            tableRowParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

            TableRow trimagen = new TableRow(getContext());
            trimagen.setId(i);
            final ImageView imagen = new ImageView(getContext());
            imagen.setId(i);
            int idAlbum = listaAlbums.get(i).getIdAlbum();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference().child("album_images");
            String imageName = "album_";
            if (idAlbum < 10) {
                imageName += "0" + idAlbum + ".jpg";
            } else {
                imageName += idAlbum + ".jpg";
            }

            StorageReference albumImage = storageRef.child(imageName);

            albumImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.with(getContext()).load(uri).resize(350, 350).into(imagen);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.getMessage();
                }
            });
            imagen.setClickable(true);
            imagen.setOnClickListener(this);
            trimagen.setGravity(Gravity.CENTER);
            trimagen.setLayoutParams(tableRowParams);

            trimagen.addView(imagen);
            albumes.addView(trimagen);

            TableRow tr = new TableRow(getContext());
            TextView tv = new TextView(getContext());
            tv.setTextSize(16);
            tv.setPadding(10, 10, 10, 10);
            tr.setId(i);
            tv.setText(listaAlbums.get(i).getNombreAlbum() + " - " + listaAlbums.get(i).getAño());
            tr.setClickable(true);
            tr.setOnClickListener(this);
            tr.setGravity(Gravity.CENTER);
            tableRowParams.setMargins(0, 0, 0, 30);
            tr.setLayoutParams(tableRowParams);
            tr.addView(tv);
            albumes.addView(tr);
        }
        albumes.setVisibility(View.VISIBLE);
    }


    @Override
    public void onClick(View v) {
        try {
            int idView = v.getId();
            String nombreAlbum = listaAlbums.get(idView).getNombreAlbum();
            int ventas = listaAlbums.get(idView).getVentas();
            int idAlbum = listaAlbums.get(idView).getIdAlbum();
            int idArtista = listaAlbums.get(idView).getIdArtista();
            int año = listaAlbums.get(idView).getAño();
            int numeroCanciones = listaAlbums.get(idView).getNumeroCanciones();
            String nombreArtista = listaArtistas.get(idView).getNombre();
            FragmentManager fragmentManager2 = getFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
            fragmentTransaction2.addToBackStack("xyz");
            fragmentTransaction2.hide(AlbumFragment.this);
            Fragment albumBuyFragment = new AlbumBuyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("idAlbum", idAlbum);
            bundle.putString("nombreAlbum", nombreAlbum);
            bundle.putInt("ventas", ventas);
            bundle.putInt("año", año);
            bundle.putInt("numeroCanciones", numeroCanciones);
            bundle.putInt("artista", idArtista);
            bundle.putString("nombreArtista", nombreArtista);
            albumBuyFragment.setArguments(bundle);
            fragmentTransaction2.add(R.id.framePrincipal, albumBuyFragment);
            fragmentTransaction2.commit();
        } catch (Exception e) {
            e.getMessage();
        }

    }
}
