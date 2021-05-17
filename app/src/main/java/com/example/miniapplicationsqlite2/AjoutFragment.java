package com.example.miniapplicationsqlite2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AjoutFragment extends Fragment {


    EditText editNom , editNum;
    Button btnSauvegarder;
    DatabaseHandler db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

// Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_ajout, container, false);
        editNom=(EditText)v.findViewById(R.id.editNom);
        editNum=(EditText)v.findViewById(R.id.editNum);
        btnSauvegarder=(Button)v.findViewById(R.id.BtnSaveg);
        btnSauvegarder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editNom.getText().toString().equals("")) {
                    editNom.setError("Invalide!");
                    editNom.requestFocus();
                    return;
                }
                if (editNum.getText().toString().equals("")) {
                    editNum.setError("Invalide!");
                    editNum.requestFocus();
                }

                DatabaseHandler db=(DatabaseHandler) getArguments().getSerializable("Clé");
                Contact c = new Contact(editNom.getText().toString(), editNum.getText().toString());
                long r = db.addContact(c);
                if (r != -1)
                    Toast.makeText(getActivity().getApplication(), "Ajout avec succès ", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getActivity().getApplication(), "Problème dans l'ajout ", Toast.LENGTH_LONG).show();

            }
        });
        return v;
    }
}
