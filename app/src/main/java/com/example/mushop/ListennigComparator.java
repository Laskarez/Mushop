package com.example.mushop;

import java.util.Comparator;

/**
 * Created by Daniel on 12/10/2016.
 */

public class ListennigComparator implements Comparator<Cancion> {

    @Override
    public int compare(Cancion lhs, Cancion rhs) {
        if (lhs.getVecesEscuchada()>rhs.getVecesEscuchada()) {
            return -1;
        } else if (lhs.getVecesEscuchada()<rhs.getVecesEscuchada()) {
            return 1;
        }
        return 0;
    }
}
