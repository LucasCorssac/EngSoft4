package com.example.galtama.engsoft4;

/**
 * Created by Galtama on 15/07/2017.
 */

public class Oferta {

    int valorCobrado;
    String idOferta;

    public Oferta(int valorCobrado, String idOferta) {
        this.valorCobrado = valorCobrado;
        this.idOferta = idOferta;
    }

    public int getValorCobrado() {
        return valorCobrado;
    }

    public String getIdOferta() {
        return idOferta;
    }

}
