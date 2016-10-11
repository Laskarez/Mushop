package com.example.mushop;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Daniel on 10/10/2016.
 */

public class FireApp extends Application{

    FirebaseDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = FirebaseDatabase.getInstance();

    }
}
