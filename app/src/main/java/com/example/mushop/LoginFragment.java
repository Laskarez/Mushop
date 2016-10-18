package com.example.mushop;


import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    public ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
    public static EditText nombreUsuario;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_login, container, false);
        FireApp ref = new FireApp();
        ref.searchUsersDataBase();

        listaUsuarios = ref.listaUsuarios;
        nombreUsuario = (EditText) view.findViewById(R.id.usuario);


        Button ingresar = (Button) view.findViewById(R.id.ingresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verificarUsuario()){
                    Toast.makeText(getContext(),"Ingreso con exito",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"No se encontro ese usuario",Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }


    public boolean verificarUsuario(){
        for(int i=0;i<listaUsuarios.size();i++){
            if(nombreUsuario.getText().toString().equals(listaUsuarios.get(i).getUsuario())){
                FragmentManager fragmentManager2 = getFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                fragmentTransaction2.addToBackStack("xyz");
                fragmentTransaction2.hide(LoginFragment.this);
                Fragment mainFragment = new MainFragment();
                fragmentTransaction2.add(R.id.framePrincipal, mainFragment);
                fragmentTransaction2.commit();
                return true;
            }
        }
        return false;
    }

}
