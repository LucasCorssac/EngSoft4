package com.example.galtama.engsoft4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FazerOfertaActivity extends AppCompatActivity {

    private static final String TAG = "FazerOfertaActivity";

    private DatabaseReference dbRef;


    private EditText edit_text_oferta_valor;
    private Button button_enviar_oferta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_oferta);

        edit_text_oferta_valor = (EditText) findViewById(R.id.edit_text_oferta_valor);
        button_enviar_oferta = (Button) findViewById(R.id.button_enviar_oferta);

        button_enviar_oferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarOferta();
            }
        });

        dbRef = FirebaseDatabase.getInstance().getReference("ofertas");


    }


    private void enviarOferta()
    {

    }





}
