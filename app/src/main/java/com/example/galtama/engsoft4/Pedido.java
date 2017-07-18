package com.example.galtama.engsoft4;

import android.media.Image;
import android.widget.TextView;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Galtama on 29/05/2017.
 */

public class Pedido implements Serializable {

    String idPedido;
    int valorPedido;
    String dataPagamento;
    String causaSocial;
    String nome;
    String userId;
    //Image imagem;


    public Pedido() {

    }

    public Pedido(String idPedido, int valorPedido, String dataPagamento, String causaSocial, String nome, String userId) {
        this.valorPedido = valorPedido;
        this.dataPagamento = dataPagamento;
        this.idPedido = idPedido;
        this.causaSocial = causaSocial;
        this.nome = nome;
        this.userId = userId;
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

    public String getUserId(){return userId;}

}
