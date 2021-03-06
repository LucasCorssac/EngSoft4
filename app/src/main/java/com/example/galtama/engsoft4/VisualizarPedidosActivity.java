package com.example.galtama.engsoft4;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class VisualizarPedidosActivity extends AppCompatActivity {

    DatabaseReference dbRef;

    ListView listViewPedido;
    List<Pedido> pedidoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_pedidos);

        dbRef = FirebaseDatabase.getInstance().getReference("pedidos");


        listViewPedido = (ListView) findViewById(R.id.listViewPedidos);
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

                    for (DataSnapshot pedidoSnapshot : userPedidoSnapshot.getChildren() )
                    {
                        Pedido pedido = pedidoSnapshot.getValue(Pedido.class);

                        pedidoList.add(pedido);
                    }

                }

                PedidoAdapter adapter = new PedidoAdapter(VisualizarPedidosActivity.this, pedidoList);
                listViewPedido.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        listViewPedido.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),PedidoView.class);
                intent.putExtra("pedido", pedidoList.get(position));
                startActivity(intent);
            }
        });
    }






}
