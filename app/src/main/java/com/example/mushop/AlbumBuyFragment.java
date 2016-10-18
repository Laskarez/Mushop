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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumBuyFragment extends Fragment {

    public static int añoAl;
    public static int ventas;
    public static int idAlbum ;
    public static String nombreAlbum;
    public static int idArtista;
    public static int numCanciones;
    public static String nombreArtista;
    public static TextView album;
    public static TextView añoAlbum;
    public static TextView numeroCanciones;
    public static TextView nombreArtistaAlbum;
    public static ImageView imagenAlbum;
    public Button comprar;
    public static boolean albumComprado = false;


    public AlbumBuyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_album_buy, container, false);

        Bundle datos = this.getArguments();
        idAlbum = datos.getInt("idAlbum");
        nombreAlbum = datos.getString("nombreAlbum");
        ventas = datos.getInt("ventas");
        añoAl = datos.getInt("año");
        idArtista = datos.getInt("artista");
        nombreArtista = datos.getString("nombreArtista");
        numCanciones = datos.getInt("numeroCanciones");
        albumComprado = false;
        album = (TextView) view.findViewById(R.id.nombreAlbum);
        añoAlbum = (TextView) view.findViewById(R.id.año);
        numeroCanciones = (TextView) view.findViewById(R.id.numeroCanciones);
        nombreArtistaAlbum = (TextView) view.findViewById(R.id.nombreArtista);
        imagenAlbum = (ImageView) view.findViewById(R.id.imagenAlbum);
        album.setText(nombreAlbum);
        añoAlbum.setText(String.valueOf(añoAl));
        numeroCanciones.setText(String.valueOf(numCanciones)+ " Canciones");
        nombreArtistaAlbum.setText(nombreArtista);

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
                Picasso.with(getContext()).load(uri).resize(350,350).into(imagenAlbum);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.getMessage();
            }
        });

        comprar = (Button) view.findViewById(R.id.comprar);
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!albumComprado){
                    comprarAlbum();
                }else{
                    Toast.makeText(getContext(),"Ya compraste este album.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    public void comprarAlbum(){
        String album = "album_";
        if(idAlbum<10){
            album+="0"+idAlbum;
        }else{
            album+=idAlbum;
        }

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference();
        DatabaseReference song = reference.child("album").child(album).child("sellings");
        song.setValue(ventas+1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(),"Album comprado!!", Toast.LENGTH_SHORT).show();
                albumComprado = true;

            }
        });
    }

}
