package com.example.galtama.engsoft4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VisualizarMeusPedidosActivity extends AppCompatActivity {

    DatabaseReference dbRef;

    ListView listViewPedido;
    List<Pedido> pedidoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_meus_pedidos);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        dbRef = FirebaseDatabase.getInstance().getReference("pedidos").child(uid);


        listViewPedido = (ListView) findViewById(R.id.listViewMeusPedidos);
        pedidoList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pedidoList.clear();

                for(DataSnapshot userPedidoSnapshot: dataSnapshot.getChildren()){

                    Pedido pedido = userPedidoSnapshot.getValue(Pedido.class);

                    pedidoList.add(pedido);

                }

                PedidoAdapter adapter = new PedidoAdapter(VisualizarMeusPedidosActivity.this, pedidoList);
                listViewPedido.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
