package com.example.galtama.engsoft4;

/**
 * Created by Galtama on 17/07/2017.
 */

public class Usuario {

    private String id;
    private String nome;
    private int dinheiro;

    public Usuario(){

    }

    public Usuario(String ID, String nome, int dinheiro) {
        this.id = ID;
        this.nome = nome;
        this.dinheiro = dinheiro;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getDinheiro() {
        return dinheiro;
    }
}
