package com.example.galtama.engsoft4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class VisualizarPedidosActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    DatabaseReference dbRef;

    //ListView listViewPedido;
    List<Pedido> pedidoList;

    TextView textViewValor ;
    TextView textViewData ;
    TextView textcausaSocial;
    ImageView imageView;

    int index;

    private StorageReference mStorageRef;

    GestureDetector gestureDetector;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pedidos_layout);

        dbRef = FirebaseDatabase.getInstance().getReference("pedidos");

        mStorageRef = FirebaseStorage.getInstance().getReference();


        //listViewPedido = (ListView) findViewById(R.id.listViewPedidos);
        pedidoList = new ArrayList<>();


        textViewValor = (TextView) findViewById(R.id.list_pedidos_layout_valor);
        textViewData = (TextView) findViewById(R.id.list_pedidos_layout_dataPagamento);
        textcausaSocial = (TextView) findViewById(R.id.list_pedidos_layout_causaSocial);
        imageView = (ImageView) findViewById(R.id.list_pedidos_layout_imageView);

        index = 0;

        gestureDetector = new GestureDetector(this, this);


    }

    private void loader(Pedido pedido){

        StorageReference testRef = mStorageRef.child("images/" + pedido.getIdPedido());

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(testRef)
                .into(imageView);


        textViewValor.setText(Integer.toString(pedido.getValorPedido()));
        textViewData.setText(pedido.getdataPagamento());
        textcausaSocial.setText(pedido.getcausaSocial());

    }


    @Override
    protected void onStart() {
        super.onStart();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                pedidoList.clear();
                index = 0;

                for(DataSnapshot pedidoSnapshot: dataSnapshot.getChildren()){

                    Pedido pedido = pedidoSnapshot.getValue(Pedido.class);

                    pedidoList.add(pedido);
                }

                //PedidoAdapter adapter = new PedidoAdapter(VisualizarPedidosActivity.this, pedidoList);
                //listViewPedido.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if(!pedidoList.isEmpty())
            loader(pedidoList.get(index));

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        return gestureDetector.onTouchEvent(motionEvent);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        if(e1.getX() - e2.getX() > 50){ //left


            loader(pedidoList.get(--index));

            return true;
        }

        if(e2.getX() - e1.getX() > 50) { // right

            loader(pedidoList.get(++index));

            return true;
        }
        else {

            return true ;
        }

    }
}
