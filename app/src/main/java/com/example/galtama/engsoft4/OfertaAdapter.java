package com.example.galtama.engsoft4;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Galtama on 19/07/2017.
 */

public class OfertaAdapter extends ArrayAdapter<Oferta> {


    //list_pedidos_layout_imageView

    private Activity context;
    private List<Oferta> ofertaList;

    //private StorageReference mStorageRef;
    //private DatabaseReference dbRef;


    public OfertaAdapter(Activity context, List<Oferta> ofertaList) {
        super(context, R.layout.pedido_layout_short, ofertaList);
        this.context = context;
        this.ofertaList = ofertaList;

        //mStorageRef = FirebaseStorage.getInstance().getReference();
        //dbRef = FirebaseDatabase.getInstance().getReference("pedidos");


    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.pedido_layout_short, null, true);

        TextView textViewValor = (TextView) listViewItem.findViewById(R.id.text_pedidoShort_valor);
        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.text_pedidoShort_nome);


//        TextView textViewData = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_dataPagamento);
//        TextView textcausaSocial = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_causaSocial);
//        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_Nome);
//        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.list_pedidos_layout_imageView);
        if(!ofertaList.isEmpty())
        {
            final Oferta oferta = ofertaList.get(position);


            textViewValor.setText(Integer.toString(oferta.getValorCobrado()));
            textViewNome.setText(oferta.getNome());

        }



        return listViewItem;


    }

}





