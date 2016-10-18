package com.example.mushop;

import java.util.Comparator;


public class SellingComparator implements Comparator<Album> {

        @Override
        public int compare(Album lhs, Album rhs) {
            if (lhs.getVentas()>rhs.getVentas()) {
                return -1;
            } else if (lhs.getVentas()<rhs.getVentas()) {
                return 1;
            }
            return 0;
        }

}
