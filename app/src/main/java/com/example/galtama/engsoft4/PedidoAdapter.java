package com.example.galtama.engsoft4;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by Galtama on 29/05/2017.
 */

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    //list_pedidos_layout_imageView

    private static final String TAG = "VisualizarPedidosActivity";

    private Activity context;
    private List<Pedido> pedidoList;

    private StorageReference mStorageRef;
    private DatabaseReference dbRef;


    public PedidoAdapter(Activity context, List<Pedido> pedidoList){
        super(context, R.layout.list_pedidos_layout, pedidoList);
        this.context = context;
        this.pedidoList = pedidoList;

        mStorageRef = FirebaseStorage.getInstance().getReference();
        dbRef = FirebaseDatabase.getInstance().getReference("pedidos");


    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_pedidos_layout, null, true);

        TextView textViewValor = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_valor);
        TextView textViewData = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_dataPagamento);
        TextView textcausaSocial = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_causaSocial);
        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_Nome);
        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.list_pedidos_layout_imageView);

        Button buttonFazerOferta= (Button) listViewItem.findViewById(R.id.buttonFazerOferta);


        final Pedido pedido = pedidoList.get(position);

        buttonFazerOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerOfertaDialog(pedido, v);
            }
        });

        StorageReference testRef = mStorageRef.child("images/" + pedido.getIdPedido());

        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(testRef)
                .into(imageView);


        textViewValor.setText(Integer.toString(pedido.getValorPedido()));
        textViewData.setText(pedido.getdataPagamento());
        textcausaSocial.setText(pedido.getcausaSocial());
        textViewNome.setText(pedido.getNome());

        return listViewItem;


    }


    private void fazerOfertaDialog(Pedido pedido, View v)
    {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);

        LayoutInflater inflater = context.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.fazer_oferta_dialog, (ViewGroup) v.getRootView());

        dialogBuilder.setView(dialogView);

        final EditText editTextValorOferta = (EditText) dialogView.findViewById(R.id.dialog_valor_oferta);
        final Button buttonEnviarOferta = (Button) dialogView.findViewById(R.id.button_enviar_oferta);

        dialogBuilder.setTitle("Enviar Oferta");

        final AlertDialog alertDialog = dialogBuilder.create();

        alertDialog.show();

        final DatabaseReference pedidoReference = dbRef.child(pedido.getUserId()).child(pedido.getIdPedido());


        buttonEnviarOferta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = pedidoReference.push().getKey();
                String valorString = editTextValorOferta.getText().toString().trim();
                int valor = Integer.parseInt(valorString);

                Oferta oferta = new Oferta(valor, key);

                pedidoReference.child(key).setValue(oferta);

            }
        });


        alertDialog.dismiss();


    }




}


