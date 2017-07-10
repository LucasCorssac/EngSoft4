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

    String idPedido;
    int valorPedido;
    String dataPagamento;
    String causaSocial;
    String nome;
    //Image imagem;


    public Pedido() {

    }

    public Pedido(String idPedido, int valorPedido, String dataPagamento, String causaSocial, String nome) {
        this.valorPedido = valorPedido;
        this.dataPagamento = dataPagamento;
        this.idPedido = idPedido;
        this.causaSocial = causaSocial;
        this.nome = nome;
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

    public String getIdPedido(){return idPedido;}

    public String getNome(){return nome;}

}
