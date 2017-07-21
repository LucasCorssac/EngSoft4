package com.example.galtama.engsoft4;

import java.io.Serializable;

/**
 * Created by Galtama on 15/07/2017.
 */

public class Oferta implements Serializable {

    private int valorCobrado;
    private String idOferta;
    private String userID;
    private String nome;

    public Oferta() {
    }

    public Oferta(int valorCobrado, String idOferta, String userID, String nome) {
        this.valorCobrado = valorCobrado;
        this.idOferta = idOferta;
        this.userID = userID;
        this.nome = nome;
    }

    public int getValorCobrado() {
        return valorCobrado;
    }

    public String getIdOferta() {
        return idOferta;
    }

    public String getUserID() {
        return userID;
    }

    public String getNome() {
        return nome;
    }
}
