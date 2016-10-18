package com.example.mushop;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.HashMap;


public class SongFragment extends Fragment {

    public static TextView cancion;
    public static TextView album;
    public static ImageView imagen;
    public Button play;
    public static boolean reproduciendo = false;
    public static boolean reproducida;
    public static int idCancion;
    public static String nombreCancion ;
    public static int reproducciones;
    public static int idAlbum ;
    public static String nombreAlbum;
    public static int idArtista;
    public static String nombreArtista;
    public static int segundos = 0;


    public SongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_song, container, false);

        Bundle datos = this.getArguments();
        idCancion = datos.getInt("id");
        nombreCancion = datos.getString("nombre");
        reproducciones = datos.getInt("reproducciones");
        idAlbum = datos.getInt("idAlbum");
        nombreAlbum = datos.getString("nombreAlbum");
        idArtista = datos.getInt("artista");
        nombreArtista = datos.getString("nombreArtista");
        reproducida = false;
        segundos = 0;

        cancion = (TextView) view.findViewById(R.id.nombreCancion);
        album = (TextView) view.findViewById(R.id.album);
        imagen = (ImageView) view.findViewById(R.id.imagen);
        cancion.setText(nombreCancion);
        album.setText(nombreArtista +" - "+nombreAlbum);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference().child("album_images");
        String imageName = "album_";
        if(idAlbum<10){
            imageName+="0"+idAlbum+".jpg";
        }else{
            imageName+=idAlbum+".jpg";
        }

        StorageReference albumImage = storageRef.child(imageName);

        albumImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getContext()).load(uri).resize(350,350).into(imagen);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.getMessage();
            }
        });

        play = (Button) view.findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!reproducida){
                    sumarReproduccion();

                }
                cambiarEstado();
            }
        });

        return view;
    }

    public void sumarReproduccion(){
        String cancion = "song_";
        if(idCancion<10){
            cancion+="0"+idCancion;
        }else{
            cancion+=idCancion;
        }

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        DatabaseReference song = reference.child("song").child(cancion).child("listening");
        song.setValue(reproducciones+1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                reproducida = true;
            }
        });

    }

    public void cambiarEstado(){

        if (reproduciendo){
            play.setBackgroundResource(R.drawable.ic_play_button);
            reproduciendo = false;

        }else{
            play.setBackgroundResource(R.drawable.ic_pause_button);
            reproduciendo = true;
        }
    }

}
