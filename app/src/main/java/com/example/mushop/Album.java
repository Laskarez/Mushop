package com.example.mushop;

import java.util.ArrayList;

/**
 * Created by Daniel on 17/10/2016.
 */

public class Album {


    private int idAlbum;
    private String nombreAlbum;
    private int año;
    private int ventas;
    private int numeroCanciones;
    private int idArtista;
    private ArrayList<Integer> idCanciones;

    public Album() {
    }

    public Album(int idAlbum, String nombreAlbum, int año, int ventas, int numeroCanciones,
                 int idArtista) {
        this.idAlbum = idAlbum;
        this.nombreAlbum = nombreAlbum;
        this.año = año;
        this.ventas = ventas;
        this.numeroCanciones = numeroCanciones;
        this.idArtista = idArtista;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public ArrayList<Integer> getIdCanciones() {
        return idCanciones;
    }

    public void setIdCanciones(ArrayList<Integer> idCanciones) {
        this.idCanciones = idCanciones;
    }
}
