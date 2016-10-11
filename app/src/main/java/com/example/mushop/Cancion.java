package com.example.mushop;

/**
 * Created by Daniel on 11/10/2016.
 */

public class Cancion {

    public int id_cancion;
    public String nombre_cancion;
    public int vecesEscuchada;

    public Cancion(int id_cancion, String nombre_cancion, int vecesEscuchada) {
        this.id_cancion = id_cancion;
        this.nombre_cancion = nombre_cancion;
        this.vecesEscuchada = vecesEscuchada;
    }

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }

    public int getVecesEscuchada() {
        return vecesEscuchada;
    }

    public void setVecesEscuchada(int vecesEscuchada) {
        this.vecesEscuchada = vecesEscuchada;
    }

    public String getNombre_cancion() {
        return nombre_cancion;
    }

    public void setNombre_cancion(String nombre_cancion) {
        this.nombre_cancion = nombre_cancion;
    }
}
