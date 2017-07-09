package com.example.galtama.engsoft4;

import android.media.Image;
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
    String causaSocial;
    //Image imagem;


    public Pedido() {

    }

    public Pedido(String idPedido, int valorPedido, String dataPagamento, String causaSocial) {
        this.valorPedido = valorPedido;
        this.dataPagamento = dataPagamento;
        this.idPedido = idPedido;
        this.causaSocial = causaSocial;
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

    public String getcausaSocial() {
        return this.causaSocial;
    }

}
