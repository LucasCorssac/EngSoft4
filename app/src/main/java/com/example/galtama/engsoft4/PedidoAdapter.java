package com.example.galtama.engsoft4;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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


    public PedidoAdapter(Activity context, List<Pedido> pedidoList){
        super(context, R.layout.list_pedidos_layout, pedidoList);
        this.context = context;
        this.pedidoList = pedidoList;

        mStorageRef = FirebaseStorage.getInstance().getReference();


    }

    private void downloadFirebaseFile(){



//        File localFile = null;
//        try {
//            localFile = File.createTempFile("images", "jpg");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        testRef.getFile(localFile)
//                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                        // Successfully downloaded data to local file
//                        // ...
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle failed download
//                // ...
//            }
//        });
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_pedidos_layout, null, true);

        TextView textViewValor = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_valor);
        TextView textViewData = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_dataPagamento);
        TextView textcausaSocial = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_causaSocial);
        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.list_pedidos_layout_imageView);

        Pedido pedido = pedidoList.get(position);


        StorageReference testRef = mStorageRef.child("images/" + pedido.getIdPedido());

        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(testRef)
                .into(imageView);


        textViewValor.setText(Integer.toString(pedido.getValorPedido()));
        textViewData.setText(pedido.getdataPagamento());
        textcausaSocial.setText(pedido.getcausaSocial());

        return listViewItem;


    }
}


