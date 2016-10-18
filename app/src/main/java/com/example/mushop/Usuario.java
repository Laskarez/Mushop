package com.example.mushop;

import java.util.ArrayList;

/**
 * Created by Daniel on 18/10/2016.
 */

public class Usuario {
    private int idUsuario;
    private String nombre;
    private String usuario;
    private ArrayList<Integer> albumesComprados;

    public Usuario() {

    }

    public Usuario(int idUsuario, String nombre, String usuario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Integer> getAlbumesComprados() {
        return albumesComprados;
    }

    public void setAlbumesComprados(ArrayList<Integer> albumesComprados) {
        this.albumesComprados = albumesComprados;
    }
}
