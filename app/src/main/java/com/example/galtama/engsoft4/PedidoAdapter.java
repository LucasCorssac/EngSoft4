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
 * Created by Galtama on 29/05/2017.
 */

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    private Activity context;
    private List<Pedido> pedidoList;

    public PedidoAdapter(Activity context, List<Pedido> pedidoList){
        super(context, R.layout.list_pedidos_layout, pedidoList);
        this.context = context;
        this.pedidoList = pedidoList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_pedidos_layout, null, true);

        TextView textViewValor = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_valor);
        TextView textViewData = (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_dataPagamento);
        TextView textcausaSocial= (TextView) listViewItem.findViewById(R.id.list_pedidos_layout_causaSocial);

        Pedido pedido = pedidoList.get(position);

        textViewValor.setText(Integer.toString(pedido.getValorPedido()));
        textViewData.setText(pedido.getdataPagamento());
        textcausaSocial.setText(pedido.getcausaSocial());

        return listViewItem;


    }
}


