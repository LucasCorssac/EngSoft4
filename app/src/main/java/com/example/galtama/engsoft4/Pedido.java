package com.example.galtama.engsoft4;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Galtama on 29/05/2017.
 */

public class Pedido {

    int valorPedido;
    String dataPagamento;
    String idPedido;


    public Pedido() {

    }

    public Pedido(String idPedido, int valorPedido, String dataPagamento) {
        this.valorPedido = valorPedido;
        this.dataPagamento = dataPagamento;
        this.idPedido = idPedido;
    }




    public int getValorPedido() {
        return valorPedido;
    }

    public String getdataPagamento(){
        //Date date = new GregorianCalendar(ano_DiaDoPagamento, mes_DiaDoPagamento - 1 , dia_DiaDoPagamento).getTime();
        //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        //return df.format(date);

        return dataPagamento;

    }

}
