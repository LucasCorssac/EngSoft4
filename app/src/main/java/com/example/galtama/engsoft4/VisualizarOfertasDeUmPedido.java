package com.example.galtama.engsoft4;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VisualizarOfertasDeUmPedido extends AppCompatActivity {
    DatabaseReference dbRef;

    ListView listViewOferta;
    List<Oferta> ofertaList;
    Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_ofertas_de_um_pedido);


        listViewOferta = (ListView) findViewById(R.id.listViewMinhasOfertas);
        ofertaList = new ArrayList<>();

        pedido = (Pedido) getIntent().getSerializableExtra("pedido");

        dbRef = FirebaseDatabase.getInstance().getReference("pedidos").
                child(pedido.getUserId()).
                child(pedido.getIdPedido()).
                child("ofertas");



    }

    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ofertaList.clear();

                for(DataSnapshot ofertaSnapshot: dataSnapshot.getChildren()){

                        ofertaList.add(ofertaSnapshot.getValue(Oferta.class));

                }

                OfertaAdapter adapter = new OfertaAdapter(VisualizarOfertasDeUmPedido.this, ofertaList);
                listViewOferta.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listViewOferta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aceitarOferta(ofertaList.get(position));
            }
        });

    }


    void aceitarOferta(Oferta oferta)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.aceitar_oferta_dialog, null);

        dialogBuilder.setView(dialogView);

        final Button buttonAceitarOfertaSim = (Button) dialogView.findViewById(R.id.buttonAceitarOfertaSim);
        final Button buttonAceitarOfertaNao = (Button) dialogView.findViewById(R.id.buttonAceitarOfertaNao);

        dialogBuilder.setTitle("Aceitar Oferta?");

        final AlertDialog alertDialog = dialogBuilder.create();

        alertDialog.show();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("pedidos");

        final DatabaseReference pedidoReference = dbRef.child(pedido.getUserId()).child(pedido.getIdPedido());




        buttonAceitarOfertaSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pedidoReference.removeValue();

                Toast.makeText(VisualizarOfertasDeUmPedido.this, "Oferta Aceita!", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();

                Intent intent = new Intent(VisualizarOfertasDeUmPedido.this,VisualizarMeusPedidosActivity.class);
                startActivity(intent);

            }
        });

        buttonAceitarOfertaNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });




    }









}
