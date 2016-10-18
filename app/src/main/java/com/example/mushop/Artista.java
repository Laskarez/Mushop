package com.example.mushop;

import java.util.ArrayList;

/**
 * Created by Daniel on 17/10/2016.
 */

public class Artista {

    private int idArtista;
    private String nombre;
    private ArrayList<Integer> albums;

    public Artista() {
    }

    public Artista(int idArtista, String nombre) {
        this.idArtista = idArtista;
        this.nombre = nombre;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Integer> albums) {
        this.albums = albums;
    }
}
