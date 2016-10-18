package com.example.mushop;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Daniel on 10/10/2016.
 */

public class FireApp extends Application{

    public ArrayList<Cancion> listaCanciones = new ArrayList<Cancion>();
    public ArrayList<Album> listaAlbums = new ArrayList<Album>();
    public ArrayList<Artista> listaArtistas = new ArrayList<Artista>();


    @Override
    public void onCreate() {
        super.onCreate();

        listaCanciones = new ArrayList<Cancion>();
        listaAlbums = new ArrayList<Album>();
        listaArtistas = new ArrayList<Artista>();

    }

    public void searchSongsDataBase() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        DatabaseReference song = mData.child("song");
        listaCanciones = new ArrayList<Cancion>();
        song.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    Cancion cancion = new Cancion(
                            Integer.parseInt(map.get("id_song").toString()),
                            map.get("song_name").toString(),
                            Integer.parseInt(map.get("listening").toString()),
                            Integer.parseInt(map.get("album").toString()),
                            Integer.parseInt(map.get("artist").toString()));
                    listaCanciones.add(cancion);
                } catch (Exception e) {
                    Log.v("Excepcion", e.getMessage());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void searchAlbumsDataBase() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        DatabaseReference album = mData.child("album");
        listaAlbums = new ArrayList<Album>();
        album.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    Album album = new Album(
                            Integer.parseInt(map.get("id_album").toString()),
                            map.get("album_name").toString(),
                            Integer.parseInt(map.get("year").toString()),
                            Integer.parseInt(map.get("sellings").toString()),
                            Integer.parseInt(map.get("number_songs").toString()),
                            Integer.parseInt(map.get("id_artist").toString()));
                    listaAlbums.add(album);
                } catch (Exception e) {
                    Log.v("Excepcion", e.getMessage());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void searchArtistDataBase() {
        DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
        DatabaseReference album = mData.child("artist");
        listaArtistas = new ArrayList<Artista>();
        album.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                try {
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    Artista artista = new Artista(
                            Integer.parseInt(map.get("id_artist").toString()),
                            map.get("artist_name").toString());
                    listaArtistas.add(artista);
                } catch (Exception e) {
                    Log.v("Excepcion", e.getMessage());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
